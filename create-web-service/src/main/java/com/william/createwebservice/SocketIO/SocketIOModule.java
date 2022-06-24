package com.william.createwebservice.SocketIO;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.william.createwebservice.shared.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.EventListener;

@Component
public class SocketIOModule {

    private static final Logger log = LoggerFactory.getLogger(SocketIOModule.class);

    private final SocketIONamespace namespace;
    private String onUpdateUserList = "on-updateUserList";

    @Autowired
    public SocketIOModule(SocketIOServer server) {
        this.namespace = server.addNamespace("/chat");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener("chat", SocketIOModule.class, onChatReceived());
        this.namespace.addEventListener("updateUserList", UserDTO.class, onUpdateUserList());
    }

    private DataListener<SocketIOModule> onChatReceived() {
        return (client, data, ackSender) -> {
            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
            namespace.getBroadcastOperations().sendEvent("chat", data);
        };
    }

    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            System.out.println("Connected");
            client.joinRoom(onUpdateUserList);

            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }

    private DataListener<UserDTO> onUpdateUserList() {
        return (client, data, ackSender) -> {
            System.out.println(data);
            ArrayList<SocketIOClient> newList = new ArrayList<SocketIOClient>(
                    ServerCommandLineRunner.server.getRoomOperations(onUpdateUserList).getClients());
            for (SocketIOClient s : newList) {
                    s.sendEvent("updateUserList",data);
            }
            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
        };
    }
}
