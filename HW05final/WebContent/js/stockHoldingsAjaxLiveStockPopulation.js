$(document).ready(function(){
	$("#div-table").css({"visibility": "hidden"});	// hide the small table box that is created due to border spacing, padding, etc
	$("#checkStock").click(function(){
		var stockSymbol = $("#stockSymbol").val();
//
		
		var $tr = $('<tr/>');
		var $td = $('<td/>')
//		
//		var tableHeader = "" + 
//             "<div class=\"div-table-row\">\n" +
//        		"<div class=\"div-table-col ninetyWidth tableHeader\">Stock Symbol</div>\n" +
//    			"<div  class=\"div-table-col tableHeader\">Current Price per Share</div>\n" +
//                "<div  class=\"div-table-col ninetyWidth tableHeader\">Recent Change</div>\n" +
//                "<div  class=\"div-table-col tableHeader\">Last Updated</div>\n" +
//                "<div  class=\"div-table-col seventyWidth tableHeader\">Quantity</div>\n" +
//                "<div  class=\"div-table-col oneTwentyFiveWidth tableHeader\">Transaction</div>\n" +
//            "</div>\n";


		var tableHeader = "<th>Stock Symbol</th>" +
      "	<th>Current Price per Share</th>" +
      "	<th>Recent Change</th>" +
      "	<th>Last Updated</th>" +
      "	<th>Quantity</th>" +
      "	<th>Transaction</th></tr>";
		
		
//		var tableEnd ="</table>";
		var ajaxGET = $.ajax({
			type: "GET",
			url : "https://www.google.com/finance/info?q=NSE:" + stockSymbol,
			dataType: "jsonp",

		});
		
		ajaxGET.done(function(stockPrices){
					
			$.each(stockPrices, function(i, stockNamePriceTimeDateChange) {
				
		//		console.log(stockNamePriceDateChange);
	
				$("#div-table").css({"visibility": "visible"});
				$("tr#table-header").html(tableHeader);
				$("tr#div-table-row").html('<td>'  + stockNamePriceTimeDateChange.t +
						'<input type="hidden" name="stockSymbol" value="'+ stockNamePriceTimeDateChange.t +'"></td>' +
						'<td>'  + stockNamePriceTimeDateChange.l +
						'<input type="hidden" name="stockValue" value="'+ stockNamePriceTimeDateChange.l +'"></td>' +
						'<td>'  + stockNamePriceTimeDateChange.c  +
						'<input type="hidden" name="stockChange" value="'+ stockNamePriceTimeDateChange.c +'"></td>' +
						'<td>'  + stockNamePriceTimeDateChange.lt +"</td>" +
						'<input type="hidden" name="stockAction" value="buy">' +
						'<td><input type="text" name="quantity"></td>' +
						'<td><input type="submit" value="Make Transaction"></td>');
	
	
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
});

