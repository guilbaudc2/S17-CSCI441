$(document).ready(function(){
	
	var $info = "";
		var ajaxGET = $.ajax({
			type: "GET",
			url : "https://www.google.com/finance/info?q=NSE:" + stockSymbol,
			dataType: "jsonp",

		});
		
		ajaxGET.done(function(stockPrices){
					
			$.each(stocks, function(i, stockPTDC) {
				
		//		console.log(stockNamePriceDateChange);
				
			     $info.append("<td>$"+ stockPTDC.l+"</td>" +
			     "<input type=\"hidden\" name=\"stockValue\" value=\""+ stockPTDC.l +"\">" +
			     "<td>"+stockPTDC.lt+"</td>" +
			     "<input type=\"hidden\" name=\"stockChange\" value=\""+ stockPTDC.lt+"\">" +
			     "<td>"+ stockPTDC.c + "</td>");
			});
		});
//		
		ajaxGET.fail(function(){
			alert('ajaxStock.js fail');
//			console.log('fail' + stocks.status);
//			console.log('fail' + stocks.state());
//			alert('ajaxStock.js fail');
		});
});

