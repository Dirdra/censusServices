package de.dirdra.censusEventServer;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class AppConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
	
	@Value("${dbg.census.push.url}")
	private String censusURL = "wss://push.planetside2.com/streaming?environment=ps2&service-id=s:";
	
	private WebSocketConnectionManager webSocketConnectionManager;
	
	@Bean
	public WebSocketConnectionManager connectionManager(@Value("${websocket.connectionManager.autostart:true") String autostart, WebSocketClient webSocketClient, WebSocketHandler webSocketHandler) {
		LOG.info("censusurl > {}", censusURL);
		final WebSocketConnectionManager manager = new WebSocketConnectionManager(webSocketClient, webSocketHandler, censusURL);
		manager.setAutoStartup(Boolean.valueOf(autostart));
		manager.start();
		
		LOG.debug("connectionManager created: {}", manager);
		this.webSocketConnectionManager = manager;
		return manager;
	}

	@Bean
	public WebSocketHandler webSocketHandler() {
		final WebSocketHandler handler = new de.dirdra.censusEventServer.websocket.WebSocketHandler();
		
		LOG.debug("websocketHandler created: {}", handler);
		return handler;
	}

	@Bean
	public WebSocketClient websocketClient() {
		final StandardWebSocketClient client = new StandardWebSocketClient();
		
		LOG.debug("websocketClient created: {}", client);
		return client;
	}
	
	@PreDestroy
	public void onShutdown() {
	    if (webSocketConnectionManager != null) {
	        webSocketConnectionManager.stop();
	    } else {
	        LOG.warn("webSocketConnectionManager is null");
	    }
	}
}
