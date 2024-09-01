package com.bsl.employee.dao;

import java.util.List;

import com.bsl.employee.vo.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployees();

	public Employee getOneEmployee(Integer eid);

	public void deleteEmployee(Integer eid);

	public void saveEmployee(Employee employee);

	public void updateEmployee(Integer id,Employee employee);
}
