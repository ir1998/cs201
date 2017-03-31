package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CinemateException;
import data.DataStorage;
import data.StringConstants;

@WebServlet("/FileChooserServlet")
public class FileChooserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileChooserServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get filename
		System.out.println("fuckkkk");
		String file = request.getParameter(StringConstants.INFILE);
		
		//try to parse file, if a CinemateException is caught, we know there was an error
		try{
			DataStorage ds = new DataStorage(file);
			request.getSession().setAttribute(StringConstants.DATA, ds);
			System.out.println("line 35");
			//response.sendRedirect(StringConstants.JSP_EXT+StringConstants.LOGIN_JSP);
		}
		catch(CinemateException e){
			System.out.println("line 38");
			request.setAttribute(StringConstants.ERROR, e.getMessage());
			PrintWriter pw = response.getWriter();
			pw.print(e.getMessage());
			pw.flush();
			//request.getRequestDispatcher(StringConstants.JSP_EXT+StringConstants.FILE_CHOOSER_JSP).forward(request, response);
		}
	}

}
