package spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateEmployeeController {
	
	Connection conn = null;
	private String strConnect = "jdbc:mysql://localhost:3306/hr?&serverTimezone=Europe/Amsterdam";
	private String uid = "root";
	private String pwd = "M1ll44rd";

	@PostMapping("/updateemployee")
	public String updateEmployee(@RequestParam(name = "lastName") String lastName, @RequestParam(name = "employeeId") int empId, Model model) throws SQLException {
		conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement("update employees set manager_id = ? where last_name = ?");
		stmt.setInt(1, empId);
		stmt.setString(2, lastName.toUpperCase());
		
		model.addAttribute("empId", empId);
		model.addAttribute("lastName", lastName);
		model.addAttribute("numUpdate", stmt.executeUpdate());
		
		return "updateemployee";
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(strConnect, uid, pwd);
		return conn;
	}
}
