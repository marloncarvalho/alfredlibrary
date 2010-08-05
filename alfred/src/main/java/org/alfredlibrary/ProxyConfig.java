package org.alfredlibrary;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

public class ProxyConfig {
	private String host;
	private int port;
	private Proxy proxy;
	
	public ProxyConfig(String host, int port, Type proxyType) {
		this.setHost(host);
		this.setPort(port);
		this.proxy = new Proxy(proxyType, new InetSocketAddress(host, port));
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

}