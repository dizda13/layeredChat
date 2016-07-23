package tranferLayer;

import consoleLayer.IConsoleLayer;
import org.json.JSONObject;
import socketComunication.ISocketComunication;

import java.io.IOException;

/**
 * Created by Dino on 21.7.2016.
 */
public class JSONtranslator implements ITransferLayer {
    private IConsoleLayer iConsoleLayer;
    private ISocketComunication iSocketComunication;

    public void setIConsoleLayer(IConsoleLayer iConsoleLayer) {
        this.iConsoleLayer=iConsoleLayer;
    }

    public void setISocketComunication(ISocketComunication iSocketComunication) {
        this.iSocketComunication=iSocketComunication;
    }

    public JSONtranslator(){

    }

    public void sendConectionParamtars(String ip, String port) throws IOException {
        iSocketComunication.setConnectionParametars(ip,port);
    }

    public void sendConectionParamtars(String port) throws IOException {
        iSocketComunication.setConnectionParametars(port);
    }

    public void sendEndConnectionSignal() {
        try {
            iSocketComunication.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendStatus(String status){
        iConsoleLayer.printStatus(status);
    }


    public void toJSON(String user, String msg) {
        String jsonStr=new String("{\"Username\":\""+user+"\",\"Message\":\""+msg+"\"}");

        iSocketComunication.sendLine(jsonStr);

    }

    public void fromJSON(String jsonMsg) {
        JSONObject json = new JSONObject(jsonMsg);
        iConsoleLayer.printMsg(json.getString("Username"),json.getString("Message"));
    }



}
