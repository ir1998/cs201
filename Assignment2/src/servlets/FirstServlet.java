package servlets;


import java.io.IOException;
import main.ApplicationInterface;
import data.DataStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String fileName = request.getParameter( "file" );
		request.setAttribute( "file", fileName );
		DataStorage ds = new DataStorage((String)request.getAttribute("file"));
		//if error, set error variable as error
		if(!ds.getError().equals("")){
			request.setAttribute("error", ds.getError());
			//then redirect back to page
			RequestDispatcher rd = request.getRequestDispatcher("GetFile.jsp");
			rd.forward(request, response);
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("dataStorage", ds);
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