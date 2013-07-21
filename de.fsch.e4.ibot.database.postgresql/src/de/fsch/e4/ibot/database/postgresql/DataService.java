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
import java.util.Calendar;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;

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

}
