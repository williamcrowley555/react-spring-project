package com.william.createwebservice.gcp_pubsub.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.william.createwebservice.CreateWebServiceApplication;
import com.william.createwebservice.io.entity.UserEntity;
import com.william.createwebservice.socketIO.module.UserSocketModule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriber {
    private static final Log LOGGER = LogFactory.getLog(CreateWebServiceApplication.class);

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Autowired
    private UserSocketModule userSocketModule;

//     Create a message channel for messages arriving from the subscription `topic-user-sub`.
    @Bean
    public MessageChannel inputUserChannel() {
        return new PublishSubscribeChannel();
    }

    // Create an inbound channel adapter to listen to the subscription `topic-user-sub` and send
// messages to the input message channel.
    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapter(
            @Qualifier("inputUserChannel") MessageChannel messageChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "topic-user-sub");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(String.class);
        return adapter;
    }

    // Define what happens to the messages arriving in the message channel.
    @ServiceActivator(inputChannel = "inputUserChannel")
    public void messageReceiver(
            String payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) throws JsonProcessingException {
        LOGGER.info("Message arrived via an inbound channel adapter from topic-user-sub! Payload: " + payload);
        message.ack();

        userSocketModule.emit(UserSocketModule.USER_TOPIC,
                new ObjectMapper().readValue(payload, UserEntity.class),
                UserSocketModule.USER_ROOM);
    }
}
