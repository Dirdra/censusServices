package de.dirdra.censusEventServer.websocket;

import org.springframework.web.socket.WebSocketMessage;

public interface WebSocketMessageHandler {
	void handleMessage(final WebSocketMessage<?> message);
}
