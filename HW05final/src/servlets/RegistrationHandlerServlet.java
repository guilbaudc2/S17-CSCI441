package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SessionBean;
import stockDownload.StockDownloader;
import stockDownload.StockGetSet;
import stockDownload.StockYahooMapping;
import utilities.DataCheck;

/**
 * Servlet implementation class RegistrationHandlerServlet
 */
@WebServlet("/RegistrationHandlerServlet")
public class RegistrationHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);	       
		SessionBean sessionInfo = (SessionBean) session.getAttribute("bean");
			
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPW = request.getParameter("confirmPW");
		
		
		if (DataCheck.NullOrEmpty(userName) || DataCheck.NullOrEmpty(password) || DataCheck.NullOrEmpty(confirmPW)){
			sessionInfo.setMessage("*****   Don't leave empty   *****");
			response.sendRedirect("registration.jsp");	
//		} else if (userName.equalsIgnoreCase("pat") || userName.equalsIgnoreCase("bob") || userName.equalsIgnoreCase("peg")){
//			sessionInfo.setMessage("*****   username (" + userName + ") taken   *****");
//			response.sendRedirect("registration.jsp");
//			return;
		} else if (!password.equals(confirmPW)){
			sessionInfo.setMessage("*****   Passwords don't match   *****");
			response.sendRedirect("registration.jsp");
			return;
		} else if (model.User_Login_Register_Info.isUserInDB(userName) == true){
			sessionInfo.setMessage("*****   username (" + userName + ") is in DB *****");
			response.sendRedirect("registration.jsp");
			return;
		} else {
			model.User_Login_Register_Info.addUserName(userName, password);
			sessionInfo.setUserName(userName);
			sessionInfo.setPassword(password);
			String primaryKey = (model.User_Login_Register_Info.getPrimaryKey(userName)).toString();
			sessionInfo.setUserPrimaryKey(primaryKey);
			
			Integer userKey = Integer.parseInt(primaryKey);
			
			StockYahooMapping stockYahooMapping = new StockYahooMapping();
		    StockDownloader stockDownloader = new StockDownloader(stockYahooMapping);
		    StockGetSet stock;
			
			model.User_StockInfo.addUserStock(userKey, "MSFT", 10);
			model.User_StockInfo.addUserStock(userKey, "GOOG", 10);
			model.User_StockInfo.addUserStock(userKey, "AAPL", 10);
			sessionInfo.setStockHoldings("MSFT", String.valueOf(stockDownloader.Download("MSFT").getLastTrade()), String.valueOf(stockDownloader.Download("MSFT").getChange()), "10");
			sessionInfo.setStockHoldings("AAPL", String.valueOf(stockDownloader.Download("AAPL").getLastTrade()), String.valueOf(stockDownloader.Download("AAPL").getChange()), "10");
			sessionInfo.setStockHoldings("GOOG", String.valueOf(stockDownloader.Download("GOOG").getLastTrade()), String.valueOf(stockDownloader.Download("GOOG").getChange()), "10");
			
			
			sessionInfo.setMessage("");
			response.sendRedirect("StockHoldingsHandlerServlet");
			
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
