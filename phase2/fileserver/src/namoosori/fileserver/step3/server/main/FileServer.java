package namoosori.fileserver.step3.server.main;

import namoosori.fileserver.step3.server.react.FileServerReactor;

public class FileServer {

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer(){
        FileServerReactor reactor = new FileServerReactor();
        reactor.start();
        System.out.println("서버가 실행됩니다..");
    }

}
