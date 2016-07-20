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

    public String connect()  {
        if(port=="")
            return "You didn't set port";

        try {
            server = new ServerSocket(Integer.parseInt(port));
            socket=server.accept();
            if(!socket.isConnected())
                return  "Not connected";
        }
        catch(IOException e){
            return e.getMessage();
        }
        return "Connected";
    }

    public String disconnect(){
        try {
            //socket.close();
            server.close();
        }
        catch(IOException e){
            return e.getMessage();
        }
        return "Closed";
    }

    public void run(){
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = "";
            while((clientData = reader.readLine())!=null)
            System.out.println(clientData);

            System.out.println(disconnect());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }




}
