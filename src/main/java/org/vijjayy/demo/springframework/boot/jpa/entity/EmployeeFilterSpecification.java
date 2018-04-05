package org.vijjayy.demo.springframework.boot.jpa.entity;

import java.time.chrono.ChronoLocalDate;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vijjayy.demo.springframework.boot.jpa.filter.Converters;
import org.vijjayy.demo.springframework.boot.jpa.filter.FilterCriteria;
import org.vijjayy.demo.springframework.boot.jpa.filter.FilterSpecifications;


/**
 * 
 * Specification for Employee Entity
 * 
 * @author Vijjayy
 *
 */
@Service
public class EmployeeFilterSpecification {
	
	
	/**
	 * {@link FilterSpecifications} for Entity {@link Employee} and Field type {@link ChronoLocalDate} (LocalDate)
	 */
	@Autowired
	private FilterSpecifications<Employee, ChronoLocalDate> dateTypeSpecifications;

	/**
	 * {@link FilterSpecifications} for Entity {@link Employee} and Field type {@link String}
	 */
	@Autowired
	private FilterSpecifications<Employee, String> stringTypeSpecifications;

	/**
	 * {@link FilterSpecifications} for Entity {@link Employee} and Field type {@link Integer}
	 * 
	 */
	@Autowired
	private FilterSpecifications<Employee, Integer> integerTypeSpecifications;

	/**
	 * {@link FilterSpecifications} for Entity {@link Employee} and Field type {@link Long}
	 */
	@Autowired
	private FilterSpecifications<Employee, Long> longTypeSpecifications;
	
	
	/**
	 * Converter Functions
	 */
	@Autowired
	private Converters converters;
	
	
	/**
	 * Returns the Specification for Entity {@link Employee} for the given fieldName and filterValue for the field type Date
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Employee> getDateTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(ChronoLocalDate.class), dateTypeSpecifications);
	}

	/**
	 * Returns the Specification for Entity {@link Employee} for the given fieldName and filterValue for the field type String
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Employee> getStringTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(String.class), stringTypeSpecifications);
	}
	
	/**
	 * Returns the Specification for Entity {@link Employee} for the given fieldName and filterValue for the field type Long
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Employee> getLongTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(Long.class), longTypeSpecifications);
	}
	
	
	/**
	 * Returns the Specification for Entity {@link Employee} for the given fieldName and filterValue for the field type Integer
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Employee> getIntegerTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(Integer.class), integerTypeSpecifications);
	}

	/**
	 * Generic method to return {@link Specification} for Entity {@link Employee}
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @param converter
	 * @param specifications
	 * @return
	 */
	private <T extends Comparable<T>> Specification<Employee> getSpecification(String fieldName,
			String filterValue, Function<String, T> converter, FilterSpecifications<Employee, T> specifications) {

		if (StringUtils.isNotBlank(filterValue)) {
			
			//Form the filter Criteria
			FilterCriteria<T> criteria = new FilterCriteria<>(fieldName, filterValue, converter);
			return specifications.getSpecification(criteria.getOperation()).apply(criteria);
		}

		return null;

	}
	
}
