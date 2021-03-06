package namoosori.fileserver.step3.server.handler;

import namoosori.fileserver.step3.server.repo.FileStore;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

public class FileFindHandler implements FileHandler{

    public FileFindHandler() {
    }

    public FileStore getFileStore(){
        return FileStore.newInstance();
    }

    @Override
    public ResponseMessage handle(RequestMessage request) {

        FileStore fileStore = getFileStore();
        String fileName = request.getValue();

        char[] fileContents = fileStore.readFile(fileName);

        return new ResponseMessage(request.getServiceName(),String.valueOf(fileContents));
    }
}
