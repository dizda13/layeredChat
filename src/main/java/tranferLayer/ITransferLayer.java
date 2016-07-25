package tranferLayer;

import consoleLayer.IConsoleLayer;
import databaseLayer.IDatabaseLayer;
import socketComunication.ISocketComunication;

import java.io.IOException;

/**
 * Created by Dino on 22.7.2016.
 */
public interface ITransferLayer {
    public void toJSON(String msg, String user);
    public void fromJSON(String jsonMsg);
    public void sendStatus(String status);
    public void setIConsoleLayer(IConsoleLayer iConsoleLayer);
    public void setISocketComunication(ISocketComunication iSocketComunication);
    public void sendConectionParamtars(String ip,String port) throws IOException;
    public void sendConectionParamtars(String port) throws IOException;
    public void sendEndConnectionSignal();
    public void setIDatabaseLayer(IDatabaseLayer iDatabaseLayer);
}
