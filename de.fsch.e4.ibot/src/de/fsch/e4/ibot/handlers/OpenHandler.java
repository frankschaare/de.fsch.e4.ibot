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
package de.fsch.e4.ibot.handlers;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.fsch.e4.ibot.asset.Quote;
import de.fsch.e4.ibot.asset.Stock;
import de.fsch.e4.ibot.data.csv.yahoo.CSVDatei;
import de.fsch.e4.ibot.database.IDataService;

public class OpenHandler 
{
@Inject IDataService dataService;	

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell)
	{
	Stock stock = new Stock();	
	stock.setIsin("DE0005557508");
	
	Quote q = null;
	ArrayList<Quote> quotes = new ArrayList<Quote>();
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	FileDialog dialog = new FileDialog(shell);
	dialog.setFilterExtensions(new String[] {"*.csv","*.txt", "*.*"});
	String path = dialog.open();

		if (path != null)
		{
		CSVDatei csv = new CSVDatei(path);
		csv.setHasHeader(true);
		csv.setDelimiter(",");

		csv.read();
		
			for (String[] fields : csv.getFields())
			{
			q = new Quote();
			
			q.setType(Quote.Types.DAY);
				try
				{
				q.setDate(df.parse(fields[0]));
				}
				catch (ParseException e)
				{
				e.printStackTrace();
				}
			q.setOpen(Double.parseDouble(fields[1]));	
			q.setHigh(Double.parseDouble(fields[2]));
			q.setLow(Double.parseDouble(fields[3]));
			q.setClose(Double.parseDouble(fields[4]));
			q.setVolume(Integer.parseInt(fields[5]));
			q.setAdjClose(Double.parseDouble(fields[6]));
			
			quotes.add(q);
			}
		stock.setQuotes(quotes);	
		SQLException e = dataService.setQuotes(stock);
		}
	
	}
}
