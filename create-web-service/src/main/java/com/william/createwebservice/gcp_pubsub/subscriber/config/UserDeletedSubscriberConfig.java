package com.william.createwebservice.gcp_pubsub.subscriber.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.william.createwebservice.gcp_pubsub.subscriber.consumers.UserDeletedConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class UserDeletedSubscriberConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeletedSubscriberConfig.class);

    private final PubSubTemplate pubSubTemplate;

    private final UserDeletedConsumer userDeletedConsumer;

    @Autowired
    public UserDeletedSubscriberConfig(PubSubTemplate pubSubTemplate, UserDeletedConsumer userDeletedConsumer) {
        this.pubSubTemplate = pubSubTemplate;
        this.userDeletedConsumer = userDeletedConsumer;
    }

    /**
     * It's called only when the application is ready to receive requests.
     * Passes a consumer implementation when subscribing to a Pub/Sub topic.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        LOGGER.info("Subscribing {} to {}", userDeletedConsumer.getClass().getSimpleName(),
                userDeletedConsumer.subscription());
        pubSubTemplate.subscribe(userDeletedConsumer.subscription(), userDeletedConsumer.consumer());
    }
}
