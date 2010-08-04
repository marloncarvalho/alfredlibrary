package org.alfredlibrary;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.Proxy.Type;

public class ProxyConfig {
	private Proxy proxy;
	private String host;
	private int port;
	
	public ProxyConfig(String host, int port, Type proxyType) {
		this.setHost(host);
		this.setPort(port);
		SocketAddress sa = new InetSocketAddress(host, port);
		this.setProxy(new Proxy(proxyType, sa));
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