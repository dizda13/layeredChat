package tranferLayer;

import consoleLayer.IConsoleLayer;
import org.json.JSONObject;
import socketComunication.Connection;
import consoleLayer.abstractSide;
import socketComunication.ISocketComunication;

import java.io.IOException;

/**
 * Created by Dino on 21.7.2016.
 */
public class JSONtranslator implements ITransferLayer {
    IConsoleLayer iConsoleLayer;
    ISocketComunication iSocketComunication;
    //String msg;
    private String ip;
    private String port;
    private String user;
    private abstractSide client;
    private Connection socket;

    public JSONtranslator(){

    }

    public JSONtranslator(String ip, String port, String user, abstractSide client) throws IOException {
        this.ip=ip;
        this.port=port;
        this.user=user;
        this.client=client;
        socket =new Connection(ip,port,this);
    }

    public JSONtranslator(String port, String user, abstractSide client) throws IOException {
        this.ip="";
        this.port=port;
        this.user=user;
        this.client=client;
        socket =new Connection(port,this);
    }

    public void toJSON(String msg, String user) {
        String jsonStr=new String("{\"Username\":\""+user+"\",\"Message\":\""+msg+"\"}");

        socket.sendLine(jsonStr);

    }

    public void fromJSON(String jsonMsg) {
        JSONObject json = new JSONObject(jsonMsg);
        client.printMsg(json.getString("Username"),json.getString("Message"));
    }

    public void sendStatus(String status){
        client.printStatus(status);
    }

    public void setIConsoleLayer(IConsoleLayer iConsoleLayer) {
        this.iConsoleLayer=iConsoleLayer;
    }

    public void setISocketComunication(ISocketComunication iSocketComunication) {
        this.iSocketComunication=iSocketComunication;
    }

    public void close() throws IOException {
        socket.closeConnection();
    }
}
