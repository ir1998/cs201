/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M15
 * Generated at: 2017-03-07 19:07:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Set;
import java.util.HashSet;
import data.DataStorage;
import data.User;
import data.Event;
import data.StringConstants;

public final class feed_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("java.util.HashSet");
    _jspx_imports_classes.add("data.DataStorage");
    _jspx_imports_classes.add("data.Event");
    _jspx_imports_classes.add("java.util.Set");
    _jspx_imports_classes.add("data.User");
    _jspx_imports_classes.add("data.StringConstants");
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
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<title>User Profile</title>\n");
      out.write("\t<link rel=\"stylesheet\" href= \"../css/main.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href= \"../css/feed.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href= \"../css/profile.css\">\n");
      out.write("\t<link href=\"https://fonts.googleapis.com/css?family=Lato:700i\" rel=\"stylesheet\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-3.3.7-dist/*\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!-- get the data storage object and logged in user info -->\n");
  DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA); 
	User user = ds.getLoggedInUser();
	String name = user.getFName() +" "+user.getLName();
	String un = "@"+user.getUsername();

      out.write("\n");
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
      out.write("\t<br>\n");
      out.write("\t\t<table id = \"feed\">\n");
      out.write("\t\t\t<thead >\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><h2>Feed</h2></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</thead>\n");
      out.write("\t\t\t<tbody>\n");
      out.write("<!-- \t\t\tGet the users list of following and add their own username. Then iterate through this new set and get the user object\n");
      out.write("\t\t\tand iterate through each of their feeds -->\n");
      out.write("\t\t\t\t");
 
				Set<String> following = new HashSet<>(user.getFollowing());
				following.add(user.getUsername());
				
				for (String username : following){
					
					User current = ds.getUser(username);
							
						for (Event event : current.getFeed()){
							User currentUser = ds.getUser(event.getUsername());
							String actionImage="";
							if(event.getAction().toLowerCase().equals("rated")){
								double rating = event.getRating();
								if(rating<3){
									actionImage="../img/actions/rating1.png";
								}else if(rating <5){
									actionImage="../img/actions/rating2.png";
								}else if(rating <7){
									actionImage="../img/actions/rating3.png";
								}else if(rating <9){
									actionImage="../img/actions/rating4.png";
								}else if(rating <=10){
									actionImage="../img/actions/rating5.png";
								}
							}else if(event.getAction().toLowerCase().equals("watched")){
								actionImage="../img/actions/watched.png";
							}else if(event.getAction().toLowerCase().equals("liked")){
								actionImage="../img/actions/liked.png";
							}else if(event.getAction().toLowerCase().equals("disliked")){
								actionImage="../img/actions/disliked.png";
							}
							session.setAttribute("ActionImage", actionImage);
							
      out.write("<tr>\n");
      out.write("\t\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t\t<a data-toggle=\"tooltip\" title=\"");
out.print(event.getUsername());
      out.write("\" \n");
      out.write("\t\t\t\t\t\t\t\t\thref=\"profile.jsp?profileView=");
out.print(event.getUsername());
      out.write("\"><img   \n");
      out.write("\t\t\t\t\t\t\t\t\tid=\"profile_image\" src=");
out.print(currentUser.getImage());
      out.write(" alt = \"Profile Image Not Found\"></a>\n");
      out.write("\t\t\t\t\t\t\t\t\t<a data-toggle=\"tooltip\" title=\"");
out.print(event.getAction().toLowerCase());
      out.write("\" ><img id=\"action_image\"src=\"");
out.print(session.getAttribute("ActionImage"));
      out.write("\"></a>\n");
      out.write("\t\t\t\t\t\t\t\t\t<a data-toggle=\"tooltip\" title=\"");
out.print(event.getMovie().getTitle());
      out.write("\" href=\"movie_page.jsp?movieView=");
out.print(event.getMovie().getTitle());
      out.write("\"><img id=\"movie_image\" \n");
      out.write("\t\t\t\t\t\t\t\t\tsrc=\"");
out.print(event.getMovie().getImage()); 
      out.write("\"></a>\n");
      out.write("\t\t\t\t\t\t\t\t</td> \n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t\t");
      out.write("\n");
      out.write("\t\t\t\t\t");
 }
				} 
      out.write("\n");
      out.write("\t\t\t</tbody>\n");
      out.write("\t\t</table>\n");
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