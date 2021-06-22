package namoosori.fileserver.step2.server.handler;

import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

public interface FileHandler {

    public ResponseMessage handle(RequestMessage request);
}
