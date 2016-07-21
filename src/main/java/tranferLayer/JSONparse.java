package tranferLayer;

import org.json.JSONObject;
import org.json.simple.JSONObject;

import java.net.Socket;
import java.util.Map;

/**
 * Created by Dino on 21.7.2016.
 */
public class JSONparse {

    public JSONparse(){

    }

    public String toJSON(String msg, String user , Socket socket){
        //JSONObject neki;
        String jsonStr=new String("{\"Username\":\""+user+"\",\"Message\":\""+msg+"\"}");
        Map<String,String> mapa=new Map;

        //JSONObject neki=new JSONObject(jsonStr);

        

        return "a";
    }

    public String fromJSON(String msg, Socket socket){
        return "a";
    }
}
