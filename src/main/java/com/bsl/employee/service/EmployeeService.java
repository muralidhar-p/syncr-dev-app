package com.bsl.employee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.employee.dao.EmployeeDaoImpl;
import com.bsl.employee.vo.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDaoImpl dao;

	public List<Employee> getEmployees() {

		List<Employee> employees = dao.getEmployees();
		System.out.println(employees);
		return employees;
	}

	public Employee getOneEmployee(Integer eid) {
		return dao.getOneEmployee(eid);
	}

	public void deleteEmployee(Integer eid) {
		 dao.deleteEmployee(eid);
	}

	public void saveEmployee(Employee employee) {
		 dao.saveEmployee(employee);
	}

	public void updateEmployee(Integer id, Employee employee) {

		dao.updateEmployee(id, employee);
	}
}
