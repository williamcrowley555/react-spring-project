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
    public static final String JOIN_USER_ROOM_TOPIC = "join-user-room-topic";
    public static final String USER_TOPIC = "user-topic";
    private final SocketIONamespace namespace;

    @Autowired
    public UserSocketModule(SocketIOServer server) {
        this.namespace = server.addNamespace("/chat");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener(JOIN_USER_ROOM_TOPIC, String.class, onJoinUserRoom());
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
