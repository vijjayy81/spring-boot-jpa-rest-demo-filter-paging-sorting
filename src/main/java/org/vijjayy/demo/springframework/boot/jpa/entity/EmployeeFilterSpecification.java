package org.vijjayy.demo.springframework.boot.jpa.entity;

import java.util.Date;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vijjayy.demo.springframework.boot.jpa.filter.Converters;
import org.vijjayy.demo.springframework.boot.jpa.filter.FilterCriteria;
import org.vijjayy.demo.springframework.boot.jpa.filter.FilterSpecifications;

@Service
public class EmployeeFilterSpecification {
	
	@Autowired
	private FilterSpecifications<Employee, Date> dateTypeSpecifications;

	@Autowired
	private FilterSpecifications<Employee, String> stringTypeSpecifications;

	@Autowired
	private FilterSpecifications<Employee, Integer> integerTypeSpecifications;

	@Autowired
	private FilterSpecifications<Employee, Long> longTypeSpecifications;
	
	@Autowired
	private Converters converters;
	
	
	public Specification<Employee> getDateTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(Date.class), dateTypeSpecifications);
	}

	public Specification<Employee> getStringTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(String.class), stringTypeSpecifications);
	}
	
	public Specification<Employee> getLongTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(Long.class), longTypeSpecifications);
	}
	
	public Specification<Employee> getIntegerTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(Integer.class), integerTypeSpecifications);
	}

	private <T extends Comparable<T>> Specification<Employee> getSpecification(String fieldName,
			String filterValue, Function<String, T> converter, FilterSpecifications<Employee, T> specifications) {

		if (StringUtils.isNotBlank(filterValue)) {
			FilterCriteria<T> criteria = new FilterCriteria<>(fieldName, filterValue, converter);
			return specifications.getSpecification(criteria.getOperation()).apply(criteria);
		}

		return null;

	}
	
}
