
package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bsl.employee.dao.EmployeeDaoImpl;
import com.bsl.employee.service.EmployeeService;
import com.bsl.employee.vo.Employee;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeDaoImpl employeeDao;

	@Test
	public void testGetEmployee() {
		when(employeeDao.getEmployees()).thenReturn(
				(List<Employee>) Stream.of(new Employee(45, "sreeja", 555, "IT"), new Employee(49, "raj", 6767, "HR"))
						.collect(Collectors.toList()));
		assertEquals(2, employeeService.getEmployees().size());
	}

	
	@Test
	public void testDeleteEmployee() {
		Employee employee = new Employee(45, "sreeja", 555, "IT");
		Integer eid = employee.getEmployeeId();
		employeeService.deleteEmployee(eid);
		verify(employeeDao, times(1)).deleteEmployee(eid);
	}

	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("sreeja");
		employee.setEmployeeAge(34);
		employee.setEmployeeDept("Finance");
		employeeService.updateEmployee(1, employee);
		verify(employeeDao, times(1)).updateEmployee(1, employee);
	}

	
	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Vani");
		employee.setEmployeeAge(34);
		employee.setEmployeeDept("Finance");
		employeeService.saveEmployee(employee);
		verify(employeeDao, times(1)).saveEmployee(employee);
	}
}
