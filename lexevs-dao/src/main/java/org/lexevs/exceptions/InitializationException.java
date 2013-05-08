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
package org.lexevs.exceptions;

/**
 * An exception to throw when things go wrong during startup of a class.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @version subversion $Revision: $ checked in on $Date: $
 */
public class InitializationException extends InternalException {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7340972251523163860L;

    /**
     * Instantiates a new initialization exception.
     * 
     * @param message the message
     */
    public InitializationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new initialization exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}