package com.ibm.tw.servers.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oauth")
public class ClientDataConfiguration {

	private List<ClientParams> credentials;

	/**
	 * @return the params
	 */
	public List<ClientParams> getCredentials() {
		return credentials;
	}

	/**
	 * @param params the params to set
	 */
	public void setCredentials(List<ClientParams> params) {
		this.credentials = params;
	}

}
