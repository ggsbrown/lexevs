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
package org.lexevs.dao.database.ibatis.association.parameter;

import org.LexGrid.relations.AssociationEntity;
import org.lexevs.dao.database.ibatis.parameter.IdableParameterBean;

/**
 * The Class InsertAssociationPredicateBean.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class InsertOrUpdateAssociationEntityBean extends IdableParameterBean {
	
	private String entityUId;

	private AssociationEntity associationEntity;

	public void setAssociationEntity(AssociationEntity associationEntity) {
		this.associationEntity = associationEntity;
	}

	public AssociationEntity getAssociationEntity() {
		return associationEntity;
	}

	public void setEntityUId(String entityUId) {
		this.entityUId = entityUId;
	}

	public String getEntityUId() {
		return entityUId;
	}
}