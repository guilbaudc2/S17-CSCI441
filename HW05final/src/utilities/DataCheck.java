package utilities;

import javax.servlet.http.HttpServletRequest;

public final class DataCheck {

	
	
	public static boolean NullOrEmpty(String input)
    {
        if(input==null||input.trim().equals(""))    //trim removes any whitespace the user might have entered
        {
            return true;                            //this states that nothing was entered into the form
        }
        else
        {
            return false;                           //this states that something was in the form
        }
    }
	
	public static boolean isNumeric(String input) {

	    try  
	    {  
	    	int num = Integer.parseInt(input); 
	    }  
	    catch(NumberFormatException nfe)  
	    {  
	      return false;  
	    }  
	    return true;  
	  }
	
//	public static boolean isNumeric(String input) {
//
//	    Integer decimalCount = 0;
//	    for (Integer i = 0; i < input.length(); i++) {
//	        switch (input.charAt(i)) {
//	            case '0':
//	            case '1':
//	            case '2':
//	            case '3':
//	            case '4':
//	            case '5':
//	            case '6':
//	            case '7':
//	            case '8':
//	            case '9':
//	                break;
//	            case '.':
//	                if (i == 0 || decimalCount > 0) {
//	                    return false;
//	                }
//	                decimalCount += 1;
//	                break;
//	            default:
//	                return false;
//	        }
//	    }
//	    return true;
//	}
	
   public static boolean IfReferredBy(HttpServletRequest request, String cameFrom)
    {
        if(request.getHeader("Referer")==null)
        {
            return false;
        }
        else
        {
            String Referer = (String)request.getHeader("Referer");
            if(Referer.contains(cameFrom))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
