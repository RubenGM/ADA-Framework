package com.desandroid.ormlite_ada.model.helpers;

import android.content.Context;

import com.desandroid.framework.ada.ObjectContext;
import com.desandroid.framework.ada.ObjectSet;
import com.desandroid.framework.ada.exceptions.AdaFrameworkException;
import com.desandroid.ormlite_ada.model.entities.AdaFrameworkEntity;

public class AdaFrameworkHelper extends ObjectContext {

	private ObjectSet<AdaFrameworkEntity> entitiesSet;
	public ObjectSet<AdaFrameworkEntity> getEntitiesSet() { return entitiesSet; }
	
	public AdaFrameworkHelper(Context pContext) throws AdaFrameworkException {
		super(pContext, "AdaFrameworkDatabase.db");
		
		initializeHelper();
	}
	
	private void initializeHelper() throws AdaFrameworkException {
		entitiesSet = new ObjectSet<AdaFrameworkEntity>(AdaFrameworkEntity.class, this);
	}
}
