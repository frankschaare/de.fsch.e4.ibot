/**
 * 
 */
package de.fsch.e4.ibot.asset;

import java.util.ArrayList;



/**
 * @author Frank Schaare, 25.09.2007
 * @version 1.0
 * 
 * Superklasse für alle Anlagetypen
 *
 */
public class Asset implements ISecurity, Comparable<Asset>
{
public static final String ASSET_NOT_SET = "bitte auswählen...";	
public static final String INDEX = "Index";
public static final String STOCK = "Aktie";
public static final String BOND = "Anleihe";
public static final String FUTURE = "Future";
public static final String DEFAULT_CURRENCY = "EUR";

private ArrayList<Quote> quotes = null;

/**
 * Die Bezeichnung der Anlageklasse 
 */
private String name = null;

/**
 * Die International Security Identification Number 
 */
private String isin = null;

/**
 * Die Wertpapierkennnummer 
 */
private String wkn = null;
/**
 * Das Symbol, mit dem bei einigen deutschen Börsen referenziert wird 
 */
private String symbol = null;

/**
 * Das Interactive Brokers Symbol 
 */
private String ibSymbol = null;

/**
 * Die konkrete Anlageklasse 
 */
private String type = null;

/**
 * Die Standardwährung, in der die Anlageklasse gehandelt wird 
 */
private String currency = null;


	/**
	 * Konstruktor
	 */
	public Asset() 
	{
	}

	public int compareTo(Asset o) 
	{
		int result = this.getName().compareToIgnoreCase(o.getName());
		return result;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) 	{this.isin = isin;}

	public String getName() {return name;}

	public void setName(String name) 
	{
	this.name = name;
	}

	public void setType(String type) 
	{
	this.type = type;
	}
	
	public String getWkn() {return wkn;}
	public void setWkn(String wkn) {this.wkn = wkn;}

	public String getType() {return type;}

	public String getIbSymbol() {return ibSymbol;}
	public void setIbSymbol(String ibSymbol) {this.ibSymbol = ibSymbol;}

	public String getSymbol() {return symbol;}
	public void setSymbol(String symbol) {this.symbol = symbol;	}

	public String getCurrency() {return currency;}

	public void setCurrency(String currency) {this.currency = currency;	}

	@Override
	public ArrayList<Quote> getQuotes()	{return this.quotes;}

	@Override
	public void setQuotes(ArrayList<Quote> quotes)	{this.quotes = quotes;}
}
