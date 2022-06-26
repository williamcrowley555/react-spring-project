package com.william.createwebservice.gcp_pubsub.subscriber.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.william.createwebservice.gcp_pubsub.subscriber.consumers.UserUpdatedConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class UserUpdatedSubscriberConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdatedSubscriberConfig.class);

    private final PubSubTemplate pubSubTemplate;

    private final UserUpdatedConsumer userUpdatedConsumer;

    @Autowired
    public UserUpdatedSubscriberConfig(PubSubTemplate pubSubTemplate, UserUpdatedConsumer userUpdatedConsumer) {
        this.pubSubTemplate = pubSubTemplate;
        this.userUpdatedConsumer = userUpdatedConsumer;
    }

    /**
     * It's called only when the application is ready to receive requests.
     * Passes a consumer implementation when subscribing to a Pub/Sub topic.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        LOGGER.info("Subscribing {} to {}", userUpdatedConsumer.getClass().getSimpleName(),
                userUpdatedConsumer.subscription());
        pubSubTemplate.subscribe(userUpdatedConsumer.subscription(), userUpdatedConsumer.consumer());
    }
}
