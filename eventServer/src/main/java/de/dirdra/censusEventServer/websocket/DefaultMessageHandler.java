package de.dirdra.censusEventServer.websocket;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.dirdra.census.model.ps2v2.Event;
import de.dirdra.census.model.ps2v2.EventResponseType;

public class DefaultMessageHandler implements WebSocketMessageHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultMessageHandler.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void handleMessage(final WebSocketMessage<?> message) {
		final Object payload = message.getPayload();
		LOG.info("message: {}", payload);
		
		if (message instanceof TextMessage) {
			handleTextMessage((TextMessage) message);
		}
		
		
//		jmsTemplate.send(new ActiveMQTopic("myTopic"), new MessageCreator() {
//			
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				final ActiveMQTextMessage createdMessage = new ActiveMQTextMessage();
//				createdMessage.setText(message.getPayload().toString());
//				return createdMessage;
//			}
//		});
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

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
