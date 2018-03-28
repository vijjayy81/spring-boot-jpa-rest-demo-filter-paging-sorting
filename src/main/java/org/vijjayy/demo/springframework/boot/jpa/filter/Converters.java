package org.vijjayy.demo.springframework.boot.jpa.filter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class Converters {

	private Map<Class<?>, Function<String, ? extends Comparable<?>>> map = new HashMap<>();

	@PostConstruct
	public void init() {
		map.put(Date.class, Converters::convert);
		map.put(String.class, s -> s);
		map.put(Long.class, Long::valueOf);
		map.put(Integer.class, Integer::valueOf);
		// Add more converters
	}

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> Function<String, T> getFunction(Class<?> classObj) {
		return (Function<String, T>) map.get(classObj);
	}

	private static Date convert(String dateStr) {
		LocalDate localDate = LocalDate.parse(dateStr);
		return java.sql.Date.valueOf(localDate);
	}

}
