package namoosori.fileserver.step3.client.transfer;

import namoosori.fileserver.util.DispatchFailException;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;
import namoosori.fileserver.util.SocketWorker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketDispatcher {

    private static final int TIME_OUT_IN_SECONDS = 3;

    private SocketWorker socketWorker;

    public SocketDispatcher(String serverIp, int listeningPort) {
        this.socketWorker = createSocketWorker(serverIp,listeningPort);
    }

    public static SocketDispatcher getInstance(String ip, int port){
        return new SocketDispatcher(ip,port);
    }

    public void close(){
        this.socketWorker.closeSocket();
    }

    public ResponseMessage dispatchReturn(RequestMessage message){

        String json = null;

        try {
            socketWorker.writeMessage(message.toJson());
            json = socketWorker.readMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return ResponseMessage.fromJson(json);
    }

    public void dispatchVoid(RequestMessage message){
        try {
            socketWorker.writeMessage(message.toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SocketWorker createSocketWorker(String serverIp, int listeningPort){
        return new SocketWorker(this.prepareSocket(serverIp,listeningPort));
    }

    private Socket prepareSocket(String serverIp, int listeningPort){

        Socket socket = null;

        try {
            socket = new Socket();
            socket.setSoLinger(true, 0);
            socket.setReuseAddress(true);
            socket.connect(new InetSocketAddress(serverIp, listeningPort), TIME_OUT_IN_SECONDS*1000); //밀리세컨 단위로 담는다 ==> 현재 3초
        }catch(UnknownHostException e) {
            throw new DispatchFailException(e.getMessage());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
