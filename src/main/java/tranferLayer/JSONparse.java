package tranferLayer;

import com.sun.deploy.util.SessionState;
import org.json.JSONObject;
import socketComunication.connection;
import socketComunication.getLine;
import socketComunication.sendLine;
import terminalLayer.ClientSide;

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
    ClientSide client;

    public JSONparse(String ip, String port, String user,ClientSide client){
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

    public JSONObject fromJSON(Socket socket) throws ExecutionException, InterruptedException {
        ExecutorService single = Executors.newSingleThreadExecutor();
        Callable<String> callable=new getLine(socket);

        Future<String> future= single.submit(callable);



        String returnMsg="dino";

        JSONObject json;

        while(returnMsg==null) {
            returnMsg = future.get();
            Thread.sleep(1000);

        }
            json = new JSONObject(returnMsg);




        return json;
    }
}
