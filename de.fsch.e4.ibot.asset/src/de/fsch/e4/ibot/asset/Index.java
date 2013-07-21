/**
 * 
 */
package de.fsch.e4.ibot.asset;

import java.util.ArrayList;

/**
 * @author Frank Schaare, 25.09.2007
 * @version 1.0
 * 
 * Ein Index kann eine Liste von Aktien enthalten
 *
 */
public class Index extends Asset 
{
private ArrayList<Stock> stocks = new ArrayList<Stock>();

	/**
	 * Default Konstruktor.
	 * Der Typ wird automatisch auf Index gesetzt.
	 */
	public Index() 
	{
	this.setType(Asset.INDEX);
	}

	public ArrayList<Stock> getStocks()
	{
	return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) 
	{
	this.stocks = stocks;
	}

	public void addStock(Stock stock) 
	{
		if (!stocks.contains(stock)) 
		{
		stocks.add(stock);	
		}
	}
}
