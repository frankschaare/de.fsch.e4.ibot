/**
 * 
 */
package de.fsch.e4.ibot.data.yahoo;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author hit
 *
 */
public class YQLConnection implements IYQLConnection 
{
private	HttpResponse response = null;
	/**
	 * 
	 */
	public YQLConnection() {}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.yahoo.IYQLConnection#getData()
	 */
	@Override
	public void getISIN() 
	{


	}

	@Override
	public void getHistoricaldata() 
	{
	String request = Yahoo.FINANCE.HISTORICAL_DATA;	
	DefaultHttpClient client = new DefaultHttpClient();
	HttpGet get = new HttpGet(request);

		try 
		{
		response = client.execute(get);
		} 
		catch (ClientProtocolException e) 
		{
		e.printStackTrace();
		} 
		catch (IOException e) 
		{
		e.printStackTrace();
		}


		if (response.getEntity() != null) 
		{
			try 
			{
			InputStream in = response.getEntity().getContent();
			// Process response
			Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			// Get all search Result nodes
			NodeList nodes = (NodeList) xPath.evaluate("query/results/quote",	response, XPathConstants.NODESET);
				
			int nodeCount = nodes.getLength();
			// iterate over search Result nodes
				for (int i = 0; i < nodeCount; i++) 
				{
				// Get each xpath expression as a string
				String date = (String) xPath.evaluate("Date", nodes.item(i),	XPathConstants.STRING);
				String open	= (String) xPath.evaluate("Open", nodes.item(i), XPathConstants.STRING);
				String high = (String) xPath.evaluate("High", nodes.item(i), XPathConstants.STRING);
				String low = (String) xPath.evaluate("Low", nodes.item(i),	XPathConstants.STRING);
				String close = (String) xPath.evaluate("Close", nodes.item(i), XPathConstants.STRING);
				String volume = (String) xPath.evaluate("Volume", nodes.item(i), XPathConstants.STRING);
				// print out the Title, Summary, and URL for each search result
				System.out.println("Date: " + date);
				System.out.println("Open: " + open);
				System.out.println("High: " + high);
				System.out.println("Low: " + low);
				System.out.println("Close: " + close);
				System.out.println("Volume: " + volume);
				
				System.out.println("-----------");
				}
			} 
			catch (IllegalStateException | IOException | SAXException | ParserConfigurationException | XPathExpressionException e) 
			{
			e.printStackTrace();
			}	
		}	
	}

}
