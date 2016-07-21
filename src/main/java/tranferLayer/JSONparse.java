package tranferLayer;

import org.json.JSONObject;
import socketComunication.getLine;
import socketComunication.sendLine;

import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by Dino on 21.7.2016.
 */
public class JSONparse {

    public JSONparse(){

    }

    public String toJSON(String msg, String user , Socket socket) throws ExecutionException, InterruptedException {
        //JSONObject neki;
        String jsonStr=new String("{\"Username\":\""+user+"\",\"Message\":\""+msg+"\"}");


        //JSONObject neki=new JSONObject(jsonStr);

        ExecutorService single = Executors.newSingleThreadExecutor();
        Callable<String> callable=new sendLine(socket,msg);

        Future<String> future= single.submit(callable);

        String returnMsg = future.get();

        return returnMsg;
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
