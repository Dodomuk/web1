package namoosori.fileserver.step1;


import namoosori.fileserver.step1.folder.FileStore;

public class StoryAssistant {

    public static void main(String[] args) {
        show();
    }

    private static void show(){
        String folderName = "FileRepository";
        String fileName = "TestFile000003.txt";
        String contents = "안녕하세요. 저는 정동묵이라고 합니다.";

        FileStore folder = new FileStore(folderName);

        folder.writeFile(fileName,contents.toCharArray());

        char[] result = folder.readFile(fileName);

        System.out.println(String.valueOf(result));
    }

}
