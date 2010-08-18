/*
 * Copyright: (c) 2004-2009 Mayo Foundation for Medical Education and 
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
package org.LexGrid.LexBIG.Impl.function.codednodegraph;

import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.Utility.Constructors;

/**
 * The Class ToNodeListTest.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class ToNodeListTest extends BaseCodedNodeGraphTest {

   
    /**
     * Test to node list no focus.
     * 
     * @throws Exception the exception
     */
    public void testToNodeListNoFocus() throws Exception {
        CodedNodeSet cns = cng.toNodeList(null, true, false, -1, -1);
        ResolvedConceptReference[] refs = cns.resolveToList(null, null, null, null, -1).getResolvedConceptReference();
        assertTrue(refs.length > 0);
    }
    
    /**
     * Test to node list no focus zero levels.
     * 
     * @throws Exception the exception
     */
    public void testToNodeListNoFocusZeroLevels() throws Exception {
        CodedNodeSet cns = cng.toNodeList(null, true, false, 0, -1);
        ResolvedConceptReference[] refs = cns.resolveToList(null, null, null, null, -1).getResolvedConceptReference();
        assertEquals(2,refs.length);
    }
     
    /**
     * Test to node list with focus one level.
     * 
     * @throws Exception the exception
     */
    public void testToNodeListWithFocusOneLevel() throws Exception {
        CodedNodeSet cns = cng.toNodeList(Constructors.createConceptReference("005", AUTO_SCHEME), true, false, 1, -1);
        ResolvedConceptReference[] refs = cns.resolveToList(
                null,
                null, 
                null, 
                -1).getResolvedConceptReference();
        assertTrue("Length: " + refs.length, refs.length == 4);
    }
    
    public void testUnionToNodeList() throws Exception {
        CodedNodeSet cns1 = cng.toNodeList(Constructors.createConceptReference("005", AUTO_SCHEME), true, false, 1, -1);
        CodedNodeSet cns2 = cng.toNodeList(Constructors.createConceptReference("A0001", AUTO_SCHEME), true, false, 1, -1);
        
        CodedNodeSet union = cns1.union(cns2);
        ResolvedConceptReference[] refs = union.resolveToList(
                null,
                null, 
                null, 
                -1).getResolvedConceptReference();
        assertEquals(10,refs.length);
    }
    
    public void testUnionToNodeListNotInCodedNodeSet() throws Exception {
        CodedNodeSet cns1 = cng.toNodeList(Constructors.createConceptReference("Batteries", AUTO_SCHEME), true, false, 1, -1);
        CodedNodeSet cns2 = cng.toNodeList(Constructors.createConceptReference("Tires", AUTO_SCHEME), true, false, 1, -1);
        
        CodedNodeSet union = cns1.union(cns2);
        ResolvedConceptReference[] refs = union.resolveToList(
                null,
                null, 
                null, 
                -1).getResolvedConceptReference();
        assertEquals(2,refs.length);
    }
    
    public void testUnionToNodeListSomeInSomeNotInCodedNodeSet() throws Exception {
        CodedNodeSet cns1 = cng.toNodeList(Constructors.createConceptReference("A0001", AUTO_SCHEME), true, false, -1, -1);
        CodedNodeSet cns2 = cng.toNodeList(Constructors.createConceptReference("Tires", AUTO_SCHEME), true, false, -1, -1);
        
        CodedNodeSet union = cns1.union(cns2);
        ResolvedConceptReference[] refs = union.resolveToList(
                null,
                null, 
                null, 
                -1).getResolvedConceptReference();
        assertEquals(8,refs.length);
        
        assertEquals("T0001",refs[0].getCode());
    }
    
  
}
