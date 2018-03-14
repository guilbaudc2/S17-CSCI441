package beans;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class SessionBean {
	private String userName = "";
	private String password = "";
	private String message = "";
	private String userPrimaryKey = "";
	private String form;
	private String transactionForm;
	private HashMap<String, String[]> stockHoldings = new HashMap<String, String[]>();

	public String getUserName() {
		return(userName); 
	}
	
	public void setUserName(String newUserName) {
		userName = newUserName; 
	}
	
	public String getPassword() {
		return(password); 
	}
	
	public void setPassword(String newPassword) {
		password = newPassword; 
	}
	
	public String getMessage() {
		return(message); 
	}
	
	public void setMessage(String newMessage) {
		message = newMessage; 
	}
	
	public String getUserPrimaryKey() {
		return(userPrimaryKey); 
	}
	
	public void setUserPrimaryKey(String newUserPrimaryKey) {
		userPrimaryKey = newUserPrimaryKey; 
	}
	
	public String getForm()
    {
        return form;
    }
	
	public void setForm(String value)
    {
        this.form = value;
    }
	
	public String getTransactionForm()
    {
        return transactionForm;
    }
	
	public void setTransactionForm(String value)
    {
        this.transactionForm = value;
    }

    public Set<Entry<String, String[]>> getStockHoldings()
    {
    	Set<Entry<String, String[]>> set = stockHoldings.entrySet();
    	return set;
    }
    
    public void setStockHoldings(String argument1, String argument2a, String argument2b, String argument2c)
    {
        this.stockHoldings.put(argument1, new String[] {argument2a, argument2b, argument2c});
 
    }
	
}
