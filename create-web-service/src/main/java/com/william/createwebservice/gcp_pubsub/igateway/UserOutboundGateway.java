package com.william.createwebservice.gcp_pubsub.igateway;


import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outputUserChannel")
public interface UserOutboundGateway {
    void sendToPubSub(String text);
}
