package com.william.createwebservice.gcp_pubsub.subscriber.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.pubsub.v1.PubsubMessage;
import com.william.createwebservice.io.entity.UserEntity;
import com.william.createwebservice.socketIO.module.UserSocketModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDeletedConsumer extends PubSubConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeletedConsumer.class);

    @Override
    public String subscription() {
        return "user-deleted-sub";
    }

    @Autowired
    private UserSocketModule userSocketModule;

    @Override
    protected void consume(BasicAcknowledgeablePubsubMessage acknowledgeablePubsubMessage) throws JsonProcessingException {
        // extract wrapped message
        PubsubMessage message = acknowledgeablePubsubMessage.getPubsubMessage();

        // process message
        LOGGER.info("message received: " + message.getData().toStringUtf8());

        userSocketModule.emit(UserSocketModule.TOPIC_USER_DELETED,
                new ObjectMapper().readValue(message.getData().toStringUtf8(), UserEntity.class),
                UserSocketModule.USER_ROOM);

        // acknowledge that message was received
        acknowledgeablePubsubMessage.ack();
    }
}
