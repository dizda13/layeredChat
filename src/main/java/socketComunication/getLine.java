package socketComunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by Dino on 19.7.2016.
 */
public class getLine implements Callable {

    //private ServerSocket server;
    private Socket socket;

    public getLine(Socket socket){
        this.socket=socket;
    }



    public Object call() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientData;

        //String ip=socket.getInetAddress().getHostAddress();

        clientData = reader.readLine();
        return clientData;
    }
}
