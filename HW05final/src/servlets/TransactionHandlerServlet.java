package servlets;


import java.io.IOException;
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
 * Servlet implementation class TransactionHandlerServlet
 */
@WebServlet("/TransactionHandlerServlet")
public class TransactionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.sendRedirect("stockHoldingsServlet.java"); transactionDetails.jsp
		
		HttpSession session = request.getSession();	
		
		boolean beanCheck = session.getAttribute("bean") != null;
		
		if (beanCheck == false || !utilities.DataCheck.IfReferredBy(request, "TransactionDetails.jsp")){
			response.sendRedirect("login.jsp");
			return;
		} else {
		
			SessionBean sessionInfo = (SessionBean) session.getAttribute("bean");
				
			String stockSymbol = request.getParameter("stockSymbol");
			String stockValue = request.getParameter("stockValue");
			String stockChange = request.getParameter("stockChange");
			String stockOwned = request.getParameter("stockOwned");		
			String stockAction = request.getParameter("stockAction");
			String quantity = request.getParameter("quantity");
			
			
			Integer userKey = Integer.parseInt(sessionInfo.getUserPrimaryKey());
			
			if(DataCheck.isNumeric(quantity) == false){
				sessionInfo.setMessage("Please insert a valid number for quantity");
				sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, stockOwned);
			} else{
				if(model.User_StockInfo.isUserStockInDB(userKey, stockSymbol) == false){
					sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, quantity);
					model.User_StockInfo.addUserStock(userKey, stockSymbol, Integer.parseInt(quantity));
				} else {
					
					Map<String, String> stocks = model.User_StockInfo.getUserStock(String.valueOf(userKey));
			        
			        
					for(Map.Entry<String, String> entry : stocks.entrySet()){
						if(stockSymbol == entry.getKey()){
	
							stockOwned = entry.getValue();
						}
						
					}
					
	//				for (HashMap.Entry<String, String[]> entry : sessionInfo.getStockHoldings()) {
	//		            if(stockSymbol == entry.getKey()){
	//			            String numbersArray[] = entry.getValue();
	//			            stockOwned = numbersArray[2];
	//		            }
	//		            
	//				}
					System.out.println(stockOwned);
			
					if(DataCheck.NullOrEmpty(quantity)){
				
						quantity = "0";
						//sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, stockOwned);
						Integer additionResult = Integer.parseInt(stockOwned) + Integer.parseInt(quantity);
						model.User_StockInfo.updateUserStock(userKey, stockSymbol, stockValue, stockChange, additionResult);
						sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, String.valueOf(additionResult));
					
					} else if (stockAction.equals("buy")) {
						if (Integer.parseInt(quantity) < 0) {
							sessionInfo.setMessage("Sorry. You can't buy a negative amount of Stock");
							sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, stockOwned);
						}else {
							
							Integer additionResult = Integer.parseInt(stockOwned) + Integer.parseInt(quantity);
							model.User_StockInfo.updateUserStock(userKey, stockSymbol, stockValue, stockChange, additionResult);
							sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, String.valueOf(additionResult));
						}
					} else if (stockAction.equals("sell")) {
						if (Integer.parseInt(quantity) > Integer.parseInt(stockOwned)) {
							sessionInfo.setMessage("Sorry. You can't sell more stock than you own");
							sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, stockOwned);
						}else {
							
							Integer subtractionResult = Integer.parseInt(stockOwned) - Integer.parseInt(quantity);
							model.User_StockInfo.updateUserStock(userKey, stockSymbol, stockValue, stockChange, subtractionResult);
							sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, String.valueOf(subtractionResult));
						} 
					} else {
						sessionInfo.setMessage("Not sure what you did but try again");
					}
					
				}
				String usersKey = Integer.toString(userKey);
				utilities.createForm.buildForm(sessionInfo, usersKey);
				
			}
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
