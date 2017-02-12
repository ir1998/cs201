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
 * Servlet implementation class MovieSearchServlet
 */
@WebServlet("/MovieSearchServlet")
public class MovieSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieSearchServlet() {
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
		if(request.getParameter("searchMovie") != null){
			String searched = (String)request.getParameter("searchedMovie");
			System.out.println("hello " + searched);
			if(session.getAttribute("moviesBy").equals("actor")){
				session.setAttribute("searchResults", ds.searchByActor(searched));
			}else if(session.getAttribute("moviesBy").equals("title")){
				session.setAttribute("searchResults", ds.searchByTitle(searched));
			}else if(session.getAttribute("moviesBy").equals("genre")){
				session.setAttribute("searchResults", ds.searchByGenre(searched));
			}
			session.setAttribute("multipleSearch", true);
			response.sendRedirect("moviesBy.jsp");
		} else if(request.getParameter("back") != null){
			response.sendRedirect("MovieSearchMenu.jsp");
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