package com.william.createwebservice.ui.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.william.createwebservice.CreateWebServiceApplication;
import com.william.createwebservice.gcp_pubsub.PubSubPublisher;
import com.william.createwebservice.gcp_pubsub.igateway.PubsubOutboundGateway;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
public class PubSubController {
    private static final Log LOGGER = LogFactory.getLog(CreateWebServiceApplication.class);

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${pubsub.topic.id}")
    private String topicId;

    @Autowired
    private PubsubOutboundGateway messagingGateway;

    @PostMapping("/publishMessage")
    public ResponseEntity<?> publishMessage(@RequestParam("message") String message)
            throws IOException, ExecutionException, InterruptedException {
        messagingGateway.sendToPubsub(message);

        return new ResponseEntity<>("Sending message: '" + message + "'", HttpStatus.OK);
    }
}
