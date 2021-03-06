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
package org.lexgrid.loader.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

/**
 * The Class AbstractParameterPassingListProcessor.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public abstract class AbstractParameterPassingListProcessor<I,O> implements ItemProcessor<List<I>,List<O>>{

	/** The delegate. */
	private ItemProcessor<I,O>  delegate;
	

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	public List<O> process(List<I> items) throws Exception {
		items = beforeProcessing(items);
		List<O> buffer = new ArrayList<O>();
		for(I item : items){
			buffer.add(delegate.process(item));
		}
		return afterProcessing(buffer, items);
	}
	
	/**
	 * Before processing.
	 * 
	 * @param items the items
	 * 
	 * @return the list< i>
	 */
	protected abstract List<I> beforeProcessing(List<I> items);
	
	/**
	 * After processing.
	 * 
	 * @param processedItems the processed items
	 * @param originalItems the original items
	 * 
	 * @return the list< o>
	 */
	protected abstract List<O> afterProcessing(List<O> processedItems, List<I> originalItems);

	/**
	 * Gets the delegate.
	 * 
	 * @return the delegate
	 */
	public ItemProcessor<I, O> getDelegate() {
		return delegate;
	}

	/**
	 * Sets the delegate.
	 * 
	 * @param delegate the delegate
	 */
	public void setDelegate(ItemProcessor<I, O> delegate) {
		this.delegate = delegate;
	}
}