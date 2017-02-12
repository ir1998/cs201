package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataStorage;

/**
 * Servlet implementation class MainMenuServlet
 */
@WebServlet("/MainMenuServlet")
public class MainMenuServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public MainMenuServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			HttpSession session = request.getSession();
			DataStorage ds = (DataStorage)session.getAttribute("dataStorage");
			if(request.getParameter("searchUsers") != null){
				if(request.getParameter("searchedUser") != null){
					String searched = (String)request.getParameter("searchedUser");
					session.setAttribute("searchResults", ds.searchForUser(searched));
					session.setAttribute("multipleSearch", true);
				}
				response.sendRedirect("SearchUsers.jsp");
			}else if(request.getParameter("searchMovies") != null){
				response.sendRedirect("MovieSearchMenu.jsp");
			}else if(request.getParameter("viewFeed") != null){
				response.sendRedirect("Feed.jsp");
			}else if(request.getParameter("viewProfile") != null){
				response.sendRedirect("Profile.jsp");
			}else if(request.getParameter("logOut") != null){
				response.sendRedirect("LoginPage.jsp");
			}else if(request.getParameter("exit") != null){
				response.sendRedirect("entry_kaimakis.jsp");
			}else{
				response.sendRedirect("MainMenu.jsp");
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
