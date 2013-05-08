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
package org.lexevs.logging.messaging.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.LexGrid.LexBIG.DataModel.Core.LogEntry;
import org.LexGrid.LexBIG.DataModel.Core.types.LogLevel;
import org.LexGrid.LexBIG.Utility.logging.CachingMessageDirectorIF;
import org.LexGrid.LexBIG.Utility.logging.LgMessageDirectorIF;

/**
 * The class implements the CachingMessageDirectorIF interface and is used to
 * log the messages generated by programs into a Collection so they can be
 * easily retrieved for later use. This takes in another LgMessageDirectorIF -
 * all messages are passed down to this message director for further action.
 * 
 * @author <A HREF="mailto:sharma.deepak2@mayo.edu">Deepak Sharma</A>
 * @author <A HREF="mailto:kanjamala.pradip@mayo.edu"> Pradip Kanjamala</A>
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu"> Dan Armbrust</A>
 */
public class CachingMessageDirectorImpl implements CachingMessageDirectorIF {
    
    /** The base message director_. */
    protected LgMessageDirectorIF baseMessageDirector_ = null;
   
    /** The log entries. */
    private List<LogEntry> logEntries = new ArrayList<LogEntry>();
    
    private int messageNumber = 0;

    /**
     * Instantiates a new caching message director impl.
     * 
     * @param msgDirector the msg director
     */
    public CachingMessageDirectorImpl(LgMessageDirectorIF msgDirector) {
        this.baseMessageDirector_ = msgDirector;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#debug
     * (java.lang.Object)
     */
    public String debug(String message) {
        addMsg(LogLevel.DEBUG, message);
        return baseMessageDirector_.debug(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#error
     * (java.lang.Object, java.lang.Throwable)
     */
    public String error(String message, Throwable cause) {
        addMsg(LogLevel.ERROR, message, cause.getMessage());
        return baseMessageDirector_.error(message, cause);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#error
     * (java.lang.Object)
     */
    public String error(String message) {
        addMsg(LogLevel.ERROR, message);

        return baseMessageDirector_.error(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#fatal
     * (java.lang.Object, java.lang.Throwable)
     */
    public String fatal(String message, Throwable cause) {

        addMsg(LogLevel.FATAL, message, cause.getMessage());

        return baseMessageDirector_.fatal(message, cause);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#fatal
     * (java.lang.Object)
     */
    public String fatal(String message) {
        addMsg(LogLevel.FATAL, message);

        return baseMessageDirector_.fatal(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#info
     * (java.lang.Object)
     */
    public String info(String message) {

        addMsg(LogLevel.INFO, message);

        return baseMessageDirector_.info(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#warn
     * (java.lang.Object, java.lang.Throwable)
     */
    public String warn(String message, Throwable cause) {
        addMsg(LogLevel.WARN, message, cause.getMessage());

        return baseMessageDirector_.warn(message, cause);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.mayo.informatics.resourcereader.core.impl.ResourceLoggerImpl#warn
     * (java.lang.Object)
     */
    public String warn(String message) {
        addMsg(LogLevel.WARN, message);

        return baseMessageDirector_.warn(message);
    }

    /**
     * Adds the msg.
     * 
     * @param level the level
     * @param msg the msg
     * @param exceptionMsg the exception msg
     */
    private void addMsg(LogLevel level, String msg, String exceptionMsg) {

        LogEntry entry = new LogEntry();
        entry.setMessageNumber(messageNumber++);
        entry.setEntryLevel(level);
        entry.setEntryTime(new Date());
        entry.setMessage(msg + " - " + exceptionMsg);
        
        this.logEntries.add(entry);
    }

    /**
     * Adds the msg.
     * 
     * @param level the level
     * @param msg the msg
     */
    private void addMsg(LogLevel level, String msg) {
        addMsg(level, msg, "");
    }


    /* (non-Javadoc)
     * @see org.LexGrid.LexBIG.Utility.logging.LgMessageDirectorIF#busy()
     */
    public void busy() {
    }

    /* (non-Javadoc)
     * @see org.LexGrid.LexBIG.Utility.logging.LgMessageDirectorIF#fatalAndThrowException(java.lang.String)
     */
    public void fatalAndThrowException(String message) throws Exception {
        fatalAndThrowException(message, null);

    }

    /* (non-Javadoc)
     * @see org.LexGrid.LexBIG.Utility.logging.LgMessageDirectorIF#fatalAndThrowException(java.lang.String, java.lang.Throwable)
     */
    public void fatalAndThrowException(String message, Throwable sourceException) throws Exception {
        fatal(message, sourceException);
        baseMessageDirector_.fatalAndThrowException(message, sourceException);
        throw new Exception(message, sourceException);
    }

	/* (non-Javadoc)
	 * @see org.LexGrid.LexBIG.Utility.logging.CachingMessageDirectorIF#clearLog()
	 */
	@Override
	public void clearLog() {
		this.logEntries.clear();
	}

	/* (non-Javadoc)
	 * @see org.LexGrid.LexBIG.Utility.logging.CachingMessageDirectorIF#getLog(org.LexGrid.LexBIG.DataModel.Core.types.LogLevel)
	 */
	@Override
	public LogEntry[] getLog(LogLevel level) {
		if(level == null) {
			return logEntries.toArray(new LogEntry[logEntries.size()]);
		}
	
		List<LogEntry> entries = new ArrayList<LogEntry>();
		for(LogEntry entry : this.logEntries) {
			if(entry.getEntryLevel().equals(level)) {
				entries.add(entry);
			}
		}
		return entries.toArray(new LogEntry[entries.size()]);
	}
}