/*
 * Copyright: (c) 2004-2010 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 * 
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * 		http://www.eclipse.org/legal/epl-v10.html
 * 
 */
package org.lexevs.dao.database.service.event.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.lexevs.dao.database.service.event.DatabaseServiceEventListener;

/**
 * The Class BaseListenerRegistry.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class BaseListenerRegistry implements ListenerRegistry {
	
	/** The empty listener list. */
	private static List<DatabaseServiceEventListener> EMPTY_LISTENER_LIST = 
		Collections.unmodifiableList(new ArrayList<DatabaseServiceEventListener>());

	/** The database service event listeners. */
	private Map<String,DatabaseServiceEventListener> databaseServiceEventListeners = 
		new WeakHashMap<String,DatabaseServiceEventListener>();

	/** The enable listeners. */
	private boolean enableListeners = true;
	
	/**
	 * Sets the database service event listeners.
	 * 
	 * @param databaseServiceEventListeners the new database service event listeners
	 */
	public void setDatabaseServiceEventListeners(
			List<DatabaseServiceEventListener> databaseServiceEventListeners) {
		this.databaseServiceEventListeners.clear();
		if(CollectionUtils.isNotEmpty(databaseServiceEventListeners)) {
			for(DatabaseServiceEventListener listener : databaseServiceEventListeners) {
				this.registerListener(listener);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.service.event.registry.ListenerRegistry#getRegisteredListener(java.lang.String)
	 */
	@Override
	public DatabaseServiceEventListener getRegisteredListener(String listenerId) {
		return databaseServiceEventListeners.get(listenerId);
	}
	
	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.service.event.registry.ListenerRegistry#registerListener(java.lang.String, org.lexevs.dao.database.service.event.DatabaseServiceEventListener)
	 */
	@Override
	public void registerListener(String listenerId, DatabaseServiceEventListener listener) {
		databaseServiceEventListeners.put(listenerId, listener);
	}

	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.service.event.registry.ListenerRegistry#registerListener(org.lexevs.dao.database.service.event.DatabaseServiceEventListener)
	 */
	@Override
	public String registerListener(DatabaseServiceEventListener listener) {
		String key = this.createListenerId();
		this.registerListener(key, listener);
		return key;
	}

	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.service.event.registry.ListenerRegistry#unregisterListener(java.lang.String)
	 */
	@Override
	public void unregisterListener(String listenerId) {
		databaseServiceEventListeners.remove(listenerId);
	}

	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.service.event.registry.ListenerRegistry#getRegisteredListeners()
	 */
	@Override
	public Collection<DatabaseServiceEventListener> getRegisteredListeners() {
		if(enableListeners) {
			return databaseServiceEventListeners.values();
		} else {
			return EMPTY_LISTENER_LIST;
		}
	}

	/**
	 * Checks if is enable listeners.
	 * 
	 * @return true, if is enable listeners
	 */
	public boolean isEnableListeners() {
		return enableListeners;
	}

	/**
	 * Sets the enable listeners.
	 * 
	 * @param enableListeners the new enable listeners
	 */
	public void setEnableListeners(boolean enableListeners) {
		this.enableListeners = enableListeners;
	}
	
	/**
	 * Creates the listener id.
	 * 
	 * @return the string
	 */
	protected String createListenerId() {
		return UUID.randomUUID().toString();
	}
}