/**
 * 
 */
package de.fsch.e4.ibot.charts.finance.candlestick;

import java.util.Date;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import de.fsch.e4.ibot.asset.Quote;
import de.fsch.e4.ibot.charts.finance.CandlestickChart;

/**
 * @author hit
 *
 */
public class Candle
{
// Eine Kerze ist minimal drei Pixel breit	
public static final int MIN_WIDTH = 9;
/*
 * Der Abstand zwischen zwei Kerzen mindestens ein Pixel
 * Ohne Gimmicks wie Rand oder Werteachse können also maximal
 * die Breite des Canvas / (MIN_WIDTH + MIN_SPACE) Kerzern gezeichnet werden.  	
 */
public static final int MIN_SPACE = 5;

private Rectangle body = null;
private Rectangle drawArea = null;
private CandlestickChart chart = null;
private Quote quote = null;
private boolean bullish = false;
public int x = 0;
public int y = 0;
public int topShadowY = 0;
public int bottomShadowY = 0;
public int height = 0;
private Point shadowTop = null;
private Point shadowBottom = null;

	/**
	 * 
	 */
	public Candle(CandlestickChart chart, Quote quote)
	{
	this.chart = chart;
	this.quote = quote;
	this.bullish = quote.getClose() > quote.getOpen() ? true : false;
	setDrawArea();
	}
	
	/*
	 * Die horizontale Position wird vom Chart vorgegeben.
	 * Steht diese fest, wird sofort die x-Position des Kerzenschattens definiert.
	 * 
	 * Gleichzeitig wird hier auch die drawArea fertiggestellt. Dieser unsichtbare Rahmen umschliesst die Kerze
	 * und erleichtert das spätere Suchen bei MouseEvents.
	 */
	public void setX(int x)
	{
	this.x = x;
	this.shadowTop = new Point(x + ((Candle.MIN_WIDTH) / 2), this.topShadowY );
	this.shadowBottom = new Point(x + ((Candle.MIN_WIDTH) / 2), this.bottomShadowY );
	this.drawArea = new Rectangle(x, topShadowY, Candle.MIN_WIDTH, (bottomShadowY - topShadowY));
	}
	
	public Point getShadowTop() {return shadowTop;}
	public Point getShadowBottom() {return shadowBottom;}

	public boolean isBullish()
	{
	return bullish;
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

	/*
	 * Die Fläche, in der die Kerze gezeichnet wird.
	 * Dreisatz: 	Chart.drawableValue = Chart.Height
	 * 				z.b. 2,13 = 634 Pixel
	 * 				diff = X	
	 */
	// TODO: Hier wird zunächst nur der Body berechnet. Sollte inclusive Schatten sein !
	public void setDrawArea()
	{
	double diffBodyTop = 0;
	double diffBodyBottom = 0;
	double max = chart.getMaxQuote().getHigh();
	double quoteMax = 0;
	double quoteMin = 0;
	
		if (bullish)
		{
		diffBodyTop = max - quote.getClose();
		diffBodyBottom = max - quote.getOpen();
		height = (int) ((diffBodyBottom * chart.getTotalHeight()) / chart.getDrawableValue());
		y = (int) Math.round((diffBodyTop * chart.getTotalHeight()) / chart.getDrawableValue());
		height = height - y;
		quoteMax = quote.getHigh() > quote.getClose() ? quote.getHigh() : quote.getClose();
		quoteMin = quote.getLow() < quote.getOpen() ? quote.getLow() : quote.getOpen();
		}
		else
		{
		diffBodyTop = max - quote.getOpen();
		diffBodyBottom = max - quote.getClose();
		height = (int) ((diffBodyBottom * chart.getTotalHeight()) / chart.getDrawableValue());
		y = (int) Math.round((diffBodyTop * chart.getTotalHeight()) / chart.getDrawableValue());
		height = height - y;
		quoteMax = quote.getHigh() > quote.getOpen() ? quote.getHigh() : quote.getOpen();
		quoteMin = quote.getLow() < quote.getClose() ? quote.getLow() : quote.getClose();
		}	
		topShadowY = (int) Math.round(((max - quoteMax) * chart.getTotalHeight()) / chart.getDrawableValue());
		bottomShadowY = (int) Math.round(((max - quoteMin) * chart.getTotalHeight()) / chart.getDrawableValue());
	}

	public Quote getQuote()
	{
	return this.quote;
	}

	
}
