package socketComunication;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.Callable;

/**
 * Created by Dino on 19.7.2016.
 */
public class sendLine implements Callable{

    private Socket socket;
    private  String msg;



    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }



    public sendLine(Socket socket,String msg){
        this.socket=socket;
        this.msg=msg;
    }





    public String call() throws Exception {
        if (socket.isConnected()) {
                System.out.println("doslo");
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println(msg);
                //out.flush();

            return "Sent";
        }
        return "Not connected";
    }
}
