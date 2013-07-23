/**
 * 
 */
package de.fsch.e4.ibot.database.postgresql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TreeMap;

import org.eclipse.core.runtime.FileLocator;

import de.fsch.e4.ibot.asset.ISecurity;
import de.fsch.e4.ibot.asset.Quote;
import de.fsch.e4.ibot.database.IDataService;

/**
 * @author hit
 *
 */
public class DataService implements IDataService
{
private Connection con = null;
private PreparedStatement ps = null;
private ResultSet rs = null;

private String info = "Nicht verbunden";

private Calendar cal = Calendar.getInstance();

private	Properties props;

	/**
	 * 
	 */
	public DataService()
	{
	String url = "jdbc:postgresql://localhost/ibot";	
	props = new Properties();

		try
		{
		Class.forName("org.postgresql.Driver");		
			
		URL configURL = this.getClass().getClassLoader().getResource("dbProperties.xml");
		File configFile = new File(FileLocator.toFileURL(configURL).getPath());	
		InputStream in = new FileInputStream(configFile);
		props.loadFromXML(in);

		con = DriverManager.getConnection(url, props);

		DatabaseMetaData dbmd = con.getMetaData();
		this.info = "Benutzer " + dbmd.getUserName();
		this.info += " verbunden mit " + dbmd.getDatabaseProductName() + " (" + dbmd.getDatabaseProductVersion() + ")";
		this.info += " - " + dbmd.getDriverName() + " (" + dbmd.getDriverVersion() + ")";
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}
		catch (SQLException e)
		{
		e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
		e.printStackTrace();
		}
	}

	@Override
	public String getInfo()	{return this.info;}

	@Override
	public SQLException setQuotes(ISecurity security)
	{
	SQLException e = null;
		try
		{
		ps = con.prepareStatement(PreparedStatements.INSERT_QUOTES);
			for (Quote q : security.getQuotes().values())
			{
			ps.setString(1, security.getIsin());	
			ps.setInt(2, q.getType());
			ps.setDate(3, new java.sql.Date(q.getDate().getTime()));
			ps.setDouble(4, q.getOpen());
			ps.setDouble(5, q.getHigh());
			ps.setDouble(6, q.getLow());
			ps.setDouble(7, q.getClose());
			ps.setInt(8, q.getVolume());
			ps.setDouble(9, q.getAdjClose());
			
			ps.execute();
			}

		}
		catch (SQLException exception)
		{
		exception.printStackTrace();
		e = exception;
		}	
	return e;
	}

	@Override
	public TreeMap<Date, Quote> getQuotes(String isin, int periodType)
	{
	TreeMap<Date, Quote> quotes = new TreeMap<Date, Quote>();
	Quote q = null;
		try
		{
		ps = con.prepareStatement(PreparedStatements.SELECT_QUOTES);
		ps.setString(1, isin);
		ps.setInt(2, periodType);
		rs = ps.executeQuery();
			
			while (rs.next())
			{
			q = new Quote();
			q.setType(rs.getInt(3));
			q.setDate(rs.getDate(4));
			q.setOpen(rs.getDouble(5));
			q.setHigh(rs.getDouble(6));
			q.setLow(rs.getDouble(7));
			q.setClose(rs.getDouble(8));
			q.setVolume(rs.getInt(9));
			q.setAdjClose(rs.getDouble(10));
			
			quotes.put(q.getDate(), q);
			}
		}
		catch (SQLException exception)
		{
		exception.printStackTrace();
		}
	return quotes;
	}

}
