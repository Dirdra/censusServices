package de.dirdra.censusEventServer.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

@Component
@Profile("loggingHandler")
@Primary
public class LoggingHandler implements WebSocketMessageHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(LoggingHandler.class);

    @Override
    public void handleMessage(WebSocketMessage<?> message) {
        LOG.info("class of message > {}", message.getClass());
        
        if (message instanceof TextMessage) {
            handleTextMessage((TextMessage) message);
        }

    }
    
    private void handleTextMessage(TextMessage textMessage) {
        LOG.info("textmessage > {}", textMessage.getPayload());
    }

}
