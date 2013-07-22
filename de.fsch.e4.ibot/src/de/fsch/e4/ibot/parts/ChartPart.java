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

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.fsch.e4.ibot.asset.Quote;
import de.fsch.e4.ibot.asset.Stock;
import de.fsch.e4.ibot.database.IDataService;

import org.eclipse.swt.widgets.Canvas;

public class ChartPart 
{
private Canvas canvas = null;
private Label label;

@Inject IDataService dataService; 
@Inject @Optional MApplication app;

	@PostConstruct
	public void createComposite(Composite parent) 
	{
	MWindow main = app.getChildren().get(0);
	main.setLabel("IBot - " + dataService.getInfo());
	
	Stock dte = new Stock();
	dte.setIsin("DE0005557508");
	dte.setQuotes(dataService.getQuotes(dte.getIsin(), Quote.Types.DAY));
		
		parent.setLayout(new GridLayout());

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
				System.out.println("BoundsX=" + canvas.getBounds().x + ", BoundsY=" + canvas.getBounds().y + ", BoundsWidth=" + canvas.getBounds().width + ", BoundsHeight=" + canvas.getBounds().height);
				System.out.println("ClientAreaX=" + canvas.getClientArea().x + ", ClientAreaY=" + canvas.getClientArea().y + ", ClientAreaWidth=" + canvas.getClientArea().width + ", ClientAreaHeight=" + canvas.getClientArea().height);
			e.gc.drawRectangle(new Rectangle(canvas.getClientArea().x, canvas.getClientArea().y, (canvas.getClientArea().width - 11), (canvas.getClientArea().height -11)));
				
			}
		});

		
	}

	@Focus
	public void setFocus() 
	{
	
	}
}
