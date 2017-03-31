package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataStorage;
import data.StringConstants;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(true);
		//string that describes which search we are doing
		String search = (String)request.getParameter("selectSearch");
		//search parameter provided to us by the user
		String searchParam = request.getParameter(StringConstants.SEARCH_PARAM);

		DataStorage ds = (DataStorage) request.getSession().getAttribute(StringConstants.DATA);
		//boolean that stores whether this is a user or movie search
		Boolean userSearch = false;
		//execute the search base on the search type given
		
		if(search.equals("movies")){
			//gets first six characters
			String searchKey = searchParam.substring(0,6).toLowerCase();
			//gets all characters including and past 6th, trimming leading and ending whitespace
			String searchVal = searchParam.substring(6).trim();
			
			//search by title genre or actor based on prefix of search
			if(searchKey.equals("genre:")){
				request.setAttribute(StringConstants.RESULTS, ds.searchByGenre(searchVal));
			}else if(searchKey.equals("title:")){
				request.setAttribute(StringConstants.RESULTS, ds.searchByTitle(searchVal));
			}else if(searchKey.equals("actor:")){
				request.setAttribute(StringConstants.RESULTS, ds.searchByActor(searchVal));
			}
		}else if(search.equals("users")){
			userSearch = true;
			request.setAttribute(StringConstants.RESULTS, ds.searchForUser(searchParam));
		}
		

		//set an attribute to tell us on the front end whether the search was for users or movies
		request.setAttribute(StringConstants.IS_USER_SEARCH, userSearch);
		request.getRequestDispatcher(StringConstants.JSP_EXT+StringConstants.SEARCH_JSP).forward(request, response);
	}
}
