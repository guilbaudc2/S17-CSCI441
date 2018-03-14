$(document).ready(function(){
	var $stocks = $('#stockInfo');
	
	var ajaxGET = $.ajax({
		type: "GET",
		url: 'json/stock.json',
		dataType: "json",
	});
	
	ajaxGET.done(function(stocks){
		
		$.each(stocks, function(i, stock){
            $stocks.append('<li><img src="' + stock.Picture + '"> <br>'
            		
            		+ stock.Company +': '+ stock.tickerSymbol + '<br>'
            		+'<a href="' + stock.Webpage + '">' + stock.Webpage +'</a><br>'
            		+ '<p>' + stock.Description + '</p></li>');    // yay it worked
        });


	});
	
	ajaxGET.fail(function(stocks){
		console.log('fail' + stocks.status);
		console.log('fail' + stocks.state());
		alert('ajaxStock.js fail');
	});
	
});