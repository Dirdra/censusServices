package de.dirdra.censusEventServer.websocket;

import org.apache.camel.component.jackson.JacksonDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.dirdra.census.model.ps2v2.EventResponseType;

@Component
public class DefaultMessageHandler implements WebSocketMessageHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultMessageHandler.class);
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void handleMessage(final WebSocketMessage<?> message) {
		final Object payload = message.getPayload();
		LOG.info("message: {}", payload);
		
		if (message instanceof TextMessage) {
			handleTextMessage((TextMessage) message);
		}
	}
	
	private void handleTextMessage(final TextMessage textMessage) {
		try {
			LOG.debug("payload > {}", textMessage.getPayload());
			new JacksonDataFormat(EventResponseType.class).getObjectMapper(); //irgendwas macht das ding anders als der autowired-ObjetMapper
			final EventResponseType event = new JacksonDataFormat(EventResponseType.class).getObjectMapper().readValue(textMessage.getPayload(), EventResponseType.class);
			if (event.getPayload() != null) {
				LOG.debug("Event > {}, characterID > {}", event.getPayload().getEvent_Name(), event.getPayload().getCharacter_Id());
			}
		} catch (Exception e) {
			LOG.warn("Error on proccessing event", e);
		}
	}
}
