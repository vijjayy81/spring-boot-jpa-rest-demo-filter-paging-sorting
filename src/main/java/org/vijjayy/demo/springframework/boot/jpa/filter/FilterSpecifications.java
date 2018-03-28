package org.vijjayy.demo.springframework.boot.jpa.filter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FilterSpecifications<E, T extends Comparable<T>> {

	private EnumMap<FilterOperation, Function<FilterCriteria<T>, Specification<E>>> map;
	
	public FilterSpecifications() {
		initSpecifications();
	}

	
	public Function<FilterCriteria<T>, Specification<E>> getSpecification(FilterOperation operation){
		return map.get(operation);
	}
	
	private Map<FilterOperation, Function<FilterCriteria<T>, Specification<E>>> initSpecifications() {

		map = new EnumMap<>(
				FilterOperation.class);

		map.put(FilterOperation.EQUAL, filterCriteria -> (root, query, cb) -> cb
				.equal(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));
		
		map.put(FilterOperation.NOT_EQUAL, filterCriteria -> (root, query, cb) -> cb
				.notEqual(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));
		
		map.put(FilterOperation.GREATER_THAN, filterCriteria -> (root, query, cb) -> cb
				.greaterThan(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));
		
		map.put(FilterOperation.GREATER_THAN_OR_EQUAL_TO,
				filterCriteria -> (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(filterCriteria.getFieldName()),
						filterCriteria.getConvertedSingleValue()));
		
		map.put(FilterOperation.LESS_THAN, filterCriteria -> (root, query, cb) -> cb
				.lessThan(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));
		
		map.put(FilterOperation.LESSTHAN_OR_EQUAL_TO, filterCriteria -> (root, query, cb) -> cb
				.lessThanOrEqualTo(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));
		
		map.put(FilterOperation.IN, filterCriteria -> (root, query, cb) -> root.get(filterCriteria.getFieldName())
				.in(filterCriteria.getConvertedValues()));
		
		map.put(FilterOperation.NOT_IN, filterCriteria -> (root, query, cb) -> cb
				.not(root.get(filterCriteria.getFieldName()).in(filterCriteria.getConvertedSingleValue())));
		
		map.put(FilterOperation.BETWEEN,
				filterCriteria -> (root, query, cb) -> cb.between(root.get(filterCriteria.getFieldName()),
						filterCriteria.getMinValue(), filterCriteria.getMaxValue()));
		
		map.put(FilterOperation.CONTAINS, filterCriteria -> (root, query, cb) -> cb
				.like(root.get(filterCriteria.getFieldName()), "%" + filterCriteria.getConvertedSingleValue() + "%"));

		return map;
	}
}
