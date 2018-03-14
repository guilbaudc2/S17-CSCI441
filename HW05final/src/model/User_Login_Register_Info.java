
package model;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class User_Login_Register_Info {

    
	
	 public static boolean isUserInDB(String userName) {
	    	
	        Connection con = Database_Connect.Connect2LocalDB();
	        String userFromDB = "";
	        int userCount = 0;
	        try {

	            PreparedStatement count = con.prepareStatement("SELECT userName FROM usertable");
	            ResultSet rs = count.executeQuery();
	            while (rs.next()){
	            	userFromDB = rs.getString("userName");
						if (userFromDB.equalsIgnoreCase(userName)){
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
	
	

	    public static void addUserName(String userName, String password) {
	    	Connection con = Database_Connect.Connect2LocalDB();
	       
	        
	        try {
	        	
	            PreparedStatement prep = con.prepareStatement("INSERT INTO usertable (userName,password) VALUES (?,?)");
	            prep.setString(1, userName);
	            prep.setString(2, password);
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



		public static String getPassword(String userName) {
			// TODO Auto-generated method stub
			Connection con = Database_Connect.Connect2LocalDB();
	       
	        String pw = "";
	        try {
	        	
	            PreparedStatement prep = con.prepareStatement("Select password FROM usertable where userName = ?");
	            prep.setString(1, userName);
	            ResultSet resultPW = prep.executeQuery();
	            resultPW.next();
	            pw = resultPW.getString(1);

	        } catch (SQLException ex) {
	            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName(), ex);
	        }
	        try {
	            con.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return pw;
	    } 

	    

	    public static Integer getPrimaryKey(String userName) {

	        Connection con = Database_Connect.Connect2LocalDB();
	        
	        int userKey = 0;

	        try {
	            PreparedStatement key = con.prepareStatement("SELECT PrimaryKey FROM usertable WHERE userName = ?");
	            key.setString(1, userName);
	            ResultSet resultUserKey = key.executeQuery();
	            resultUserKey.next();
	            userKey = resultUserKey.getInt(1);
	        } catch (SQLException ex) {
	            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName(), ex);
	        }
	        try {
	            con.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return userKey;
	    }

	
	    
	 
	    
	    
	
}

