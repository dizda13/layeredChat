package tranferLayer;

import com.sun.deploy.util.SessionState;
import org.json.JSONObject;
import socketComunication.connection;
import socketComunication.getLine;
import socketComunication.sendLine;
import terminalLayer.ClientSide;
import terminalLayer.abstractSide;

import java.io.IOException;
import java.net.Socket;
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

    public JSONparse(String ip, String port, String user, abstractSide client){
        this.ip=ip;
        this.port=port;
        this.user=user;
        this.client=client;
    }

    public void toJSON(String msg) throws ExecutionException, InterruptedException, IOException {
        String jsonStr=new String("{\"Username\":\""+user+"\",\"Message\":\""+msg+"\"}");

        connection socket=new connection(ip,port,this);

        socket.sendLine(jsonStr);

    }

    public void fromJSON(String jsonMsg) throws ExecutionException, InterruptedException {
        JSONObject json = new JSONObject(jsonMsg);
        client.printMsg(json.getString("Username"),json.getString("Message"));
    }

    public void sendStatus(String status){
        client.printStatus(status);
    }
}
