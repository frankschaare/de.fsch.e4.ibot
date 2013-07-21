/**
 * 
 */
package de.fsch.e4.ibot.asset;

import java.util.Date;

/**
 * @author hit
 *
 */
public class Quote
{
private Date date = null;
private double open = 0;
private double high = 0;
private double low = 0;
private double close = 0;
private int volume = 0;
private double adjClose = 0;
	

	/**
	 * 
	 */
	public Quote()
	{

	}


	public Date getDate()
	{
		return date;
	}


	public void setDate(Date date)
	{
		this.date = date;
	}


	public double getOpen()
	{
		return open;
	}


	public void setOpen(double open)
	{
		this.open = open;
	}


	public double getHigh()
	{
		return high;
	}


	public void setHigh(double high)
	{
		this.high = high;
	}


	public double getLow()
	{
		return low;
	}


	public void setLow(double low)
	{
		this.low = low;
	}


	public double getClose()
	{
		return close;
	}


	public void setClose(double close)
	{
		this.close = close;
	}


	public int getVolume()
	{
		return volume;
	}


	public void setVolume(int volume)
	{
		this.volume = volume;
	}


	public double getAdjClose()
	{
		return adjClose;
	}


	public void setAdjClose(double adjClose)
	{
		this.adjClose = adjClose;
	}
	
}
