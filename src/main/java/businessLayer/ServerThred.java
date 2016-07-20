package businessLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dino on 19.7.2016.
 */
public class ServerThred implements Runnable {

    ServerSocket server;
    Socket socket;
    String port;

    public ServerThred(String port){
        this.port=port;
    }

    public void run(){
        try {
            server = new ServerSocket(Integer.parseInt(port));
            socket=server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String clientData = "";
            clientData = reader.readLine();
            System.out.println(clientData);
            //socket.close();
            server.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }




}
