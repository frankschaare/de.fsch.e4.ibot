/**
 * 
 */
package de.fsch.e4.ibot.data.csv.yahoo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.fsch.e4.ibot.data.csv.ICSVFile;

/**
 * @author hit
 *
 */
public class CSVDatei extends File implements ICSVFile
{
private Charset charset = Charset.forName("ISO-8859-1");
private List<String> lines;
private ArrayList<String[]> fields = new ArrayList<String[]>();	

protected boolean hasHeader = true;
protected boolean errors = false;
protected boolean checked = false;
protected String delimiter = ";";
protected int lineCount = -1;	

	/**
	 * @param arg0
	 */
	public CSVDatei(String arg0) {super(arg0);}

	/**
	 * @param arg0
	 */
	public CSVDatei(URI arg0) {	super(arg0);}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CSVDatei(String arg0, String arg1) {	super(arg0, arg1);}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CSVDatei(File arg0, String arg1)	{super(arg0, arg1);}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#read()
	 */
	@Override
	public void read()
	{
		lineCount = 1;
		try
		{
		lines = Files.readAllLines(Paths.get(super.getPath()), charset);
		for (String line : lines)
		{
		switch (lineCount)
		{
		// Erste Zeile wird nur verarbeitet wenn keine Kopfzeile vorhanden ist
		case 1:
		if (!hasHeader)
		{
		fields.add(line.split(delimiter));	
		}
		break;

		default:
		fields.add(line.split(delimiter));
		break;
		}	
		lineCount++;
		}	
		}
		catch (IOException e)
		{
		e.printStackTrace();	
		}

	}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#getLines()
	 */
	@Override
	public List<String> getLines()	{return this.lines; }

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#getFields()
	 */
	@Override
	public ArrayList<String[]> getFields() { return this.fields;}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#hasHeader()
	 */
	@Override
	public boolean hasHeader() {return this.hasHeader;}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#setHasHeader(boolean)
	 */
	@Override
	public void setHasHeader(boolean hasHeader) {this.hasHeader = hasHeader;}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#getDelimiter()
	 */
	@Override
	public String getDelimiter() { return this.delimiter;}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#setDelimiter(java.lang.String)
	 */
	@Override
	public void setDelimiter(String delimiter) {this.delimiter = delimiter;}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#getLineCount()
	 */
	@Override
	public int getLineCount() { return this.getLineCount();}

	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.data.csv.ICSVFile#setLineCount(int)
	 */
	@Override
	public void setLineCount(int lineCount)	{this.lineCount = lineCount;}

}
