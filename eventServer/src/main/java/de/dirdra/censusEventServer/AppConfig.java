package de.dirdra.censusEventServer;

import javax.annotation.PreDestroy;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import de.dirdra.censusEventServer.websocket.DefaultMessageHandler;
import de.dirdra.censusEventServer.websocket.TopicListener;
import de.dirdra.censusEventServer.websocket.WebSocketMessageHandler;

@Configuration
@EnableJms
public class AppConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
	
	@Value("${dbg.census.push.url}")
	private String censusURL = "wss://push.planetside2.com/streaming?environment=ps2&service-id=s:";
	
	@Autowired
	private WebSocketConnectionManager webSocketConnectionManager;
	
	@Bean
	@Autowired
	public WebSocketConnectionManager connectionManager(@Value("${websocket.connectionManager.autostart:true") String autostart) {
		LOG.info("censusurl > {}", censusURL);
		final WebSocketConnectionManager manager = new WebSocketConnectionManager(websocketClient(), webSocketHandler(), censusURL);
		manager.setAutoStartup(Boolean.valueOf(autostart));
		manager.start();
		
		LOG.debug("connectionManager created: {}", manager);
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
	
	@Bean
	public WebSocketMessageHandler createWebSocketMessageHandler() {
		return new DefaultMessageHandler();
	}
	
	@Bean
	public TopicListener createTopicListener() {
		return new TopicListener();
	}
	
	@Bean
	public DefaultMessageListenerContainer createMessageListenerContainer(ConnectionFactory connectionFactory) {
		final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		final Destination destination = new ActiveMQTopic("myTopic");
		container.setDestination(destination);
		container.setMessageListener(createTopicListener());
		return container;
	}
	
	@PreDestroy
	public void onShutdown() {
		webSocketConnectionManager.stop();
	}
}
