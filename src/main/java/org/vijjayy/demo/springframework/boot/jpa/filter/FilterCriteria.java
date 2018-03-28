package org.vijjayy.demo.springframework.boot.jpa.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class FilterCriteria<T extends Comparable<T>> {

	private final FilterOperation operation;
	
	private final String fieldName;
	
	private final Function<String, T> converterFunction;
	
	private T convertedSingleValue;
	
	private T minValue;
	
	private T maxValue;
	
	private final Collection<String> originalValues;
	
	private final Collection<T> convertedValues;
	
	public FilterCriteria(String fieldName, String filter, Function<String, T> converterFunction) {
		
		Validate.notEmpty(fieldName);
		Validate.notEmpty(filter);
		
		this.fieldName = fieldName;
		
		this.converterFunction = converterFunction;
		
		String[] filterSplit = StringUtils.split(filter, ":");
		
		if(filterSplit.length != 2) {
			throw new IllegalArgumentException("");//TODO
		}
		
		String operation = filterSplit[0];
		String operationValue = filterSplit[1];
		
		this.operation = FilterOperation.fromValue(operation);
		
		String[] operationValues = StringUtils.split(operationValue, ",");
		
		if(operationValues.length < 1) {
			throw new IllegalArgumentException("");//TODO
		}
		
		this.originalValues = Arrays.asList(operationValues);
		this.convertedValues = new ArrayList<>();
		validateAndAssign(operationValues);
		
		
	}


	public T getConvertedSingleValue() {
		return convertedSingleValue;
	}

	public T getMinValue() {
		return minValue;
	}


	public T getMaxValue() {
		return maxValue;
	}


	public FilterOperation getOperation() {
		return operation;
	}


	public String getFieldName() {
		return fieldName;
	}


	public Function<String, T> getConverterFunction() {
		return converterFunction;
	}


	public Collection<String> getOriginalValues() {
		return originalValues;
	}


	public Collection<T> getConvertedValues() {
		return convertedValues;
	}


	private void validateAndAssign(String[] operationValues) {
		
		if(FilterOperation.BETWEEN == operation) {
			if(operationValues.length != 2) {
				throw new IllegalArgumentException("");//TODO
			}else {
				
				T value1 = this.converterFunction.apply(operationValues[0]);
				T value2 = this.converterFunction.apply(operationValues[1]);
				
				if( value1.compareTo(value2) > 0) {
					this.minValue = value2;
					this.maxValue = value1;
				}else {
					this.minValue = value1;
					this.maxValue = value2;
				}
			}
		}else if(FilterOperation.IN == operation || FilterOperation.NOT_IN == operation) {
			convertedValues.addAll(originalValues.stream().map(converterFunction).collect(Collectors.toList()));
		}else {
			this.convertedSingleValue = converterFunction.apply(operationValues[0]);
		}
		
		
	}
	
	
	
}
