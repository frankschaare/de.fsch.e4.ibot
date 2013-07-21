package de.fsch.e4.ibot.data.yahoo;

public class Yahoo 
{
	public interface FINANCE
	{
	public static final String HISTORICAL_DATA = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22YHOO%22%20and%20startDate%20%3D%20%222012-01-01%22%20and%20endDate%20%3D%20%222012-12-31%22&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";	
	}
}
