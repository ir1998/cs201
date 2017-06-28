/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M15
 * Generated at: 2017-04-19 15:44:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import data.Client;
import data.User;
import data.Stock;
import java.util.List;

public final class mainPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("data.User");
    _jspx_imports_classes.add("data.Client");
    _jspx_imports_classes.add("data.Stock");
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\" >\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("\t<meta charset=\"UTF-8\">\n");
      out.write("\t<title>fortune - gamify investing</title>\n");
      out.write("\t<link rel=\"stylesheet\" href=\"http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css\">\n");
      out.write("\t<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"mdstyle.css\">\n");
      out.write("\n");
      out.write("</head>\n");
Client local = (Client)request.getSession().getAttribute("client"); 
      out.write("\n");
      out.write("<script>\n");
      out.write("function loadStocks(){\n");
      out.write("\tvar xhttp = new XMLHttpRequest();\n");
      out.write("\txhttp.open(\"GET\",\n");
      out.write("\t\t\t\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/StockUpdateServlet?\",\n");
      out.write("\t\t\tfalse);\n");
      out.write("\txhttp.send();\n");
      out.write("\t");
if (local.isGuest()) {
      out.write("\n");
      out.write("\tvar stockData = JSON.parse(xhttp.responseText);\n");
      out.write("\n");
      out.write("\tfor (var i=0; i<stockData.length; i++)\n");
      out.write("\t{\n");
      out.write("\t  var stock = JSON.parse(stockData[i]);\n");
      out.write("\t  document.getElementById(\"stockSymbol\"+stock.symbol).innerHTML = stock.symbol;\n");
      out.write("\t  document.getElementById(\"stockName\"+stock.symbol).innerHTML = stock.name;\n");
      out.write("\t  document.getElementById(\"stockPrice\"+stock.symbol).innerHTML = stock.price;\n");
      out.write("\t  document.getElementById(\"stockChange\"+stock.symbol).innerHTML = stock.growth.toFixed(2);\n");
      out.write("\t  document.getElementById(\"dayhigh\"+stock.symbol).innerHTML = stock.daylow;\n");
      out.write("\t  document.getElementById(\"daylow\"+stock.symbol).innerHTML = stock.dayhigh;\n");
      out.write("\t  document.getElementById(\"week52high\"+stock.symbol).innerHTML = stock.week52low;\n");
      out.write("\t  document.getElementById(\"week52low\"+stock.symbol).innerHTML = stock.week52high;\n");
      out.write("\t  document.getElementById(\"movingav50day\"+stock.symbol).innerHTML = stock.movingav50day;\n");
      out.write("\t  document.getElementById(\"image\"+stock.symbol).src = \"https://chart.finance.yahoo.com/z?s=\"+stock.symbol+\"&t=1d&q=l&l=on\"; \n");
      out.write("\t}\n");
      out.write("\treturn true;\n");
      out.write("\t\n");
      out.write("\t");
} else {
      out.write("\n");
      out.write("\tvar stockData = JSON.parse(xhttp.responseText);\n");
      out.write("\tvar ownedStocks = stockData[0];\n");
      out.write("\tvar allStocks = stockData[1];\n");
      out.write("\t\n");
      out.write(" \tfor (var i=0; i<ownedStocks.owned.length; i++)\n");
      out.write("\t{\n");
      out.write("\t\t  var stock = JSON.parse(ownedStocks.owned[i]);\n");
      out.write("\t\t  document.getElementById(\"ownedStockSymbol\"+stock.symbol).innerHTML = stock.symbol;\n");
      out.write("\t\t  document.getElementById(\"ownedStockName\"+stock.symbol).innerHTML = stock.name;\n");
      out.write("\t\t  document.getElementById(\"ownedStockPrice\"+stock.symbol).innerHTML = stock.price;\n");
      out.write("\t\t  document.getElementById(\"ownedStockChange\"+stock.symbol).innerHTML = stock.growth.toFixed(2);\n");
      out.write("\t\t  document.getElementById(\"quantity\"+stock.symbol).innerHTML = stock.amountOwned;\n");
      out.write("\t\t  document.getElementById(\"ownedDayHigh\"+stock.symbol).innerHTML = stock.daylow;\n");
      out.write("\t\t  document.getElementById(\"ownedDayLow\"+stock.symbol).innerHTML = stock.dayhigh;\n");
      out.write("\t\t  document.getElementById(\"ownedWeek52low\"+stock.symbol).innerHTML = stock.week52low;\n");
      out.write("\t\t  document.getElementById(\"ownedWeek52high\"+stock.symbol).innerHTML = stock.week52high;\n");
      out.write("\t\t  document.getElementById(\"ownedMovingAv50day\"+stock.symbol).innerHTML = stock.movingav50day;\n");
      out.write("\t\t  document.getElementById(\"ownedImage\"+stock.symbol).src = \"https://chart.finance.yahoo.com/z?s=\"+stock.symbol+\"&t=1d&q=l&l=on\";\n");
      out.write("\t}  \n");
      out.write("\t\n");
      out.write("\tfor (var i=0; i<allStocks.all.length; i++)\n");
      out.write("\t{\n");
      out.write("\t  var stock = JSON.parse(allStocks.all[i]);\n");
      out.write("\t  document.getElementById(\"stockSymbol\"+stock.symbol).innerHTML = stock.symbol;\n");
      out.write("\t  document.getElementById(\"stockName\"+stock.symbol).innerHTML = stock.name;\n");
      out.write("\t  document.getElementById(\"stockPrice\"+stock.symbol).innerHTML = stock.price;\n");
      out.write("\t  document.getElementById(\"stockChange\"+stock.symbol).innerHTML = stock.growth.toFixed(2);\n");
      out.write("\t  document.getElementById(\"dayhigh\"+stock.symbol).innerHTML = stock.daylow;\n");
      out.write("\t  document.getElementById(\"daylow\"+stock.symbol).innerHTML = stock.dayhigh;\n");
      out.write("\t  document.getElementById(\"week52low\"+stock.symbol).innerHTML = stock.week52low;\n");
      out.write("\t  document.getElementById(\"week52high\"+stock.symbol).innerHTML = stock.week52high;\n");
      out.write("\t  document.getElementById(\"movingav50day\"+stock.symbol).innerHTML = stock.movingav50day;\n");
      out.write("\t  document.getElementById(\"image\"+stock.symbol).src = \"https://chart.finance.yahoo.com/z?s=\"+stock.symbol+\"&t=1d&q=l&l=on\"; \n");
      out.write("\t}\n");
      out.write("\treturn true;\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t");
}
      out.write("\n");
      out.write("};\n");
      out.write("\n");
      out.write("setInterval(function loadStocks(){\n");
      out.write("\tvar xhttp = new XMLHttpRequest();\n");
      out.write("\txhttp.open(\"GET\",\n");
      out.write("\t\t\t\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/StockUpdateServlet?\",\n");
      out.write("\t\t\tfalse);\n");
      out.write("\txhttp.send();\n");
      out.write("\t");
