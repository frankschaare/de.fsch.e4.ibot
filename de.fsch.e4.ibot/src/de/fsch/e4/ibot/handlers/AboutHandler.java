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

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import de.fsch.e4.ibot.data.yahoo.IYQLConnection;

public class AboutHandler 
{
@Inject IYQLConnection con;


	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) 
	{
	con.getHistoricaldata();	
	MessageDialog.openInformation(shell, "About", "Eclipse 4 Application example.");
	}
}
