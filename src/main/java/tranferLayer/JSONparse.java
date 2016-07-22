package tranferLayer;

import org.json.JSONObject;
import socketComunication.connection;
import terminalLayer.abstractSide;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by Dino on 21.7.2016.
 */
public class JSONparse {

    //String msg;
    String ip;
    String port;
    String user;
    abstractSide client;
    connection socket;

    public JSONparse(String ip, String port, String user, abstractSide client) throws IOException {
        this.ip=ip;
        this.port=port;
        this.user=user;
        this.client=client;
        socket =new connection(ip,port,this);
    }

    public JSONparse(String port, String user, abstractSide client) throws IOException {
        this.ip="";
        this.port=port;
        this.user=user;
        this.client=client;
        socket =new connection(port,this);
    }

    public void toJSON(String msg) {
        String jsonStr=new String("{\"Username\":\""+user+"\",\"Message\":\""+msg+"\"}");

        socket.sendLine(jsonStr);

    }

    public void fromJSON(String jsonMsg) {
        JSONObject json = new JSONObject(jsonMsg);
        client.printMsg(json.getString("Username"),json.getString("Message"));
        //client.printMsg(user,jsonMsg);
    }

    public void sendStatus(String status){
        client.printStatus(status);
    }
}
