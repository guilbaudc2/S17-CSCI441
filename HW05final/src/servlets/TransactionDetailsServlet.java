package servlets;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SessionBean;
import utilities.DataCheck;
import utilities.Redirects;

/**
 * Servlet implementation class TransactionDetailsServlet
 */
@WebServlet("/TransactionDetailsServlet")
public class TransactionDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		
		if (null == session) {
			response.sendRedirect("login.jsp");
			return;
		} else {
			boolean beanCheck = session.getAttribute("bean") != null;
		
			if (beanCheck == false){
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
				
				if(DataCheck.isNumeric(quantity) == false){
					sessionInfo.setMessage("Please insert a valid number for quantity");
					sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, stockOwned);
					response.sendRedirect("stockHoldings.jsp");
				} else if (stockAction.equals("buy") && Integer.parseInt(quantity) < 0) {
					sessionInfo.setMessage("Sorry. You can't buy a negative amount of Stock");
					sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, stockOwned);
					response.sendRedirect("stockHoldings.jsp");
				}else if (stockAction.equals("sell") && Integer.parseInt(quantity) > Integer.parseInt(stockOwned)) {
					sessionInfo.setMessage("Sorry. You can't sell more stock than you own");
					sessionInfo.setStockHoldings(stockSymbol, stockValue, stockChange, stockOwned);
					response.sendRedirect("stockHoldings.jsp");
				} else{
				
					utilities.createForm.transactionDetailsForm(sessionInfo, stockSymbol, stockValue, stockAction, quantity, stockOwned, stockChange);
					response.sendRedirect("TransactionDetails.jsp");
				}
			
			}
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
