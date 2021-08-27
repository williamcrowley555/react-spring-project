import SockJS from "sockjs-client";
import Stomp from "stompjs";

const SOCKET_URL = "http://localhost:8082/create-web-service/stomp-endpoint";

class WebSocketService {
  initializeStompClient() {
    console.log("Initializing STOMP client on url: " + SOCKET_URL);
    const socket = new SockJS(SOCKET_URL);
    return Stomp.over(socket);
  }

  sendMessage(stompClient, destination, msg) {
    console.log("Sending message");
    stompClient.send(destination, {}, JSON.stringify({ content: msg }));
  }
}

export default new WebSocketService();
