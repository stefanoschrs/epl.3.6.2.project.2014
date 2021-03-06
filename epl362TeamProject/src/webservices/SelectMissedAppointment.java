package webservices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class SelectMissedAppointment {

	public LinkedList<String> select_missed_appointment() {
		try {
			LinkedList<String> resultString = new LinkedList<String>();
			Connection connect = null;
			Statement statement = null;
			ResultSet resultSet = null;

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager					.getConnection("jdbc:mysql://localhost/362?"
							+ "user=root&password=");

			statement = connect.createStatement();
			resultSet = statement
					.executeQuery("select * from appointments WHERE (`missed`=1)");

			while (resultSet.next()) {
				String client_id = resultSet.getString("client_id");
				String date = resultSet.getString("date");
				resultString.add("Client: "+client_id+"    Date: "+date);
			}

			connect.close();

			return resultString;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
