/**
 * 
 */
package de.fsch.e4.ibot.asset;

/**
 * @author hit
 *
 */
public interface ISecurity
{
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