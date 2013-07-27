/**
 * 
 */
package de.fsch.e4.ibot.charts.finance;

import java.util.Date;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;

import de.fsch.e4.ibot.asset.ISecurity;
import de.fsch.e4.ibot.asset.Quote;
import de.fsch.e4.ibot.charts.IChart;
import de.fsch.e4.ibot.charts.finance.candlestick.Candle;

/**
 * @author hit
 *
 */
public class CandlestickChart implements IChart
{
private Canvas canvas = null; 
private Image buffer = null;
private ISecurity input = null;
private int drawableElements = 0;
private int totalWidth = 0;
private int totalHeight = 0;
private TreeMap<Date, Quote> drawableQuotes = null;
private TreeMap<Date, Candle> drawableCandles = null;
private double drawableValue = 0;
private double valuePerPixel = 0;
private Quote maxQuote = null;
private Quote minQuote = null;


	public CandlestickChart()
	{

	}
	
	/* (non-Javadoc)
	 * @see de.fsch.e4.ibot.charts.IChart#setInput(de.fsch.e4.ibot.asset.ISecurity)
	 */
	@Override
	public void setInput(ISecurity input) {this.input = input;}

	@Override
	public Canvas getCanvas() {	return this.canvas;}

	@Override
	public void setCanvas(Canvas canvas)
	{
	this.canvas = canvas;	
	this.totalWidth = canvas.getSize().x;
	this.totalHeight = canvas.getSize().y;
	setDrawableElements();
	setDrawableQuotes();
	setMaxValue();
	setMinValue();
	setDrawableCandles();
	}
	
	
	public Candle getSelectedCandle(int mouseX, int mouseY) 
	{	
	Candle selected = null;
	
		for (Candle c : drawableCandles.values())
		{
			if (c.getDrawArea().contains(mouseX, mouseY))
			{
			selected = c;	
			}
		}
	return selected;	
	}

	public TreeMap<Date, Candle> getDrawableCandles() {	return drawableCandles;	}

	public void setDrawableCandles()
	{
	drawableCandles = new TreeMap<Date, Candle>();	
	Candle c = null;	
		for (Quote q : drawableQuotes.values())
		{
		c = new Candle(this, q);
		drawableCandles.put(q.getDate(), c);
		}
	}

	/*
	 * Liefert die Anzahl (=drawableElements) der zu zeichnenden Quotes
	 */
	public TreeMap<Date, Quote> getDrawableQuotes()
	{
	return this.drawableQuotes;
	}
	
	private void setDrawableQuotes()
	{
	this.drawableQuotes = new TreeMap<Date, Quote>();

		int putCount = 0;
		for (Entry<Date, Quote> entry : input.getQuotes().descendingMap().entrySet())
		{
			if (putCount < drawableElements)
			{
			drawableQuotes.put(entry.getKey(), entry.getValue());			
			}
		putCount++;	
		}
	}
		
		
	private void setDrawableElements()
	{
		if (this.input != null)
		{
		drawableElements = canvas.getSize().x / (Candle.MIN_WIDTH + Candle.MIN_SPACE);
		}
	}

	@Override
	public Image getBuffer()
	{	
	buffer = new Image(canvas.getDisplay(), canvas.getSize().x, canvas.getSize().y);
	GC bufferGC = new GC(buffer);
	bufferGC.setBackground(canvas.getBackground());
		
		int x = Candle.MIN_SPACE;

		for (Candle c : drawableCandles.values())
		{
		c.setX(x);	
		bufferGC.setBackground(c.isBullish() ? canvas.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN) : canvas.getDisplay().getSystemColor(SWT.COLOR_RED));	
		bufferGC.fillRectangle(x, c.y, Candle.MIN_WIDTH, c.height);
		bufferGC.setForeground(c.isBullish() ? canvas.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN) : canvas.getDisplay().getSystemColor(SWT.COLOR_RED));
		bufferGC.drawLine(c.getShadowBottom().x, c.getShadowBottom().y, c.getShadowTop().x, c.getShadowTop().y);

		//bufferGC.setForeground(canvas.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		//bufferGC.drawRectangle(c.getDrawArea());
		
		x = x + Candle.MIN_WIDTH + Candle.MIN_SPACE;
		}
				
	bufferGC.dispose();
	
	return buffer;
	}

	@Override
	public int getTotalWidth() {return totalWidth;}

	@Override
	public int getTotalHeight() {return totalHeight;}

	@Override
	public int getDrawableElements() { return this.drawableElements;}

	@Override
	public Quote getMaxQuote() {return this.maxQuote;}

	@Override
	public Quote getMinQuote()	{return this.minQuote;}

	public void setMaxValue()
	{
	maxQuote = new Quote();
		for (Quote q : drawableQuotes.values())
		{
			if (q.getHigh() > maxQuote.getHigh())
			{
			maxQuote = q;	
			}		
		}
	}

	public void setMinValue()
	{
	minQuote = maxQuote;
		for (Quote q : drawableQuotes.values())
		{
			if (q.getLow() < minQuote.getLow())
			{
			minQuote = q;	
			}		
		}
	this.drawableValue = maxQuote.getHigh() - minQuote.getLow();
	this.valuePerPixel = drawableValue / getTotalWidth();
	}

	@Override
	public double getDrawableValue() { return this.drawableValue; }

	public double getValuePerPixel() {return valuePerPixel;}

	
	
}
