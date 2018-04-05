package org.vijjayy.demo.springframework.boot.jpa.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vijjayy.demo.springframework.boot.jpa.entity.Employee;

/**
 * 
 * Spring JPA Repository with {@link JpaSpecificationExecutor}
 * 
 * @author Vijjayy
 *
 */
@Repository
public interface EmployeesRepository extends CrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

}
