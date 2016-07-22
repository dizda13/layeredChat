package socketComunication;

import tranferLayer.ITransferLayer;

import java.io.IOException;

/**
 * Created by Dino on 22.7.2016.
 */
public interface ISocketComunication {
    public void sendLine(String msg);
    public void closeConnection() throws IOException;
    public void setConnectionParametars(String ip, String port) throws IOException;
    public void setITransferLayer(ITransferLayer iTransferLayer);
    public void setConnectionParametars(String port) throws IOException;
}
