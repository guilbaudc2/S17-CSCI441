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

/**
 * Servlet implementation class LoginHandlerServlet
 */
@WebServlet("/LoginHandlerServlet")
public class LoginHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandlerServlet() {
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
		
		
		if (DataCheck.NullOrEmpty(userName) || DataCheck.NullOrEmpty(password)){
			sessionInfo.setMessage("*****   Do not leave fields empty   *****");
			response.sendRedirect("login.jsp");	
//		} else if (userName.equalsIgnoreCase("pat") && password.equalsIgnoreCase("pat")){
//			sessionInfo.setUserName(userName);
//			sessionInfo.setPassword(password);	
//			sessionInfo.setMessage("");
//			response.sendRedirect("StockHoldingsHandlerServlet");
//			return;
//		} else if (userName.equalsIgnoreCase("\"bob\"") && password.equalsIgnoreCase("\"bob\"")){
//			sessionInfo.setUserName(userName);
//			sessionInfo.setPassword(password);	
//			sessionInfo.setMessage("");
//			response.sendRedirect("StockHoldingsHandlerServlet");
//			return;
		} else if (model.User_Login_Register_Info.isUserInDB(userName) == true && password.equalsIgnoreCase(model.User_Login_Register_Info.getPassword(userName))){
			
			sessionInfo.setUserName(userName);
			sessionInfo.setPassword(model.User_Login_Register_Info.getPassword(userName));
			String primaryKey = (model.User_Login_Register_Info.getPrimaryKey(userName)).toString();
			sessionInfo.setUserPrimaryKey(primaryKey);

			response.sendRedirect("StockHoldingsHandlerServlet");
			return;
		}else {
			sessionInfo.setMessage("*****   Invalid Login   *****");
			response.sendRedirect("login.jsp");
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
