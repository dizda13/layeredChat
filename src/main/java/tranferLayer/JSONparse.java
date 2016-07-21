package tranferLayer;

import org.json.JSONObject;
import socketComunication.sendLine;

import java.net.Socket;
import java.util.Map;
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

    public String fromJSON(String msg, Socket socket){
        return "a";
    }
}
