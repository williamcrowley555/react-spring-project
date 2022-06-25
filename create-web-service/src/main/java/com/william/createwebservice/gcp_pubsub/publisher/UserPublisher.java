package com.william.createwebservice.gcp_pubsub.publisher;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.william.createwebservice.CreateWebServiceApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class UserPublisher {
    private static final Log LOGGER = LogFactory.getLog(CreateWebServiceApplication.class);

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${pubsub.topic.id}")
    private String topicId;

    // Create an outbound channel adapter to send messages from the input message channel to the
// topic `topic-two`.
    @Bean
    @ServiceActivator(inputChannel = "outputUserChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        PubSubMessageHandler adapter = new PubSubMessageHandler(pubsubTemplate, topicId);

        adapter.setSuccessCallback(
                ((ackId, message) ->
                        LOGGER.info("Message: '" + message + "' was sent via the outbound channel adapter to topic-user!")));

        adapter.setFailureCallback(
                (cause, message) -> LOGGER.info("Error sending " + message + " due to " + cause));

        return adapter;
    }
}
