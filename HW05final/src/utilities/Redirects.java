package utilities;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Redirects {

    public static void gotoPage1JSP(HttpServletResponse response) {
        try {
            response.sendRedirect("page1.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for page1.jsp did not work", ex);
        }
    }
    
    public static void goToPage2ControllerServlet(HttpServletResponse response) {
        try {
            response.sendRedirect("Page2ControllerServlet");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for Page2ControllerServlet servlet did not work", ex);
        }
    }
    public static void gotoPage2JSP(HttpServletResponse response) {
        try {
            response.sendRedirect("page2.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for page2.jsp did not work", ex);
        }
    }
    
    public static void gotoLogin(HttpServletResponse response) {
        try {
            response.sendRedirect("login.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for login.jsp did not work", ex);
        }
    }  
    
    public static void goToStockHoldingsJSP(HttpServletResponse response) {
        try {
            response.sendRedirect("stockHoldings.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for stockHoldings did not work", ex);
        }
    }

    public static void gotoTransactionDetailsJSP(HttpServletResponse response) {
        try {
            response.sendRedirect("transactionDetails.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for transactionDetails did not work", ex);
        }
    }
    
    public static void gotoRegistrationJSP(HttpServletResponse response) {
        try {
            response.sendRedirect("registration.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for registration did not work", ex);
        }
    }

    
    
}
