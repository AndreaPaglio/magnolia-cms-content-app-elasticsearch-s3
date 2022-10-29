package com.whitelabel.app.custom.interfaces;

import com.whitelabel.app.generic.connector.GenericDelegate;
import com.whitelabel.app.generic.entity.GenericItem;
import com.whitelabel.app.generic.search.Params;

import info.magnolia.ui.vaadin.integration.contentconnector.SupportsCreation;

public interface CustomContentConnector extends GenericContentConnector, ElasticSearchOperations, SupportsCreation {
	public GenericDelegate<? extends GenericItem> getQueryDelegate();

	public void setQueryDelegate(GenericDelegate<? extends GenericItem> delegate);

	void createConnection(Params params) throws Exception;

	public void setParams(Params params);

	public Params getParams();

}
