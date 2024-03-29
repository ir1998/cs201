package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataStorage;
import data.StringConstants;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	 
		DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA);	
		
		if(request.getParameter("fullname").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter your full name");
			pw.flush();
		}else if(request.getParameter("username").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter a username");
			pw.flush();
		}else if(request.getParameter("password").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter a password");
			pw.flush();
		}else if(request.getParameter("imgurl").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter an image url");
			pw.flush();
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
