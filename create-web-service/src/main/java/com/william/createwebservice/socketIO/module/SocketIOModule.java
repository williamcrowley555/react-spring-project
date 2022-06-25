package com.william.createwebservice.socketIO.module;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.william.createwebservice.shared.dto.UserDTO;
import com.william.createwebservice.socketIO.ServerCommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

interface SocketIOModule {
     static final Logger log = LoggerFactory.getLogger(SocketIOModule.class);
     DataListener<SocketIOModule> onEventReceived(String event);
     ConnectListener onConnected();
     DisconnectListener onDisconnected();
}
