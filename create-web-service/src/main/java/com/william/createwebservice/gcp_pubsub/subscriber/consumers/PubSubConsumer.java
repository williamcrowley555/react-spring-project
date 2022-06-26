package com.william.createwebservice.gcp_pubsub.subscriber.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;

import java.util.function.Consumer;

public abstract class PubSubConsumer {

    /**
     * Name of an existing Pub/Sub subscription.
     */
    public abstract String subscription();

    /**
     * The actual consumer logic.
     *
     * @param message Wrapper of a Pub/Sub message that adds acknowledging capability.
     */
    protected abstract void consume(BasicAcknowledgeablePubsubMessage message) throws JsonProcessingException;

    /**
     * Implementation of a {@link Consumer} functional interface which only calls the
     * {@link #consume(BasicAcknowledgeablePubsubMessage) consume} method.
     */
    public Consumer<BasicAcknowledgeablePubsubMessage> consumer() {
        return basicAcknowledgeablePubsubMessage -> {
            try {
                consume(basicAcknowledgeablePubsubMessage);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
    }

}
