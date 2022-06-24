package com.william.createwebservice.gcp_pubsub.igateway;


import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outputMessageChannel")
public interface PubsubOutboundGateway {
    void sendToPubsub(String text);
}
