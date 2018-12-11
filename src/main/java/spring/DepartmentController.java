package spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepartmentController {

	Connection conn = null;
	private String strConnect = "jdbc:mysql://localhost:3306/hr?&serverTimezone=Europe/Amsterdam";
	private String uid = "root";
	private String pwd = "M1ll44rd";
	private int numEmployees;

	@PostMapping("/department")
	public String department(@RequestParam(name="departmentName") String dept, Model model) throws SQLException {
		conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT count(*) as aantal FROM employees join departments using (department_id) WHERE department_name = ?");
		stmt.setString(1, dept);
		try (ResultSet rs = stmt.executeQuery()) {	
			while (rs.next()) {
				numEmployees = rs.getInt("aantal");
			}
			model.addAttribute("departmentName", dept.toUpperCase());
			model.addAttribute("numEmployees", numEmployees);
		};

		PreparedStatement emps = conn.prepareStatement("SELECT last_name, salary FROM employees join departments using (department_id) WHERE department_name = ?");
		emps.setString(1, dept);

		ArrayList<Salary> salaries = new ArrayList<>();
		try (ResultSet rs = emps.executeQuery()) {
			while (rs.next()) {
				Salary s = new Salary();
				s.last_name = rs.getString("last_name");
				s.salary =	rs.getDouble("salary");
				salaries.add(s);
			}
			model.addAttribute("salaries", salaries);
		}

		return "/department";
	}

	public void opdrachtTwee() {

	}

	public void opdrachtDrie() {

	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(strConnect, uid, pwd);
		return conn;
	}
}
