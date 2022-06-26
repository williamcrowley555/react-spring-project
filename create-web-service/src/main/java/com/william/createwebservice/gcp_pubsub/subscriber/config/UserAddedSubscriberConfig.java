package com.william.createwebservice.gcp_pubsub.subscriber.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.william.createwebservice.gcp_pubsub.subscriber.consumers.UserAddedConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class UserAddedSubscriberConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAddedSubscriberConfig.class);

    private final PubSubTemplate pubSubTemplate;

    private final UserAddedConsumer userAddedConsumer;

    @Autowired
    public UserAddedSubscriberConfig(PubSubTemplate pubSubTemplate, UserAddedConsumer userAddedConsumer) {
        this.pubSubTemplate = pubSubTemplate;
        this.userAddedConsumer = userAddedConsumer;
    }

    /**
     * It's called only when the application is ready to receive requests.
     * Passes a consumer implementation when subscribing to a Pub/Sub topic.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        LOGGER.info("Subscribing {} to {}", userAddedConsumer.getClass().getSimpleName(),
                userAddedConsumer.subscription());
        pubSubTemplate.subscribe(userAddedConsumer.subscription(), userAddedConsumer.consumer());
    }
}
