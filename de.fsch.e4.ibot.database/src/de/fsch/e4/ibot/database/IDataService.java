/**
 * 
 */
package de.fsch.e4.ibot.database;

import java.sql.SQLException;
import java.util.Date;
import java.util.TreeMap;

import de.fsch.e4.ibot.asset.ISecurity;
import de.fsch.e4.ibot.asset.Quote;

/**
 * @author hit
 *
 */
public interface IDataService
{
public String getInfo();
public SQLException setQuotes(ISecurity security);
public TreeMap<Date, Quote> getQuotes(String isin, int periodType);
}