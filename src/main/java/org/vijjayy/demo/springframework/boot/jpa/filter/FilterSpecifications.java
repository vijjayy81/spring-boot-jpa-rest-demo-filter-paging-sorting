package org.vijjayy.demo.springframework.boot.jpa.filter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * 
 * Generic filter specification which holds {@link EnumMap} for
 * {@link FilterOperation} with Lambda functions <br>
 * 
 * Refer <br>
 * <table border="1">
 * <tr>
 * <td>Symbol</td>
 * <td>Operation</td>
 * <td>Example filter query param</td>
 * <tr>
 * <td>eq</td>
 * <td>Equals</td>
 * <td>city=eq:Sydney</td>
 * <tr>
 * <td>neq</td>
 * <td>Not Equals</td>
 * <td>country=neq:uk</td>
 * <tr>
 * <td>gt</td>
 * <td>Greater Than</td>
 * <td>amount=gt:10000</td>
 * <tr>
 * <td>gte</td>
 * <td>Greater Than or equals to</td>
 * <td>amount=gte:10000</td>
 * <tr>
 * <td>lt</td>
 * <td>Less Than</td>
 * <td>amount=lt:10000</td>
 * <tr>
 * <td>lte</td>
 * <td>Less Than or equals to</td>
 * <td>amount=lte:10000</td>
 * <tr>
 * <td>in</td>
 * <td>IN</td>
 * <td>country=in:uk, usa, au</td>
 * <tr>
 * <td>nin</td>
 * <td>Not IN</td>
 * <td>country=nin:fr, de, nz</td>
 * <tr>
 * <td>btn</td>
 * <td>Between</td>
 * <td>joiningDate=btn:2018-01-01, 2016-01-01</td>
 * <tr>
 * <td>like</td>
 * <td>Like</td>
 * <td>firstName=like:John</td>
 * </tr>
 * </table>
 * *
 * 
 * @author Vijjayy
 *
 * @param <E>
 * @param <T>
 */
@Service
public class FilterSpecifications<E, T extends Comparable<T>> {

	private EnumMap<FilterOperation, Function<FilterCriteria<T>, Specification<E>>> map;

	public FilterSpecifications() {
		initSpecifications();
	}

	public Function<FilterCriteria<T>, Specification<E>> getSpecification(FilterOperation operation) {
		return map.get(operation);
	}

	/**
	 * 
	 * Forms the generic filter specifications for the operations
	 * {@link FilterOperation}
	 * 
	 * @return
	 */
	private Map<FilterOperation, Function<FilterCriteria<T>, Specification<E>>> initSpecifications() {

		map = new EnumMap<>(FilterOperation.class);

		// Equal
		map.put(FilterOperation.EQUAL, filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));

		map.put(FilterOperation.NOT_EQUAL, filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.notEqual(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));

		map.put(FilterOperation.GREATER_THAN,
				filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(
						root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));

		map.put(FilterOperation.GREATER_THAN_OR_EQUAL_TO,
				filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
						root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));

		map.put(FilterOperation.LESS_THAN, filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.lessThan(root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));

		map.put(FilterOperation.LESSTHAN_OR_EQUAL_TO,
				filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
						root.get(filterCriteria.getFieldName()), filterCriteria.getConvertedSingleValue()));

		map.put(FilterOperation.IN, filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> root
				.get(filterCriteria.getFieldName()).in(filterCriteria.getConvertedValues()));

		map.put(FilterOperation.NOT_IN, filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.not(root.get(filterCriteria.getFieldName()).in(filterCriteria.getConvertedSingleValue())));

		map.put(FilterOperation.BETWEEN,
				filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(
						root.get(filterCriteria.getFieldName()), filterCriteria.getMinValue(),
						filterCriteria.getMaxValue()));

		map.put(FilterOperation.CONTAINS, filterCriteria -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.like(root.get(filterCriteria.getFieldName()), "%" + filterCriteria.getConvertedSingleValue() + "%"));

		return map;
	}
}
