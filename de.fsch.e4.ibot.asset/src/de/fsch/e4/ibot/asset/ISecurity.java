/**
 * 
 */
package de.fsch.e4.ibot.asset;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

/**
 * @author hit
 *
 */
public interface ISecurity
{	
public TreeMap<Date, Quote> getQuotes();
public void setQuotes(TreeMap<Date, Quote> quotes);
	
public String getType();
public void setType(String type);

public void setWkn(String wkn);
public String getWkn();

public String getIsin();
public void setIsin(String isin) ;

public String getName();
public void setName(String name);
	
public String getSymbol();	
public void setSymbol(String symbol);
	
public String getIbSymbol();
public void setIbSymbol(String ibSymbol);

public String getCurrency();
public void setCurrency(String currency);
}
