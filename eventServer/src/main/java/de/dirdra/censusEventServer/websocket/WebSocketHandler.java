package de.dirdra.censusEventServer.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketHandler implements org.springframework.web.socket.WebSocketHandler {
	private static final Logger LOG = LoggerFactory.getLogger(WebSocketHandler.class);
	
	@Autowired
	private WebSocketMessageHandler messageHandler;
	

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOG.debug("Connection established");
		final WebSocketMessage<?> message = new TextMessage("{\"service\":\"event\",\"action\":\"subscribe\",\"worlds\":[\"13\"],\"eventNames\":[\"PlayerLogin\",\"PlayerLogout\"]}");
		session.sendMessage(message);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		LOG.debug("Message received: {}", message.getPayload());
		if (messageHandler != null) {
			messageHandler.handleMessage(message);
		}
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		LOG.error("handling transport error");		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		LOG.debug("connection closed");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	public WebSocketMessageHandler getMessageHandler() {
		return messageHandler;
	}
	
	public void setMessageHandler(WebSocketMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
}
