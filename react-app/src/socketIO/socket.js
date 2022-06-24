import io from 'socket.io-client'

const HOST = 'localhost'
const PORT = 81
const PROTOCOL = 'http://'
const NAMESPACE = '/chat'
const OPTIONS = {
    transports: ['websocket', 'polling', 'flashsocket']
}
const ENDPOINT = PROTOCOL + HOST + ':'+ PORT + NAMESPACE;

export default io(ENDPOINT, OPTIONS)