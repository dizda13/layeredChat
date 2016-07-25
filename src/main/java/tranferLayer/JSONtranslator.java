package tranferLayer;

import consoleLayer.IConsoleLayer;
import databaseLayer.IDatabaseLayer;
import org.json.JSONObject;
import socketComunication.ISocketComunication;

import java.io.IOException;

/**
 * Created by Dino on 21.7.2016.
 */
public class JSONtranslator implements ITransferLayer {
    private IConsoleLayer iConsoleLayer;
    private ISocketComunication iSocketComunication;
    private IDatabaseLayer iDatabaseLayer;

    public void setIConsoleLayer(IConsoleLayer iConsoleLayer) {
        this.iConsoleLayer=iConsoleLayer;
    }

    public void setIDatabaseLayer(IDatabaseLayer iDatabaseLayer) {
        this.iDatabaseLayer=iDatabaseLayer;
    }

    public void setISocketComunication(ISocketComunication iSocketComunication) {
        this.iSocketComunication=iSocketComunication;
    }

    public JSONtranslator(){

    }

    public void sendConectionParamtars(String ip, String port) throws IOException {
        iSocketComunication.setConnectionParametars(ip,port);
        iDatabaseLayer.start();
    }

    public void sendConectionParamtars(String port) throws IOException {
        iSocketComunication.setConnectionParametars(port);
        iDatabaseLayer.start();
    }

    public void sendEndConnectionSignal() {
        try {
            iSocketComunication.closeConnection();
            iDatabaseLayer.stop();
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
        iDatabaseLayer.create();
        iDatabaseLayer.addMsg(json.getString("Username"),json.getString("Message"));
    }



}
