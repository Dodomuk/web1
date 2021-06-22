package namoosori.fileserver.step3.server.handler;

import com.google.gson.Gson;
import namoosori.fileserver.step3.server.repo.FileStore;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

import java.util.List;
import java.util.StringTokenizer;

public class FileListHandler implements FileHandler{

    public FileListHandler() {
    }

    public FileStore getFileStore(){
        return FileStore.newInstance();
    }

    @Override
    public ResponseMessage handle(RequestMessage request) {

        FileStore fileStore = getFileStore();

        String value = request.getValue();
        System.out.println(value.toString());
        StringTokenizer st = new StringTokenizer(value,"-");

        int offset = Integer.valueOf(st.nextToken());
        int count = Integer.valueOf(st.nextToken());

        List<String> fileList = fileStore.listFiles(offset,count);

        return new ResponseMessage(request.getServiceName(),new Gson().toJson(fileList));
    }
}
