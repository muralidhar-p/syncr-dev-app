package com.bsl.employee.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bsl.employee.service.EmployeeService;
import com.bsl.employee.vo.Employee;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	@GetMapping("/healthCheck")
	public String justToTestApplication() {
		return "Welcome";
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}
	
	@DeleteMapping("/delete/{eid}")
	public void deleteEmployee(@PathVariable Integer eid) {
		 employeeService.deleteEmployee(eid);
	}
	
	@PutMapping("/update/{eid}")
	public void updateEmployee(@PathVariable Integer eid, @RequestBody Employee employee) {
		employeeService.updateEmployee(eid, employee);
	}

	@PostMapping("/employees")
	public void savedEmployee(@RequestBody Employee employee) {
		System.out.println(employee.employeeId);
		System.out.println(employee.employeeName);
		System.out.println(employee.employeeAge);
		System.out.println(employee.employeeDept);
		employeeService.saveEmployee(employee);
	}							

	@GetMapping("/employees/{eid}")
	public Employee getOneEmployee(@PathVariable Integer eid) {
		return employeeService.getOneEmployee(eid);
	}
}
