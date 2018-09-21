package de.trundicho.warp.reader.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("parseTextFromWebsite")
public interface TextFromWebUrlParserService extends RemoteService {
	String parseTextFromWebsite(String websiteUrl) throws IllegalArgumentException;
}
