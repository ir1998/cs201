package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Event;
import data.MySQLDriver;
import data.StringConstants;

/**
 * Servlet implementation class NewEventServlet
 */
@WebServlet("/NewEventServlet")
public class NewEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLDriver mysql = (MySQLDriver) request.getSession().getAttribute("sql");
		//add an event -- get the action, the movie title, pass in null for the rating parameter
		mysql.addEvent(request.getParameter(StringConstants.ACTION), request.getParameter(StringConstants.TITLE));
	}

}
