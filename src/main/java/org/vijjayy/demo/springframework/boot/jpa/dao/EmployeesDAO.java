package org.vijjayy.demo.springframework.boot.jpa.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vijjayy.demo.springframework.boot.jpa.entity.Employee;
import org.vijjayy.demo.springframework.boot.jpa.repository.EmployeesRepository;
import org.vijjayy.demo.springframework.boot.jpa.v1.api.model.ApiModelEmployeeResource;
import org.vijjayy.demo.springframework.boot.jpa.v1.api.model.ApiModelEmployees;
import org.vijjayy.demo.springframework.boot.jpa.v1.api.model.ApiModelPageAndSort;


/**
 * 
 * Data access layer for Entity 'Employee'
 * 
 * @author Vijjayy
 *
 */
@Service
public class EmployeesDAO {
	
	private final EmployeesRepository employeesRepository;

	/**
	 * Prefer Constructor Injection over field injection 
	 * 
	 * @param employeesRespository
	 */
	public EmployeesDAO(EmployeesRepository employeesRespository) {
		super();
		this.employeesRepository = employeesRespository;
	}

	/**
	 * 
	 * Get the list of Employee entities based on the specification and page request and convert that to {@link ApiModelEmployees}
	 * 
	 * @param spec - Specification for the Entity 'Employee'
	 * @param pageRequest - PageRequest
	 * @return
	 */
	public ApiModelEmployees getEmployees(Specification<Employee> spec, PageRequest pageRequest) {

		Validate.notNull(spec, "Specification can't be null");
		Validate.notNull(pageRequest, "pageRequest can't be null");
		
		ApiModelEmployees employeesResponse = new ApiModelEmployees();

		
		//Get Page info from EmployeesRepository
		Page<Employee> employeesPage = employeesRepository.findAll(spec, pageRequest);

		ApiModelPageAndSort pagingResponse = new ApiModelPageAndSort();

		//Set the flag to indicate next page exists
		pagingResponse.setHasNextPage(employeesPage.hasNext());
		
		//Set the flag to indicate previous page exists
		pagingResponse.setHasPreviousPage(employeesPage.hasPrevious());
		
		//Set the total number of records for the given Filter Specification
		pagingResponse.setTotalNumberOfRecords(employeesPage.getTotalElements());
		
		//Set the total number of pages for the given filter specification and pagerequests
		pagingResponse.setTotalNumberOfPages(employeesPage.getTotalPages());
		
		//Page numbers are indexed from 0 but to the consume we follow start index as 1
		pagingResponse.setPageNumber(pageRequest.getPageNumber() + 1);
		
		//Number of records per page 
		pagingResponse.setPageSize(pageRequest.getPageSize());

		employeesResponse.setPaging(pagingResponse);
		
		//Get the Employee List from the Page
		List<Employee> employees = employeesPage.getContent();

		//Map the data to ApiModelEmployeeResource using lambda function
		employeesResponse.setData(employees.stream().map(this::getEmployee).collect(Collectors.toList()));
		
		return employeesResponse;

	}

	/**
	 * Mapper from {@link Employee} to {@link ApiModelEmployeeResource}
	 * 
	 * 
	 * @param employee
	 * @return
	 */
	private ApiModelEmployeeResource getEmployee(Employee employee) {
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
