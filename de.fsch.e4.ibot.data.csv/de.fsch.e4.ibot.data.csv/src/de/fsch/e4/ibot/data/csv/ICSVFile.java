/**
 * 
 */
package de.fsch.e4.ibot.data.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hit
 *
 */
public interface ICSVFile
{
	public void read();	
	public List<String> getLines();
	public ArrayList<String[]> getFields();
	public boolean hasHeader();
	public void setHasHeader(boolean hasHeader); 
	public String getDelimiter();
	public void setDelimiter(String delimiter);
	public int getLineCount();
	public void setLineCount(int lineCount);
}
