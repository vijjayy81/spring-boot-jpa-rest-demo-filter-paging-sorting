package org.vijjayy.demo.springframework.boot.jpa.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vijjayy.demo.springframework.boot.jpa.entity.Employee;
import org.vijjayy.demo.springframework.boot.jpa.repository.EmployeesRespository;
import org.vijjayy.demo.springframework.boot.jpa.v1.api.model.ApiModelEmployeeResource;
import org.vijjayy.demo.springframework.boot.jpa.v1.api.model.ApiModelEmployees;
import org.vijjayy.demo.springframework.boot.jpa.v1.api.model.ApiModelPageAndSort;

@Service
public class EmployeesDAO {

	@Autowired
	private EmployeesRespository employeesRespository;

	public ApiModelEmployees getEmployees(Specification<Employee> spec, PageRequest pageRequest) {

		ApiModelEmployees employeesResponse = new ApiModelEmployees();

		Page<Employee> employeesPage = employeesRespository.findAll(spec, pageRequest);

		ApiModelPageAndSort pagingResponse = new ApiModelPageAndSort();

		pagingResponse.setHasNextPage(employeesPage.hasNext());
		pagingResponse.setHasPreviousPage(employeesPage.hasPrevious());
		pagingResponse.setTotalNumberOfRecords(employeesPage.getTotalElements());
		pagingResponse.setTotalNumberOfPages(employeesPage.getTotalPages());
		pagingResponse.setPageNumber(pageRequest.getPageNumber() + 1);
		pagingResponse.setPageSize(pageRequest.getPageSize());

		employeesResponse.setPaging(pagingResponse);
		List<Employee> employees = employeesPage.getContent();

		employeesResponse.setData(employees.stream().map(EmployeesDAO::getEmployee).collect(Collectors.toList()));
		return employeesResponse;

	}

	private static ApiModelEmployeeResource getEmployee(Employee employee) {
		ApiModelEmployeeResource res = new ApiModelEmployeeResource();

		res.setDob(employee.getDateOfBirth());
		res.setFirstName(employee.getFirstName());
		res.setGender(employee.getGender());
		res.setEmployeeNo(employee.getEmployeeNo());
		res.setJoiningDate(employee.getJoiningDate());
		res.setLastName(employee.getLastName());
		res.setSalary(employee.getSalary());
		return res;

	}
}
