/**
 * 
 */
package de.fsch.e4.ibot.charts.finance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.GC;

import de.fsch.e4.ibot.asset.ISecurity;
import de.fsch.e4.ibot.asset.Quote;
import de.fsch.e4.ibot.charts.IChart;
import de.fsch.e4.ibot.charts.finance.candlestick.Candlestick;

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
	setDrawableElements();	
	}

	/*
	 * Liefert die Anzahl (=drawableElements) der zu zeichnenden Quotes
	 */
	private TreeMap<Date, Quote> getDrawableQuotes()
	{
	TreeMap<Date, Quote> drawableQuotes = new TreeMap<Date, Quote>();

		int putCount = 0;
		for (Entry<Date, Quote> entry : input.getQuotes().descendingMap().entrySet())
		{
			while (putCount < drawableElements)
			{
			drawableQuotes.put(entry.getKey(), entry.getValue());			
			}
		}
	return drawableQuotes;
	}
		
		
	private void setDrawableElements()
	{
		if (this.input != null)
		{
		drawableElements = canvas.getSize().x / (Candlestick.MIN_WIDTH + Candlestick.MIN_SPACE);
		}
		
	buffer = new Image(canvas.getDisplay(), canvas.getSize().x, canvas.getSize().y);
	GC bufferGC = new GC(buffer);
	bufferGC.setBackground(canvas.getBackground());
	bufferGC.setForeground(canvas.getDisplay().getSystemColor(SWT.COLOR_BLUE));
	bufferGC.setFont(canvas.getFont());
	bufferGC.drawText("Anzahl Kerzen: " + String.valueOf(drawableElements), 5, 5);
	
	bufferGC.dispose();
	}

	@Override
	public Image getBuffer()
	{
	return buffer;
	}

}
