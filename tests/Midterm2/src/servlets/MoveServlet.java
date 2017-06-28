package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MoveServlet
 */
@WebServlet("/MoveServlet")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MoveServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isStart = (String) request.getParameter("startGame");
		System.out.println("we made it ");
		if(isStart != null){
			if(isStart.trim().equals("true")){
				System.out.println("starting game");
				Map <String, Boolean> table = new HashMap<String, Boolean>();
				for(int i=0; i<4; i++){
					table.put("a" + i, false);
					table.put("b" + i, false);
					table.put("c" + i, false);
				}
				request.getSession().setAttribute("board", table);
			}
		}
		else{
			String place = (String) request.getParameter("place");
		}
		PrintWriter pw = response.getWriter();
		pw.print("");
		pw.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
