
package model;



import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import beans.SessionBean;



public class User_StockInfo {
	
	public static boolean isUserStockInDB(Integer userKey, String stockSymb) {
    	
        Connection con = Database_Connect.Connect2LocalDB();
        Integer userStockKeyFromDB = 0;
        String userStockSymbFromDB = "";
        Integer userCount = 0;
        try {

            PreparedStatement count = con.prepareStatement("SELECT userPrimaryKey, stockSymbol FROM userstocktable");
            ResultSet rs = count.executeQuery();
            while (rs.next()){
            	userStockKeyFromDB = rs.getInt("userPrimaryKey");
            	userStockSymbFromDB = rs.getString("stockSymbol");
					if (userStockSymbFromDB.equalsIgnoreCase(stockSymb) && userStockKeyFromDB.equals(userKey)){
						userCount = 1;					
					}
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "; " + ex.getMessage(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (userCount == 0) {
            return false;
        } else {
            return true;
        }
    }

	public static void addUserStock(Integer userKey, String stockSymb, Integer stockAmt) {
    	Connection con = Database_Connect.Connect2LocalDB();
       
        
        try {
        	
            PreparedStatement prep = con.prepareStatement("INSERT INTO userstocktable (userPrimaryKey,stockSymbol,StockAmount) VALUES (?,?,?)");
            prep.setInt(1, userKey);
            prep.setString(2, stockSymb);
            prep.setInt(3, stockAmt);
            prep.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() + ".addUserCar ", ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	  


	    public static HashMap getUserStock(String userKey) {

	        Connection con = Database_Connect.Connect2LocalDB();
	        String userStockAmtFromDB = "";
	        String userStockSymbFromDB = "";
	        HashMap hm = new HashMap();
	        ResultSet rs = null;
	        //String data[];

	        try {
	            PreparedStatement prep = con.prepareStatement("SELECT stockSymbol, stockAmount FROM userstocktable WHERE userPrimaryKey = ?");
	            prep.setString(1, userKey);
	            
	            rs = prep.executeQuery();
	            //return rs;
	            while (rs.next()){
	            	userStockAmtFromDB = rs.getString("stockAmount");
	             	userStockSymbFromDB = rs.getString("stockSymbol");
	             	hm.put(userStockSymbFromDB, userStockAmtFromDB);
	             	
	            }
	            // 
	            	
	           // }
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, User_StockInfo.class.getName(), ex);
	        }
	        try {
	            con.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        //return userKey;
	        return hm;
	    }

	    
	    public static void updateUserStock(Integer userKey, String stockSymb, String stockVal, String stockCha, Integer stockAmt) {
	    	//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    		// TODO Auto-generated method stub
       
//	    		SessionBean sessionInfo = (SessionBean) session.getAttribute("bean");
	    		
	    		
	        Connection con = Database_Connect.Connect2LocalDB();
	        
	        
	        try {
	            PreparedStatement prep = con.prepareStatement("UPDATE userstocktable SET stockAmount = ? WHERE userPrimaryKey = ? AND stockSymbol = ?;");
	            prep.setInt(2, userKey);
	            prep.setString(3, stockSymb);
	            prep.setInt(1, stockAmt);
	            prep.executeUpdate();
	            
	           //sessionInfo.setStockHoldings(stockSymb, stockVal, stockCha, String.valueOf(stockAmt));
	  
	            
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, User_StockInfo.class.getName(), ex);
	        }
	        try {
	            con.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(User_StockInfo.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        //return userKey;
	    }
	    
	    
   
	    
	
}

