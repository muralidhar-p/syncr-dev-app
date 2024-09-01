package com.bsl.employee.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.bsl.employee.vo.Employee;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	public EmployeeDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<Employee> getEmployees() {
		logger.info("Invoking getEmployees()");
		List<Employee> list = getJdbcTemplate().query("select * from employee", new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt(1));
				employee.setEmployeeName(rs.getString(2));
				employee.setEmployeeAge(rs.getInt(3));
				employee.setEmployeeDept(rs.getString(4));
				return employee;
			}
		});
		return list;
	}

	@Cacheable(value = "getEmployee")
	public Employee getOneEmployee(Integer id) {
		logger.info("Invoking getOneEmployee()");

		List<Employee> list2 = getJdbcTemplate().query("select * from employee", new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt(1));
				employee.setEmployeeName(rs.getString(2));
				employee.setEmployeeAge(rs.getInt(3));
				employee.setEmployeeDept(rs.getString(4));
				return employee;
			}

		});

		Employee employee = list2.stream().filter(e -> e.getEmployeeId().equals(id)).findFirst().get();
		return employee;
	}

	public void deleteEmployee(Integer eid) {
		logger.info("Invoking deleteEmployee()");
		jdbcTemplate.update("delete from employee where employeeId= " + eid);
		logger.info("Deleted");
	}

	public void saveEmployee(Employee employee) {
		logger.info("Invoking saveEmployee()");

		getJdbcTemplate().update(
				"insert into employee(EmployeeId,EmployeeName,EmployeeAge,EmployeeDept) values(?,?,?,?)",
				employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeAge(),
				employee.getEmployeeDept());
	}

	public void updateEmployee(Integer id, Employee employee) {
		logger.info("Invoking updateEmployee()");

		StringBuffer queryBuffer = new StringBuffer("update employee set ");

		int fieldCounter = 0;
		if (employee.getEmployeeName() != null) {
			queryBuffer.append("EmployeeName='" + employee.getEmployeeName() + "'");
			fieldCounter++;
		}

		if (employee.getEmployeeAge() != 0) {
			if (fieldCounter > 0) {
				queryBuffer.append(", ");
			}
			queryBuffer.append("EmployeeAge='" + employee.getEmployeeAge() + "'");
			fieldCounter++;
		}
		
		if (employee.getEmployeeDept() != null) {
			if (fieldCounter > 0) {
				queryBuffer.append(", ");
			}
			queryBuffer.append("EmployeeDept='" + employee.getEmployeeDept() + "'");
		}
		String whereCondition = " where EmployeeId='" + employee.getEmployeeId() + "'";
		queryBuffer.append(whereCondition);
		logger.info("updateEmployeeQuery : " + queryBuffer.toString());
		getJdbcTemplate().update(queryBuffer.toString());

	}
}
