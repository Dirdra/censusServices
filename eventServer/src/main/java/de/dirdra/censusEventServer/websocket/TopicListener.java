package de.dirdra.censusEventServer.websocket;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicListener implements MessageListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(TopicListener.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			final TextMessage textMessage = (TextMessage) message;
			try {
				LOG.info("textMessage: {}:", textMessage.getText());
			} catch (Exception e) {
				LOG.error("Error on receiving message", e);
			}
		}
	}
	
}
