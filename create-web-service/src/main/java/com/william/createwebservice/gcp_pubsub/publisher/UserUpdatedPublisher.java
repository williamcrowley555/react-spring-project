package com.william.createwebservice.gcp_pubsub.publisher;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUpdatedPublisher extends PubSubPublisher {
    @Autowired
    public UserUpdatedPublisher(PubSubTemplate pubSubTemplate) {
        super(pubSubTemplate);
    }

    @Override
    protected String topic() {
        return "user-updated";
    }
}