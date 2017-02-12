package servlets;
// /Users/Nick/Desktop/cs201/Assignment2/WebContent/samplexml2

import java.io.IOException;
import data.DataStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		HttpSession session = request.getSession();
		DataStorage ds = (DataStorage)session.getAttribute("dataStorage");
		if(ds.loginAttempt(un, pw) == 1){
			//next page
			session.setAttribute("user", ds.getUser(un));
			session.setAttribute("username", un);
			session.setAttribute("password", pw);
			response.sendRedirect("MainMenu.jsp");
		}else if(ds.loginAttempt(un, pw) == -1){
			session.setAttribute("error", "Username invalid");
			response.sendRedirect("LoginPage.jsp");
		}else if(ds.loginAttempt(un, pw) == -2){
			session.setAttribute("error", "Incorrect password");
			response.sendRedirect("LoginPage.jsp");
		}else{
			session.setAttribute("error", "Unknown error has occurred");
			response.sendRedirect("LoginPage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
