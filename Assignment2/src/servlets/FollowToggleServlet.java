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
import data.User;

/**
 * Servlet implementation class FollowToggleServlet
 */
@WebServlet("/FollowToggleServlet")
public class FollowToggleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowToggleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(request.getParameter("toggle").equals("Follow")){
			PrintWriter pw = response.getWriter();
			pw.print("Unfollow");
			pw.flush();
		}else{
			PrintWriter pw = response.getWriter();
			pw.print("Follow");
			pw.flush();
		}
		
//		DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA); 
//		User loggedInUser = ds.getLoggedInUser();
//		String profileView = (String)session.getAttribute("profileView");
//		
//		User profileUser = ds.getUser(profileView);
//		if(!loggedInUser.isFollowing(profileUser.getUsername())){
//			//ds.addFollower(profileUser, loggedInUser);
//			//session.setAttribute("ds", ds);
//			PrintWriter pw = response.getWriter();
//			pw.print("Unfollow");
//			pw.flush();
//		}else{
//			//ds.removeFollower(profileUser, loggedInUser);
//			PrintWriter pw = response.getWriter();
//			pw.print("Follow");
//			pw.flush();
//		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
