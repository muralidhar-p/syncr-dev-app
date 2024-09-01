package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bsl.employee.controller.EmployeeController;
import com.bsl.employee.service.EmployeeService;
import com.bsl.employee.vo.Employee;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService employeeService;

	@Test
	public void testGetEmployees() {
		List<Employee> list = new ArrayList<Employee>();
		Employee employee = new Employee();
		employee.setEmployeeId(176);
		employee.setEmployeeName("Sreeja");
		employee.setEmployeeAge(23);
		employee.setEmployeeDept("IT");
		list.add(employee);
		when(employeeService.getEmployees()).thenReturn(list);
		assertEquals(employeeService.getEmployees(), employeeController.getEmployees());
		assertNotNull(employee);
	}

	@Test
	public void testUpdateEmployees() {
		Employee employee1 = new Employee(1, "sreelatha", 45, "IT");
		employeeController.updateEmployee(1, employee1);
		verify(employeeService, times(1)).updateEmployee(1, employee1);
		assertNotNull(employee1);
	}

	@Test
	public void testDeleteEmployees() {
		Employee employee = new Employee(19, "Sreevani", 12, "Finance");
		Integer eid = employee.getEmployeeId();
		employeeController.deleteEmployee(eid);
		verify(employeeService, times(1)).deleteEmployee(eid);
	}

	@Test
	public void testSaveEmployees() {
		Employee employee = new Employee(9, "Sreejitha", 34, "HR");

		employeeController.savedEmployee(employee);
		verify(employeeService, times(1)).saveEmployee(employee);
	}
}
