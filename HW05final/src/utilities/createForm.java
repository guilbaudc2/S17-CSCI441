package utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import beans.SessionBean;
import stockDownload.StockDownloader;
import stockDownload.StockGetSet;
import stockDownload.StockYahooMapping;

public class createForm {

	public static void buildForm(SessionBean sessionInfo, String userKey){
		

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
				
				 
			
//			System.out.println("Symb: "+ entry.getKey() + " Price: "+   stockPrice + " Change: "+   stockChange + " Amt: "+  entry.getValue());
			sessionInfo.setStockHoldings(entry.getKey(), stockPrice, stockChange, entry.getValue());
			
		}
		String stockSymbol = "";
		String form = "";
		
//		StockYahooMapping stockYahooMapping = new StockYahooMapping();
//        StockDownloader stockDownloader = new StockDownloader(stockYahooMapping);
//        StockGetSet stock;
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
    
	
		//System.out.println(sessionInfo.getStockHoldings());		
		for (HashMap.Entry<String, String[]> entry : sessionInfo.getStockHoldings()) {
            stockSymbol = entry.getKey();
            String numbersArray[] = entry.getValue();
            
            if(Integer.parseInt(numbersArray[2]) != 0){
//           System.out.println(stockSymbol + " " + numbersArray[0] + " " + numbersArray[1]+ " " + numbersArray[2]);
//           
//		}
            //String dunno = stockDownload.StockYahooMapping.BuildFetchUrl(stockSymbol);
            
          //  System.out.println(dunno);
            stock = stockDownloader.Download(stockSymbol);
			Double price = stock.getLastTrade();
			int owned = Integer.parseInt(numbersArray[2]);
			Double total = price*owned;
			form = form + "<tr><td colspan='9'>" +
						"<form action=\"TransactionDetailsServlet\"><table class=\"innerTable\">" +
							"<td>"+ stockSymbol+
						     "<input type=\"hidden\" name=\"stockSymbol\" value=\""+ stockSymbol+"\"></td>" +
						     "<td>$"+String.valueOf(stock.getLastTrade())+
						     "<input type=\"hidden\" name=\"stockValue\" value=\""+ String.valueOf(stock.getLastTrade()) +"\"></td>" +
						     "<td>"+String.valueOf(stock.getChange())+
						     "<input type=\"hidden\" name=\"stockChange\" value=\""+ String.valueOf(stock.getChange())+"\"></td>" +
						     "<td>" +  String.valueOf(stock.getLastTradeTime()) + " " +String.valueOf(stock.getLastTradeDate()) + " EST</td>" +
						     "<td>"+numbersArray[2]+
						     "<input type=\"hidden\" name=\"stockOwned\" value=\""+numbersArray[2]+"\"></td>" +
						     "<td>$"+df.format(total)+"</td>" +
						     "<td><select name=\"stockAction\" ><option value=\"buy\">buy</option><option value=\"sell\">sell</option></select></td>" +
						     "<td class=\"quantityWidth\"><input type=\"text\" name=\"quantity\"></td>" +
						     "<td><input type=\"submit\" value=\"Make Transaction\"></td>" +
						     
						"</table></form>" +
						"</td></tr>";
			
//			"<form action=\"TransactionDetailsServlet\">\n" +
//			"<td>"+ stockSymbol+"</td>" +
//		     "<input type=\"hidden\" name=\"stockSymbol\" value=\""+ stockSymbol+"\">" +
//		     "<td id=\"price\">$"+numbersArray[0]+"</td>" +
//		     "<input type=\"hidden\" name=\"stockValue\" value=\""+ numbersArray[0]+"\">" +
//		     "<td id=\"change\">"+numbersArray[1]+"</td>" +
//		     "<input type=\"hidden\" name=\"stockChange\" value=\""+ numbersArray[1]+"\">" +
//		     "<td id=\"date\"></td>" +
//		     "<td id=\"price\">"+ +"</td>" +
//		     "<input type=\"hidden\" name=\"stockOwned\" value=\""+numbersArray[2]+"\">" +
//		     "<td>$"+price*owned+"</td>" +
//		     "<td><select name=\"stockAction\" ><option value=\"buy\">buy</option><option value=\"sell\">sell</option></select></td>" +
//		     "<td><input type=\"text\" name=\"quantity\"></td>" +
//		     "<td><input type=\"submit\" value=\"Make Transaction\"></td>" +
//		     
//		"</form>" +
//		"</tr>";
            }
		}
		sessionInfo.setForm(form);
	}
	
	
	public static void transactionDetailsForm(SessionBean sessionInfo, String stockSym, String stockVal, String stockAct, String quant, String stockOwn, String stockCha){
		
		
		Integer userKey = Integer.parseInt(sessionInfo.getUserPrimaryKey());
		
		String transactionForm = "";
		DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
		
		Integer stockQuant;
		
		Map<String, String> stocks = model.User_StockInfo.getUserStock(String.valueOf(userKey));
        
        
//		for(Map.Entry<String, String> entry : stocks.entrySet()){
//			if(String.valueOf(stockSym) == String.valueOf(entry.getKey())){
//
//				stockOwn = entry.getValue();
//				System.out.println(stockOwn);
//				System.out.println("hello");
//			}
//			
//		}
//		
		if(DataCheck.NullOrEmpty(stockOwn)){
			for (HashMap.Entry<String, String[]> entry : sessionInfo.getStockHoldings()) {
	            if(stockSym.equals(entry.getKey())){
		            String numbersArray[] = entry.getValue();
		            stockOwn = numbersArray[2];
	            }
			}
		}	
		
		
		if(DataCheck.NullOrEmpty(quant)){
			quant = "0";
			stockQuant = 0;
		}else{
			stockQuant = Integer.parseInt(quant);
		}
		//Float testStockQuant = Float.parseFloat(stockQuant);
		Double newStockVal = Double.parseDouble(stockVal);
		
		Double total = stockQuant*newStockVal;
		//System.out.println(total);
		transactionForm = transactionForm + "<tr><td colspan='5'>" +
						"<table class=\'innerTable\'><tr>" +
							"<td>"+ stockSym+
						     "<input type=\"hidden\" name=\"stockSymbol\" value=\""+ stockSym+"\"></td>" +
						     "<td>"+stockAct+
						     "<input type=\"hidden\" name=\"stockAction\" value=\""+ stockAct +"\"></td>" +
						     "<td>"+quant+
						     "<input type=\"hidden\" name=\"quantity\" value=\""+ quant+"\"></td>" +
						     "<td>$"+stockVal+
						     "<input type=\"hidden\" name=\"stockValue\" value=\""+stockVal+"\"></td>" +
						     "<td>$"+ df.format(total) +"</td>" +
						"</tr></table>"+
						"<input type=\"hidden\" name=\"stockOwned\" value=\""+stockOwn+"\">" +
						"<input type=\"hidden\" name=\"stockChange\" value=\""+stockCha+"\">"+
						"</td></tr>";

	
			sessionInfo.setTransactionForm(transactionForm);
		}

	
}
