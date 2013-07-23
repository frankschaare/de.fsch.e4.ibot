/**
 * 
 */
package de.fsch.e4.ibot.charts.finance.candlestick;

import org.eclipse.swt.graphics.Rectangle;

/**
 * @author hit
 *
 */
public class Candlestick
{
// Eine Kerze ist minimal drei Pixel breit	
public static final int MIN_WIDTH = 3;
/*
 * Der Abstand zwischen zwei Kerzen mindestens ein Pixel
 * Ohne Gimmicks wie Rand oder Werteachse können also maximal
 * die Breite des Canvas / (MIN_WIDTH + MIN_SPACE) Kerzern gezeichnet werden.  	
 */
public static final int MIN_SPACE = 1;

private Rectangle body = null;
private Rectangle drawArea = null;

	/**
	 * 
	 */
	public Candlestick()
	{

	}

	public Rectangle getBody()
	{
		return body;
	}

	public void setBody(Rectangle body)
	{
		this.body = body;
	}

	public Rectangle getDrawArea()
	{
		return drawArea;
	}

	public void setDrawArea(Rectangle drawArea)
	{
		this.drawArea = drawArea;
	}

	
}
