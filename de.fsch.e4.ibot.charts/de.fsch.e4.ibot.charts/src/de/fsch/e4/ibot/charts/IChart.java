/**
 * 
 */
package de.fsch.e4.ibot.charts;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;

import de.fsch.e4.ibot.asset.ISecurity;
import de.fsch.e4.ibot.asset.Quote;

/**
 * @author hit
 *
 */
public interface IChart
{
public Canvas getCanvas();
public int getTotalWidth();
public int getTotalHeight();
public int getDrawableElements();
public Quote getMaxQuote();
public Quote getMinQuote();
public double getDrawableValue();

public void setCanvas(Canvas canvas);

public Image getBuffer(); 

public void setInput(ISecurity input);
}