if (local.isGuest()) {
      out.write("\n");
      out.write("\tvar stockData = JSON.parse(xhttp.responseText);\n");
      out.write("\n");
      out.write("\tfor (var i=0; i<stockData.length; i++)\n");
      out.write("\t{\n");
      out.write("\t  var stock = JSON.parse(stockData[i]);\n");
      out.write("\t  document.getElementById(\"stockSymbol\"+stock.symbol).innerHTML = stock.symbol;\n");
      out.write("\t  document.getElementById(\"stockName\"+stock.symbol).innerHTML = stock.name;\n");
      out.write("\t  document.getElementById(\"stockPrice\"+stock.symbol).innerHTML = stock.price;\n");
      out.write("\t  document.getElementById(\"stockChange\"+stock.symbol).innerHTML = stock.growth.toFixed(2);\n");
      out.write("\t  document.getElementById(\"dayhigh\"+stock.symbol).innerHTML = stock.daylow;\n");
      out.write("\t  document.getElementById(\"daylow\"+stock.symbol).innerHTML = stock.dayhigh;\n");
      out.write("\t  document.getElementById(\"week52high\"+stock.symbol).innerHTML = stock.week52low;\n");
      out.write("\t  document.getElementById(\"week52low\"+stock.symbol).innerHTML = stock.week52high;\n");
      out.write("\t  document.getElementById(\"movingav50day\"+stock.symbol).innerHTML = stock.movingav50day;\n");
      out.write("\t  document.getElementById(\"image\"+stock.symbol).src = \"https://chart.finance.yahoo.com/z?s=\"+stock.symbol+\"&t=1d&q=l&l=on\"; \n");
      out.write("\t}\n");
      out.write("\treturn true;\n");
      out.write("\t\n");
      out.write("\t");
} else {
      out.write("\n");
      out.write("\tvar stockData = JSON.parse(xhttp.responseText);\n");
      out.write("\tvar ownedStocks = stockData[0];\n");
      out.write("\tvar allStocks = stockData[1];\n");
      out.write("\t\n");
      out.write(" \tfor (var i=0; i<ownedStocks.owned.length; i++)\n");
      out.write("\t{\n");
      out.write(" \t\t  var stock = JSON.parse(ownedStocks.owned[i]);\n");
      out.write(" \t\t  document.getElementById(\"ownedStockSymbol\"+stock.symbol).innerHTML = stock.symbol;\n");
      out.write(" \t\t  document.getElementById(\"ownedStockName\"+stock.symbol).innerHTML = stock.name;\n");
      out.write(" \t\t  document.getElementById(\"ownedStockPrice\"+stock.symbol).innerHTML = stock.price;\n");
      out.write(" \t\t  document.getElementById(\"ownedStockChange\"+stock.symbol).innerHTML = stock.growth.toFixed(2);\n");
      out.write(" \t\t  document.getElementById(\"quantity\"+stock.symbol).innerHTML = stock.amountOwned;\n");
      out.write(" \t\t  document.getElementById(\"ownedDayHigh\"+stock.symbol).innerHTML = stock.daylow;\n");
      out.write(" \t\t  document.getElementById(\"ownedDayLow\"+stock.symbol).innerHTML = stock.dayhigh;\n");
      out.write(" \t\t  document.getElementById(\"ownedWeek52high\"+stock.symbol).innerHTML = stock.week52low;\n");
      out.write(" \t\t  document.getElementById(\"ownedWeek52low\"+stock.symbol).innerHTML = stock.week52high;\n");
      out.write(" \t\t  document.getElementById(\"ownedMovingAv50day\"+stock.symbol).innerHTML = stock.movingav50day;\n");
      out.write(" \t\t  document.getElementById(\"ownedImage\"+stock.symbol).src = \"https://chart.finance.yahoo.com/z?s=\"+stock.symbol+\"&t=1d&q=l&l=on\";\n");
      out.write(" \t} \n");
      out.write("\t\n");
      out.write("\tfor (var i=0; i<allStocks.all.length; i++)\n");
      out.write("\t{\n");
      out.write("\t  var stock = JSON.parse(allStocks.all[i]);\n");
      out.write("\t  document.getElementById(\"stockSymbol\"+stock.symbol).innerHTML = stock.symbol;\n");
      out.write("\t  document.getElementById(\"stockName\"+stock.symbol).innerHTML = stock.name;\n");
      out.write("\t  document.getElementById(\"stockPrice\"+stock.symbol).innerHTML = stock.price;\n");
      out.write("\t  document.getElementById(\"stockChange\"+stock.symbol).innerHTML = stock.growth.toFixed(2);\n");
      out.write("\t  document.getElementById(\"dayhigh\"+stock.symbol).innerHTML = stock.daylow;\n");
      out.write("\t  document.getElementById(\"daylow\"+stock.symbol).innerHTML = stock.dayhigh;\n");
      out.write("\t  document.getElementById(\"week52high\"+stock.symbol).innerHTML = stock.week52low;\n");
      out.write("\t  document.getElementById(\"week52low\"+stock.symbol).innerHTML = stock.week52high;\n");
      out.write("\t  document.getElementById(\"movingav50day\"+stock.symbol).innerHTML = stock.movingav50day;\n");
      out.write("\t  document.getElementById(\"image\"+stock.symbol).src = \"https://chart.finance.yahoo.com/z?s=\"+stock.symbol+\"&t=1d&q=l&l=on\"; \n");
      out.write("\t}\n");
      out.write("\treturn true;\n");
      out.write("\t");
}
      out.write("\n");
      out.write("}, 60000);\n");
      out.write("</script>\n");
      out.write("<script>\n");
      out.write("showPopup = function(stockID, isBuy) {\n");
      out.write("\tvar stockTicker = String(stockID);\n");
      out.write("\tconsole.log(stockTicker); \n");
      out.write("\tconsole.log(String(stockID));\n");
      out.write("    var quantity = prompt(\"Please enter amount\", \"0\");\n");
      out.write("    console.log(quantity);\n");
      out.write("    if (quantity > 0) {\n");
      out.write("    \tvar xhttp = new XMLHttpRequest();\n");
      out.write("    \txhttp.open(\"GET\",\n");
      out.write("    \t\t\t\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/TransactionServlet?amount=\"\n");
      out.write("    \t\t\t\t\t+ quantity + \n");
      out.write("    \t\t\t\t\t\"&ticker=\" + String(stockTicker),\n");
      out.write("    \t\t\tfalse);\n");
      out.write("    \txhttp.send();\n");
      out.write("    \tsetTimeout(location.reload(), 1500);\n");
      out.write("    }\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("<body onload=\"loadStocks()\" style=\"font-family: garamond;\" id=\"grad\"   ng-app=\"InvestCS\" ng-controller=\"mainPageController\" ng-cloak>\n");
      out.write("\t<div layout=\"column\">\n");
      out.write("\t\t<md-sidenav md-component-id=\"left\" class=\"md-sidenav-left\">\n");
      out.write("\t\t\t<md-list>\n");
      out.write("\t\t\t\t<md-list-item>\n");
      out.write("\t\t\t\t\t<md-button class=\"nav-item\" href=\"mainPage.jsp\">\n");
      out.write("\t\t\t\t\t\t<h3>Main Page</h3>\n");
      out.write("\t\t\t\t\t</md-button>\n");
      out.write("\t\t\t\t</md-list-item>\n");
      out.write("\t\t\t\t<md-list-item>\n");
      out.write("\t\t\t\t\t<md-button class=\"nav-item\" href=\"history.jsp\">\n");
      out.write("\t\t\t\t\t\t<h3>History</h3>\n");
      out.write("\t\t\t\t\t</md-button>\n");
      out.write("\t\t\t\t</md-list-item>\n");
      out.write("\t\t\t\t<md-list-item>\n");
      out.write("\t\t\t\t\t<md-button class=\"nav-item\" href=\"profile.jsp\">\n");
      out.write("\t\t\t\t\t\t<h3>Profile</h3>\n");
      out.write("\t\t\t\t\t</md-button>\n");
      out.write("\t\t\t\t</md-list-item>\n");
      out.write("\t\t\t</md-list>\n");
      out.write("\t\t</md-sidenav>\n");
      out.write("\t\t<md-toolbar class=\"siteToolbar\">\n");
      out.write("\t\t\t<div class=\"md-toolbar-tools\">\n");
      out.write("\t\t\t\t<md-button class=\"siteButton\" ng-click=\"openLeftMenu()\">\n");
      out.write("\t\t\t\t\t<md-icon ng-bind=\"'menu'\"></md-icon>\n");
      out.write("\t\t\t\t</md-button>\n");
      out.write("\t\t\t\t<h2 align=\"center\" flex md-truncate>fortune - gamify investing</h2>\n");
      out.write("\t\t\t\t<md-button class=\"siteButton\" href=\"profile.jsp\">\n");
      out.write("\t\t\t\t\t<md-icon ng-bind=\"'person'\"></md-icon>\n");
      out.write("\t\t\t\t\tProfile\n");
      out.write("\t\t\t\t</md-button>\n");
      out.write("\t\t\t\t<md-button class=\"siteButton\" href=\"login.jsp\">\n");
      out.write("\t\t\t\t\t<md-icon ng-bind=\"'exit_to_app'\"></md-icon>\n");
      out.write("\t\t\t\t\tLogout\n");
      out.write("\t\t\t\t</md-button>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</md-toolbar>\n");
      out.write("\t\t<md-content layout=\"row\">\n");
      out.write("\t\t\t<div flex=\"33\" id=\"profileSummary\" layout=\"column\">\n");
      out.write("\t\t\t\t<div id=\"formerror\"></div>\n");
      out.write("\t\t\t\t<h2 class=\"center\">Profile Summary</h2>\n");
      out.write("\t\t\t\t<md-card id=\"profileCard\">\n");
      out.write("\t\t\t\t\t<img id=\"profilePicture\" src=\"http://gurucul.com/wp-content/uploads/2015/01/default-user-icon-profile.png\" width=\"200px\">\n");
      out.write("\t\t\t\t\t<div layout=\"column\">\n");
      out.write("\t\t\t\t\t\t<h2 class=\"profileInfo\">");
      out.print(local.user.getFname()+ " " +local.user.getLname());
      out.write("</h2>\n");
      out.write("\t\t\t\t\t\t<h4 class=\"profileInfo\">");
      out.print(local.user.getEmail() );
      out.write("</h4>\n");
      out.write("\t\t\t\t\t\t<h4 class=\"profileInfo\">$");
      out.print(local.user.getBalance() );
      out.write("</h4>\t\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</md-card>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div flex=\"66\" id=\"activitySummary\" layout=\"column\">\n");
      out.write("\t\t\t\t<div class=\"stocksFollowing\">\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<h2 class=\"center\" ng-if=\"");
      out.print(!local.isGuest());
      out.write("\">Stocks You Own</h2>\n");
      out.write("\t\t\t\t\t");

					List<Stock> owned = (List<Stock>)request.getSession().getAttribute("owned");
					 for (int i=0; i < owned.size(); i++) {
					 
      out.write("\n");
      out.write("\t\t\t      \t</md-card>\n");
      out.write("\t\t\t\t\t <md-card layout=\"row\" flex ng-if=\"");
      out.print(!local.isGuest());
      out.write("\">\n");
      out.write("\t\t\t        \t<md-card-title>\n");
      out.write("\t\t\t          \t\t<md-card-title-text layout=\"column\">\n");
      out.write("\t\t\t  \n");
      out.write("\t\t\t            \t\t<h3>Stock: <span id=\"ownedStockSymbol");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span> (<span id=\"ownedStockName");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span>)\n");
      out.write("\t\t          \t\t\t\t</h3>\n");
      out.write("\t\t          \t\t\t\t<span flex></span>\n");
      out.write("\t\t          \t\t\t\t<h4>Value: $<span id=\"ownedStockPrice");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span>     Change: <span id=\"ownedStockChange");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span>%</h4>\n");
      out.write("\t\t          \t\t\t\t<span flex></span>\n");
      out.write("\t\t          \t\t\t\t<span flex></span> \n");
      out.write("\t\t          \t\t\t\t<h4>Owned: <span id=\"quantity");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span></h4>\n");
      out.write("\t\t          \t\t\t\t<h4>Day Low: $<span id=\"ownedDayLow");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span>\t\tDay High: $<span id=\"ownedDayHigh");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span></h4>\n");
      out.write("\t\t          \t\t\t\t<h4>Year Low: $<span id=\"ownedWeek52low");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span>\t\tYear High: $<span id=\"ownedWeek52high");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span></h4>\n");
      out.write("\t\t\t          \t\t\t<h4>50 Day Moving Average: $<span id=\"ownedMovingAv50day");
      out.print(owned.get(i).getSymbol());
      out.write("\"></span></h4>\n");
      out.write("\t\t\t          \t\t\t<span flex><img id=\"ownedImage");
      out.print(owned.get(i).getSymbol());
      out.write("\" src=\"\" width=\"350\"></span>\n");
      out.write("\t\t\t          \t\t</md-card-title-text>\n");
      out.write("\t\t\t        \t</md-card-title>\n");
      out.write("\t\t\t        \t<md-card-actions layout=\"column\" layout-align=\"top\">\n");
      out.write("\t\t\t        \t\t<div class=\"actionRow\" layout=\"row\">\n");
      out.write("\t\t\t        \t\t\t<md-button class=\"siteButton\" id=\"BUY");
      out.print(owned.get(i).getSymbol());
      out.write("\" ng-if=\"");
      out.print(!local.isGuest());
      out.write("\" onclick=\"showPopup(this.id, true)\">Buy</md-button>\n");
      out.write("\t\t\t        \t\t</div>\n");
      out.write("\t\t\t        \t\t<div class=\"actionRow\" layout=\"row\">\n");
      out.write("\t\t\t        \t\t\t<md-button class=\"siteButton\" id=\"SELL");
      out.print(owned.get(i).getSymbol());
      out.write("\" ng-if=\"");
      out.print(!local.isGuest());
      out.write("\" onclick=\"showPopup(this.id, false)\">Sell</md-button>\n");
      out.write("\t\t\t        \t\t</div>\n");
      out.write("\t\t\t          \t\t\n");
      out.write("\t\t\t          \t\t\n");
      out.write("\t\t\t        \t</md-card-actions>\n");
      out.write("\t\t\t      \t</md-card>\n");
      out.write("\t\t\t\t\t ");

					 }
					 
      out.write("\n");
      out.write("\t\t\t\t\t \n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"stocksNotFollowing\">\n");
      out.write("\t\t\t\t\t<h2 class=\"center\">Other Stocks</h2>\n");
      out.write("\t\t\t\t\t");

					List<Stock> other = (List<Stock>)request.getSession().getAttribute("other");
					 for (int i=0; i < other.size(); i++) {
					 
      out.write("\n");
      out.write("\t\t\t\t\t<md-card layout=\"row\" flex>\n");
      out.write("\t\t\t        \t<md-card-title>\n");
      out.write("\t\t\t          \t\t<md-card-title-text layout=\"column\">\n");
      out.write("\n");
      out.write("\t\t          \t\t\t\t<h3>Stock: <span id=\"stockSymbol");
      out.print(other.get(i).getSymbol());
      out.write("\"></span> (<span id=\"stockName");
      out.print(other.get(i).getSymbol());
      out.write("\"></span>)\n");
      out.write("\t\t          \t\t\t\t</h3>\n");
      out.write("\t\t          \t\t\t\t<span flex></span>\n");
      out.write("\t\t          \t\t\t\t<h4>Value: $<span id=\"stockPrice");
      out.print(other.get(i).getSymbol());
      out.write("\"></span>     Change: <span id=\"stockChange");
      out.print(other.get(i).getSymbol());
      out.write("\"></span>%</h4>\n");
      out.write("\t\t          \t\t\t\t<h4>Day Low: $<span id=\"daylow");
      out.print(other.get(i).getSymbol());
      out.write("\"></span>\t\tDay High: $<span id=\"dayhigh");
      out.print(other.get(i).getSymbol());
      out.write("\"></span></h4>\n");
      out.write("\t\t          \t\t\t\t<h4>Year Low: $<span id=\"week52low");
      out.print(other.get(i).getSymbol());
      out.write("\"></span>\t\tYear High: $<span id=\"week52high");
      out.print(other.get(i).getSymbol());
      out.write("\"></span></h4>\n");
      out.write("\t\t\t          \t\t\t<h4>50 Day Moving Average: $<span id=\"movingav50day");
      out.print(other.get(i).getSymbol());
      out.write("\"></span></h4>\n");
      out.write("\t\t\t          \t\t\t<span flex><img id=\"image");
      out.print(other.get(i).getSymbol());
      out.write("\" src=\"\" width=\"350\"></span>\n");
      out.write("\t\t          \t\t\t\t\n");
      out.write("\t\t          \t\t\t\t\n");
      out.write("\t\t\t          \t\t</md-card-title-text>\n");
      out.write("\t\t\t        \t</md-card-title>\n");
      out.write("\t\t\t        \t<md-card-actions layout=\"column\" layout-align=\"top\">\n");
      out.write("\t\t\t        \t\t<div class=\"actionRow\" layout=\"row\">\n");
      out.write("\t\t\t        \t\t\t<md-button class=\"siteButton\" id=\"BUY");
      out.print(other.get(i).getSymbol());
      out.write("\" ng-if=\"");
      out.print(!local.isGuest());
      out.write("\" onclick=\"showPopup(this.id, true)\">Buy</md-button>\n");
      out.write("\t\t\t        \t\t</div>\n");
      out.write("\t\t\t        \t\t<div class=\"actionRow\" layout=\"row\">\n");
      out.write("\t\t\t        \t\t\t<md-button class=\"siteButton\" id=\"SELL");
      out.print(other.get(i).getSymbol());
      out.write("\" ng-if=\"");
      out.print(!local.isGuest());
      out.write("\" onclick=\"showPopup(this.id, false)\">Sell</md-button>\n");
      out.write("\t\t\t        \t\t</div>\n");
      out.write("\t\t\t          \t\t\n");
      out.write("\t\t\t          \t\t\n");
      out.write("\t\t\t        \t</md-card-actions>\n");
      out.write("\t\t\t      \t</md-card>\n");
      out.write("\t\t\t      \t ");

					 }
					 
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t</md-content>\n");
      out.write("\t</div>\t\n");
      out.write("\t<!-- Angular Material requires Angular.js Libraries -->\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js\"></script>\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-animate.min.js\"></script>\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js\"></script>\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js\"></script>\n");
      out.write("\n");
      out.write("\t<!-- Angular Material Library -->\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.js\"></script>\n");
      out.write("\n");
      out.write("\t<!-- Your application bootstrap  -->\n");
      out.write("\t<script type=\"text/javascript\" src=\"app.js\"></script>\n");
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