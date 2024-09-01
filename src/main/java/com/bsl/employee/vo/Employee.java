package com.bsl.employee.vo;

public class Employee {

	public Integer employeeId;
	public String employeeName;
	public Integer employeeAge;
	public String employeeDept;

	public Employee() {
	}
	
	public Employee(Integer employeeId, String employeeName, Integer employeeAge, String employeeDept) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.employeeDept = employeeDept;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(Integer employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeDept() {
		return employeeDept;
	}

	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}

	@Override
	public String toString() {
		return "EmployeeDao [EmployeeId=" + employeeId + ", EmployeeName=" + employeeName + ", EmployeeAge="
				+ employeeAge + ", EmployeeDept=" + employeeDept + "]";
	}

}
