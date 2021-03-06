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
package edu.mayo.informatics.lexgrid.convert.utility.loaderPreferences.loaders;

import java.net.URI;

import org.LexGrid.LexBIG.Preferences.loader.HL7LoadPreferences.HL7LoaderPreferences;
import org.LexGrid.LexBIG.Preferences.loader.LoadPreferences.LoaderPreferences;

import edu.mayo.informatics.lexgrid.convert.exceptions.LgConvertException;
import edu.mayo.informatics.lexgrid.convert.utility.loaderPreferences.PreferenceLoaderConstants;
import edu.mayo.informatics.lexgrid.convert.utility.loaderPreferences.interfaces.PreferenceLoader;

/**
 * Class to load HL7 loader preferences from an XML file
 * 
 * @author <A HREF="mailto:peterson.kevin@mayo.edu">Kevin Peterson</A>
 */
public class HL7PreferenceLoader extends BasePreferenceLoader implements PreferenceLoader {
    /**
     * Constructor for creating an HL7PreferenceLoader. This class is used to
     * populate specific OWL constants that will be used during the load
     * 
     * @param HL7Preferences
     *            The location of the XML preferences file.
     * @param validate
     *            Whether or not to validate the preferences XML against the
     *            associated XML schema. This will look for invalid, extra, or
     *            missing values. Note that even if this is 'false', the XML
     *            will be structurally validated. This means that even if it is
     *            not validated against the schema, it will still be validated
     *            to ensure it is a well-structured XML file.
     * @throws LgConvertException
     *             Thrown if the XML is not a valid XML file, or if 'validate'
     *             is true this exception will be thrown if the XML does not
     *             validate against the schema.
     */
    public HL7PreferenceLoader(URI HL7Preferences, boolean validate) throws LgConvertException {
        if (validate) {
            if (validate(HL7Preferences, ClassLoader.getSystemResource(PreferenceLoaderConstants.HL7_XSD))) {
                prefs = HL7Preferences;
            } else {
                log.error("Validation failed, XML does not conform to the schema");
                throw new LgConvertException("Validation failed, XML does not conform to the schema");
            }
        } else {
            prefs = HL7Preferences;
        }
    }

    /**
     * Constructor for creating an HL7PreferenceLoader. This class is used to
     * populate specific HL7 constants that will be used during the load.
     * 
     * NOTE: This will by default NOT validate the XML against its schema.
     * 
     * @param HL7Preferences
     *            The location of the XML preferences file.
     * @throws LgConvertException
     *             Thrown if the XML is not a valid XML file, or if the XML does
     *             not validate against the schema.
     */
    public HL7PreferenceLoader(URI HL7Preferences) throws LgConvertException {
        this(HL7Preferences, false);
    }

    /**
     * Validates an XML preferences file against its corresponding XSD. If
     * errors are found, the exception will be logged.
     * 
     * @return false if the XML does not validate, otherwise true.
     */
    public boolean validate() {
        return super.validate(prefs, ClassLoader.getSystemResource(PreferenceLoaderConstants.HL7_XSD));
    }

    public LoaderPreferences load() throws LgConvertException {
        return unmarshal(HL7LoaderPreferences.class);
    }

}