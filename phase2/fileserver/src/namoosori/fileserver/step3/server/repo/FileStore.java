package namoosori.fileserver.step3.server.repo;

import namoosori.fileserver.step3.context.FileContext;
import namoosori.fileserver.util.FileUtil;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public class FileStore {

    private FileStore() {
    }

    public static FileStore newInstance() {
        return new FileStore();
    }

    public char[] readFile(String fileName) {
        File file = FileUtil.findFile(
                FileContext.SERVER_REPOSITORY_FOLDER,
                FileContext.STEP_NAME,
                fileName);

        if (!file.exists()) {
            return null;
        }
        return FileUtil.read(file);
    }

    public void deleteFile(String fileName) {
        FileUtil.deleteFile(
                FileContext.SERVER_REPOSITORY_FOLDER,
                FileContext.STEP_NAME,
                fileName
        );
    }

    public void writeFile(String fileName, char[] contents) throws FileAlreadyExistsException {
        File file = FileUtil.findFile(
                FileContext.SERVER_REPOSITORY_FOLDER,
                FileContext.STEP_NAME,
                fileName);
        if(file.exists()){
            throw new FileAlreadyExistsException(fileName);
        }

        file = FileUtil.createFile(
                FileContext.SERVER_REPOSITORY_FOLDER,
                FileContext.STEP_NAME,
                fileName);

        FileUtil.write(file, contents);
    }

    public List<String> listFiles(int offset, int count){
        List<String> fileList = FileUtil.findFileList(
                FileContext.SERVER_REPOSITORY_FOLDER,
                FileContext.STEP_NAME);

        List<String> resultList = new ArrayList<>();

        int totalCnt = fileList.size();
        for (int i = offset; i < count; i++) {
            if(i >= totalCnt){
                break;
            }
            resultList.add(fileList.get(i));
        }
        return resultList;
    }
}
