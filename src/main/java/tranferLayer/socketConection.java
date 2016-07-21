package tranferLayer;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Dino on 21.7.2016.
 */
public class socketConection {

    private String port;
    private String ip;
    private Socket socket;

    public String connect() throws IOException, IllegalArgumentException  {
        if(ip=="")
            throw new IllegalArgumentException("You didn't set IP adress");
        if(port=="")
            throw new IllegalArgumentException("You didn't set port");

        socket = new Socket(ip, Integer.parseInt(port));

        return "Connected";
    }


}
