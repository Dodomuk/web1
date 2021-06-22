package namoosori.fileserver.step2.server.react;

import namoosori.fileserver.step2.context.FileCommand;
import namoosori.fileserver.step2.server.handler.*;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;
import namoosori.fileserver.util.SocketWorker;

import java.io.IOException;
import java.net.Socket;

public class EventRouter {

    private SocketWorker socketWorker;

    public EventRouter(Socket socket) {
        this.socketWorker = new SocketWorker(socket);
    }

    public void route(){
        String json = socketWorker.readMessage();

        RequestMessage request = RequestMessage.fromJson(json);

        String serviceName = request.getServiceName();
        FileCommand command = FileCommand.valueOf(serviceName);

        FileHandler fileHandler = null;

        switch(command){
            case Store :
                fileHandler = new FileStoreHandler();
                break;
            case Delete :
                fileHandler = new FileDeleteHandler();
                break;
            case Find :
                fileHandler = new FileListHandler();
                break;
            case ListFiles :
                fileHandler = new FileFindHandler();
                break;
        }

        ResponseMessage response = fileHandler.handle(request);

        try{
            socketWorker.writeMessage(response.toJson());
        }catch (IOException e){
            e.printStackTrace();
        }
        socketWorker.closeSocket();
    }
}
