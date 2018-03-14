package stockDownload;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by senizhegorodtsev on 7/9/2015.
 * Modified for CSCI441 3/20/17
 * This is a general downloader which can download stock info from different sources
 * https://www.codeproject.com/articles/1009599/downloading-stock-quotes-with-stocks-tracker-api
 */
public class StockDownloader {

    private StockYahooMapping _stockMapping;

    public StockDownloader(StockYahooMapping stockMapping)
    {
        set_stockMapping(stockMapping);
    }

    public static StockGetSet Download(String ticker) {
    	StockGetSet stock = null;
        try {
            URL yahoo = new URL(StockYahooMapping.BuildFetchUrl(ticker));
//System.out.println(yahoo.toString());            
            URLConnection connection = yahoo.openConnection();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(is);
            // Parse the object
            StringBuilder builder = new StringBuilder();
            String aux;
            while ((aux = br.readLine()) != null) {
                builder.append(aux);
//System.out.println(aux);                
            }
            String text = builder.toString();
            stock = StockYahooMapping.MapStock(text);
        } catch (Exception e) {
            Logger log = Logger.getLogger(StockDownloader.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
        }
        return stock;
    }

	public StockYahooMapping get_stockMapping() {
		return _stockMapping;
	}

	public void set_stockMapping(StockYahooMapping _stockMapping) {
		this._stockMapping = _stockMapping;
	}
}

