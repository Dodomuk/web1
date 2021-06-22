package namoosori.fileserver.step3.server.handler;

import namoosori.fileserver.step3.server.repo.FileStore;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

import java.nio.file.FileAlreadyExistsException;

public class FileStoreHandler implements FileHandler{

    public FileStoreHandler() {
    }

    public FileStore getFileStore(){
        return FileStore.newInstance();
    }


    @Override
    public ResponseMessage handle(RequestMessage request) {

        FileStore fileStore = getFileStore();
        char[] contents = request.getValue().toCharArray();
        String fileName = request.getRemark();

        ResponseMessage response = null;

        try {
            fileStore.writeFile(fileName, contents);
            response = new ResponseMessage(request.getServiceName(), fileName);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
            response = new ResponseMessage(request.getServiceName(), fileName);
            response.setSuccess(false);
            response.setReason(e.getMessage());
        }

        return response;
    }
}
