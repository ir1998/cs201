/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M15
 * Generated at: 2017-03-06 18:35:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import data.DataStorage;
import data.Movie;
import data.Actor;
import data.StringConstants;
import java.util.List;

public final class movie_005fpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("data.DataStorage");
    _jspx_imports_classes.add("data.Actor");
    _jspx_imports_classes.add("data.StringConstants");
    _jspx_imports_classes.add("data.Movie");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<!-- get the data storage object and user info -->\n");
  DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA); 

	//if movieView is something, show movie!
	Movie movie = null;
	if(request.getParameter("movieView")!= null){
		String movieTitle = request.getParameter("movieView");
		movie = ds.getMovieByTitle(movieTitle);
	}
	

      out.write("\n");
      out.write("<head>\n");
      out.write("\t<title>User Profile</title>\n");
      out.write("\t<link rel=\"stylesheet\" href=\"../css/main.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"../css/profile.css\">\n");
      out.write("\t<link href=\"https://fonts.googleapis.com/css?family=Lato:700i\" rel=\"stylesheet\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-3.3.7-dist/*\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div>\n");
      out.write("\t\t<ul>\n");
      out.write("\t\t\t<li><a data-toggle=\"tooltip\" title=\"Feed\" href=\"feed.jsp\">Feed</a></li>\n");
      out.write("\t\t\t<li><a data-toggle=\"tooltip\" title=\"Profile\" href=\"profile.jsp\">Profile</a></li>\n");
      out.write("\t\t\t<li style=\"border-right:none;\">\n");
      out.write("\t\t\t\t<form action = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.print( StringConstants.SEARCH_SERVLET);
      out.write("\">\n");
      out.write("                            <div class=\"input-group\" id=");
      out.print( StringConstants.SEARCH );
      out.write("  style=\"width: 100%\">\n");
      out.write("                                <input style = \"width: 40%; margin-left:20px; margin-top:8px;\" type=\"text\" size = \"40\" name=\"");
      out.print( StringConstants.SEARCH_PARAM );
      out.write("\">\n");
      out.write("\t\t\t\t\t\t\t\t<select data-toggle=\"tooltip\" title=\"Select search type\" style=\"width:15%;\" name=\"selectSearch\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"movies\">Movies</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"users\">Users</option>\n");
      out.write("\t\t\t\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t\t\t\t\t<input data-toggle=\"tooltip\" title=\"Search\" style = \"width: 15%; margin-left: 5px;\" type=\"submit\" value=\"Search\">\n");
      out.write("                            </div>\n");
      out.write("                        </form>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<li data-toggle=\"tooltip\" title=\"Exit and choose new file\" style=\"float:right\"><a href=\"file_chooser.jsp\">Exit</a></li>\n");
      out.write("\t\t\t<li data-toggle=\"tooltip\" title=\"Logout\" style=\"border-right: 1px solid #bbb; float:right\"><a href=\"login.jsp\">Logout</a></li>\n");
      out.write("\t\t</ul>\n");
      out.write("\t</div>\n");
      out.write("\t<div id = \"title_container\">\n");
      out.write("\t\tCinemate\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"row panel\">\n");
      out.write("        <div class=\"col-md-8  col-xs-12\">\n");
      out.write("           <img data-toggle=\"tooltip\" title=\"");
out.print(movie.getTitle()); 
      out.write("\" id=\"movie_image\" style=\"float:left; margin:0px 30px\" src=\"");
if(movie != null){out.print(movie.getImage());} 
      out.write("\" />\n");
      out.write("           <div  class=\"header\">\n");
      out.write("                <h1>");
if(movie != null){out.print(movie.getTitle());} 
      out.write('(');
if(movie != null){out.print(movie.getYear());} 
      out.write(")</h1>\n");
      out.write("                <h2>Genre: ");
if(movie != null){out.print(movie.getGenre());} 
      out.write("</h2>\n");
      out.write("                <h2>Director: ");
if(movie != null){out.print(movie.getDirector());} 
      out.write("</h2>\n");
      out.write("                <h2>Actors: ");

                if(movie != null){
                	String toPrint="";
                	for(Actor actor:movie.getActors()){
                		toPrint += actor.getName() +", ";
                	};
                	toPrint = toPrint.substring(0, toPrint.length()-2);
                	out.print(toPrint);
                } 
      out.write("</h2>\n");
      out.write("                <h2>Writers: ");

	                if(movie != null){
	                	String toPrint= "";
	                	for(String writer: movie.getWriters()){ 
	                		toPrint += writer + ", ";
	                	}
	                	toPrint = toPrint.substring(0,toPrint.length()-2);
	                	out.print(toPrint);
	                } 
      out.write("</h2>\n");
      out.write("                <h3>Average rating: ");
if(movie != null){out.print(movie.getAvgRating());} 
      out.write("</h3>\n");
      out.write("               \n");
      out.write("                <span>");
if(movie != null){out.print(movie.getDescription());} 
      out.write("</span>\n");
      out.write("           </div>\n");
      out.write("        </div>\n");
      out.write("        <div align=\"left\" style=\"clear:both; margin:20px 20px;\">\n");
      out.write("        \t<!-- put like, dislike, movie rating here -->\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div align=\"center\" style=\"clear:both; margin: 20px 20px;\">\n");
      out.write("        <br>\n");
      out.write("        \t<table>\n");
      out.write("\t        \t<thead style=\"float:center;\">\n");
      out.write("\t        \t\t<tr><h1>Cast:</h1></tr>\n");
      out.write("\t        \t</thead>\n");
      out.write("\t        \t<tbody>\n");
      out.write("        \t");

                if(movie != null){
                	String toPrint="";
                	for(Actor actor:movie.getActors()){
                		
      out.write("\n");
      out.write("                \t\t<tr style=\"margin:10px; display:block;\">\n");
      out.write("                \t\t\t<td><img style=\"float:center; margin: 0px 20px 10px 10px; max-height:250px; min-height:200px; vertical-align:middle;\" \n");
      out.write("                \t\t\tsrc=\"");
out.print(actor.getImage());
      out.write("\"><span style=\"font-size:30px;\">");
out.print(actor.getName());
      out.write("</span></td>\n");
      out.write("                \t\t</tr>\n");
      out.write("                \t");
}
                } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("        \t</table>\n");
      out.write("        </div>\n");
      out.write("    </div>   \n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}