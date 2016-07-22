package consoleLayer;

import tranferLayer.ITransferLayer;

/**
 * Created by Dino on 22.7.2016.
 */
public interface IConsoleLayer {
    public void printStatus(String status);
    public void printMsg(String username, String msg);
    public void inputData();
    public void setITransferLayer(ITransferLayer iTransferLayer);
}
