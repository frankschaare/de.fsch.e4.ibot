/**
 * 
 */
package de.fsch.e4.ibot;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;

import de.fsch.e4.ibot.database.IDataService;

/**
 * @author hit
 * @since 21.07.2013
 *
 */
public class LifeCycleManager
{
@Inject IDataService dataService;
// @Inject @Optional private MApplication application;
	

	/**
	 * 
	 */
	public LifeCycleManager() 
	{
		
	}
	
	@PostContextCreate
	public void startup(IEclipseContext context) 
	{
	// context.set(AppConstants.LOGGER, new ContextLogger(context, broker));	
	}

}
