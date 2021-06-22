package namoosori.fileserver.step2.server.main;

import namoosori.fileserver.step2.context.FileContext;
import namoosori.fileserver.step2.server.react.FileServerReactor;

public class FileServer {

    public static void main(String[] args) {

    }

    private static void startServer(){
        FileServerReactor reactor = new FileServerReactor();
        reactor.start();
        System.out.println("파일 서버가 시작 중입니다..." + FileContext.STEP_NAME);
    }
}
