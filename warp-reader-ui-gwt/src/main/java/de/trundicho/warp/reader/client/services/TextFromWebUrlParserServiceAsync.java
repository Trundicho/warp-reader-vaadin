package de.trundicho.warp.reader.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>BoilerplateService</code>.
 */
public interface TextFromWebUrlParserServiceAsync {
	void parseTextFromWebsite(String websiteUrl, AsyncCallback<String> callback) throws IllegalArgumentException;
}
