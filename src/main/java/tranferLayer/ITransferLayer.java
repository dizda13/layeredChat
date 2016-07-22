package tranferLayer;

import consoleLayer.IConsoleLayer;
import socketComunication.ISocketComunication;

/**
 * Created by Dino on 22.7.2016.
 */
public interface ITransferLayer {
    public void toJSON(String msg, String user);
    public void fromJSON(String jsonMsg);
    public void sendStatus(String status);
    public void setIConsoleLayer(IConsoleLayer iConsoleLayer);
    public void setISocketComunication(ISocketComunication iSocketComunication);
}
