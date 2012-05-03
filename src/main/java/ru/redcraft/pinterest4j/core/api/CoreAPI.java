package ru.redcraft.pinterest4j.core.api;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class CoreAPI {

	protected final PinterestAccessToken accessToken; 
	protected final InternalAPIManager apiManager;
	
	private static final String PINTEREST_DOMAIN = "pinterest.com";
	private static final String COOKIE_HEADER_NAME = "Cookie";
	
	public enum Protocol {HTTP, HTTPS};
	
	CoreAPI(PinterestAccessToken accessToken, InternalAPIManager apiManager) {
		this.accessToken = accessToken;
		this.apiManager = apiManager;
	}
	
	protected WebResource.Builder getWR(Protocol protocol, String url) {
		
		return getWR(protocol, url, true);
	}
	
	protected WebResource.Builder getWR(Protocol protocol, String url, boolean useAJAX) {
		
		WebResource.Builder wr = null;
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		String requestURL = String.format("%s://%s/%s", protocol.name().toLowerCase(), PINTEREST_DOMAIN, url);
		wr = client.resource(UriBuilder.fromUri(requestURL).build()).getRequestBuilder();
		if(accessToken != null) {
			wr = wr.header(COOKIE_HEADER_NAME, accessToken.generateCookieHeader());
			wr = wr.header("X-CSRFToken", accessToken.getCsrfToken().getValue());
			if(useAJAX) {
				wr = wr.header("X-Requested-With", "XMLHttpRequest");
			}
		}
		return wr;
	}
	
}