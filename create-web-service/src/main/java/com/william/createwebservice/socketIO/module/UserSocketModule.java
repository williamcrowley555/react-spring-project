package com.william.createwebservice.socketIO.module;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.william.createwebservice.socketIO.ServerCommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserSocketModule implements SocketIOModule{
    public static final String USER_ROOM = "user-room";

//    TOPIC
    public static final String TOPIC_JOIN_USER_ROOM = "join-user-room";
    public static final String TOPIC_USER_ADDED = "user-added";
    public static final String TOPIC_USER_UPDATED = "user-updated";
    public static final String TOPIC_USER_DELETED = "user-deleted";

    private final SocketIONamespace namespace;

    @Autowired
    public UserSocketModule(SocketIOServer server) {
        this.namespace = server.addNamespace("/chat");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener(TOPIC_JOIN_USER_ROOM, String.class, onJoinUserRoom());
    }

    @Override
    public DataListener<SocketIOModule> onEventReceived(String event) {
        return null;
    }

    @Override
    public ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            System.out.println("Connected");
            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
        };
    }

    @Override
    public DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }

    private DataListener<String> onJoinUserRoom() {
        return (client, data, ackSender) -> {
            client.joinRoom(USER_ROOM);
            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
        };
    }

    public void emit(String topic, Object data, String... room) {
        if(room.length > 0) {
            for(int i = 0; i < room.length; i++) {
                ArrayList<SocketIOClient> clients = new ArrayList<SocketIOClient>(
                        ServerCommandLineRunner.server.getRoomOperations(room[i]).getClients());
                for (SocketIOClient client : clients) {
                    client.sendEvent(topic, data);
                }
            }
        }
    }

}
