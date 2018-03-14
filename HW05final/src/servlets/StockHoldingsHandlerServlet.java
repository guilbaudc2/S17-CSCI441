package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
import utilities.Redirects;

/**
 * Servlet implementation class StockHoldingsHandlerServlet
 */
@WebServlet("/StockHoldingsHandlerServlet")
public class StockHoldingsHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockHoldingsHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();	
		
		boolean beanCheck = session.getAttribute("bean") != null;
		
		if (beanCheck == false){
//			|| !utilities.DataCheck.IfReferredBy(request, "LoginHandlerServlet") || !utilities.DataCheck.IfReferredBy(request, "RegistrationHandlerServlet") || !utilities.DataCheck.IfReferredBy(request, "TransactionHandlerServlet")
			response.sendRedirect("login.jsp");
			return;
		} else {
			
			SessionBean sessionInfo = (SessionBean) session.getAttribute("bean");
					
			String userKey = sessionInfo.getUserPrimaryKey();
			
	        String stockPrice = "";
	        String stockChange = "";
	        Map<String, String> hash = model.User_StockInfo.getUserStock(userKey);
	        
	        StockYahooMapping stockYahooMapping = new StockYahooMapping();
	        StockDownloader stockDownloader = new StockDownloader(stockYahooMapping);
	        StockGetSet stock;
	        
			for(Map.Entry<String, String> entry : hash.entrySet()){
				
					stock = stockDownloader.Download(entry.getKey());
					
					stockPrice = String.valueOf(stock.getLastTrade());
					stockChange = String.valueOf(stock.getChange());
					
					 
				
				//System.out.println("Symb: "+ entry.getKey() + " Price: "+   stockPrice + " Change: "+   stockChange + " Amt: "+  entry.getValue());
				sessionInfo.setStockHoldings(entry.getKey(), stockPrice, stockChange, entry.getValue());
			
			}


//Read more: http://www.java67.com/2013/08/best-way-to-iterate-over-each-entry-in.html#ixzz4dC1FtQZL
		
//			//while (model.User_StockInfo.getUserStock(userKey)){
//				userStockAmtFromDB = model.User_StockInfo.getUserStock(userKey).getString("stockAmount");
//			 	userStockSymbFromDB = model.User_StockInfo.getUserStock(userKey).getString("stockSymbol");

		
			utilities.createForm.buildForm(sessionInfo, userKey);
			
			response.sendRedirect("stockHoldings.jsp");
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
