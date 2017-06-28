

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class studentQuery
 */
@WebServlet("/studentQuery")
public class studentQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//could do "jdbc.mysql://localhost:3306"
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentGrades?user=root&password=root&useSSL=false");
			st =  conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Student WHERE fname='"+firstname+"';");
			while(rs.next()){
				int studentID = rs.getInt("studentID");
				String fname = rs.getString("fname");
				String lname =rs.getString("lname");
				System.out.println(fname+ " "+ lname + " " + studentID);
			}
		}catch(SQLException sqle){
			System.out.println("sqle: "+ sqle.getMessage());
		}catch(ClassNotFoundException cnfe){
			System.out.println("cnfe: "+ cnfe.getMessage());
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException sqle){
				System.out.println("sqle closing stuff: "+ sqle.getMessage());
			}
		}
	}
}
