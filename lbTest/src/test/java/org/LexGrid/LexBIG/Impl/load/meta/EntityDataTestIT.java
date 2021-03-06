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
package org.LexGrid.LexBIG.Impl.load.meta;

import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;

import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.junit.Test;

/**
 * The Class EntityDataTestIT.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class EntityDataTestIT extends DataLoadTestBase {

	/**
	 * Test load.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testEntityCode() throws Exception {
		cns.restrictToCodes(Constructors.createConceptReferenceList("C0000005"));
		ResolvedConceptReference rcr = cns.resolveToList(null, null, null, -1).getResolvedConceptReference()[0];
		assertTrue(rcr.getCode().equals("C0000005"));
		assertTrue(rcr.getEntity().getEntityCode().equals("C0000005"));
	}
	
	public static junit.framework.Test suite() {  
		return new JUnit4TestAdapter(DefinitionPropertyDataTestIT.class);  
	}  
}