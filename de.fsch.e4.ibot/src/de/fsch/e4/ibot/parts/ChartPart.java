/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package de.fsch.e4.ibot.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.fsch.e4.ibot.ContextLogger;
import de.fsch.e4.ibot.constants.AppConstants;
import de.fsch.e4.ibot.constants.DateConstants;
import de.fsch.e4.ibot.asset.Quote;
import de.fsch.e4.ibot.asset.Stock;
import de.fsch.e4.ibot.charts.finance.CandlestickChart;
import de.fsch.e4.ibot.charts.finance.candlestick.Candle;
import de.fsch.e4.ibot.database.IDataService;

public class ChartPart 
{
private Canvas canvas = null;
private CandlestickChart chart = new CandlestickChart();

private Label label;

@Inject IDataService dataService; 
@Inject @Optional MApplication app;
@Inject @Named(AppConstants.LOGGER) private ContextLogger log;

	@PostConstruct
	public void createComposite(Composite parent) 
	{
	MWindow main = app.getChildren().get(0);
	main.setLabel("IBot - " + dataService.getInfo());
	
	Stock dte = new Stock();
	dte.setIsin("DE0005557508");
	dte.setQuotes(dataService.getQuotes(dte.getIsin(), Quote.Types.DAY));
	chart.setInput(dte);
	log.confirm("ISIN "+ dte.getIsin() + " wurde mit " + dte.getQuotes().size() + " Quotes für die Zeit vom " + DateConstants.DEFAULT.format(dte.getQuotes().firstEntry().getValue().getDate()) + " bis " + DateConstants.DEFAULT.format(dte.getQuotes().lastEntry().getValue().getDate()) + " als Input für den Chart gesetzt. " , this.getClass().getName());

	
		GridLayout gl_parent = new GridLayout();
		gl_parent.marginHeight = 0;
		gl_parent.marginWidth = 0;
		gl_parent.verticalSpacing = 0;
		gl_parent.horizontalSpacing = 0;
		parent.setLayout(gl_parent);

		label = new Label(parent, SWT.NONE);
		label.setText(dte.getIsin() + " (" + dte.getQuotes().size() + " Quotes)");
		
		canvas = new Canvas(parent, SWT.NONE);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		canvas.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		canvas.addPaintListener(new PaintListener()
		{
			@Override
			public void paintControl(PaintEvent e)
			{
			chart.setCanvas(canvas);
				if (! canvas.isDisposed())
				{
				log.info("Dem Chart stehen ingesamt " + chart.getTotalWidth() + " Pixel horizontal und " + chart.getTotalHeight() + " Pixel vertikal zur Verfügung.", this.getClass().getName() + ".paintControl(PaintEvent e)");
				log.info("Geteilt durch die minimale Breite einer Kerze (" + Candle.MIN_WIDTH + " Pixel) und dem minimalem Abstand zwischen zwei Elementen (" + Candle.MIN_SPACE + " Pixel) können maximal " + chart.getDrawableElements() + " Elemente gezeichnet werden.", this.getClass().getName() + ".paintControl(PaintEvent e)");
				log.info("Drawable Quotes enthält " + chart.getDrawableQuotes().size() + " Quotes für die Zeit vom " + DateConstants.DEFAULT.format(chart.getDrawableQuotes().firstEntry().getValue().getDate()) + " bis " + DateConstants.DEFAULT.format(chart.getDrawableQuotes().lastEntry().getValue().getDate()), this.getClass().getName());
				log.info("MinQuote vom " + DateConstants.DEFAULT.format(chart.getMinQuote().getDate()) + " enthält Minimalwert = " + chart.getMinQuote().getLow() + ", MaxQuote vom  " + DateConstants.DEFAULT.format(chart.getMaxQuote().getDate()) + " enthält Maximalwert = " + chart.getMaxQuote().getHigh(), this.getClass().getName());
				log.info("Es müssen also " + chart.getDrawableValue() + " € in " + chart.getTotalHeight() + " Pixeln dagestellt werden. Das entspricht " + (chart.getDrawableValue() / chart.getTotalHeight()) + " € / Pixel", this.getClass().getName());
				}
			Image test = chart.getBuffer();
			e.gc.drawImage(chart.getBuffer(), chart.getBuffer().getBounds().x, chart.getBuffer().getBounds().y);
			}
		});
		canvas.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseDown(MouseEvent e)
			{
			Candle selected = chart.getSelectedCandle(e.x, e.y);
			log.info("SelectedCandle vom " + DateConstants.DEFAULT.format(selected.getQuote().getDate()) + ", Open = " + selected.getQuote().getOpen() + ", Close =  " + selected.getQuote().getClose() + ", High = " + selected.getQuote().getHigh() + ", Low = " + selected.getQuote().getLow(), this.getClass().getName());
			}
			@Override
			public void mouseDoubleClick(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		canvas.addMouseTrackListener(new MouseTrackListener()
		{
			
			@Override
			public void mouseHover(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExit(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEnter(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Focus
	public void setFocus() 
	{
	
	}
}
