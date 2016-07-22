package socketComunication;

import tranferLayer.JSONparse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 22.7.2016.
 */
public class connection {

    String ip;
    String port;
    //String msg;
    Socket socket;
    JSONparse json;

    public connection(String ip, String port, JSONparse json) throws IOException {
        this.ip=ip;
        this.port=port;
        this.json=json;
        socket=new Socket(ip, Integer.parseInt(port));
        Thread lisener=new Thread(new reciver());
        lisener.start();
        json.sendStatus("Connected");
    }

    public connection(String port, JSONparse json) throws IOException {

        this.port=port;
        this.json=json;
        ServerSocket server=new ServerSocket(Integer.parseInt(port));
        socket=server.accept();
        Thread lisener=new Thread(new reciver());
        lisener.start();
        json.sendStatus("Connected");
    }

    public void sendLine(String msg){
        if(!socket.isClosed()) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(msg);
                out.flush();

                json.sendStatus("Sent");
            } catch (IOException e) {
                json.sendStatus(e.getMessage());

            }
        }

    }

    public class reciver implements Runnable{

        public void run() {
            while (true) {
                if(socket.isConnected()) {
                    String clientData = "dino";
                    try {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        //String ip=socket.getInetAddress().getHostAddress();

                        clientData = reader.readLine();
                        if (clientData != null)
                            json.fromJSON(clientData);

                    } catch (IOException e) {
                        json.sendStatus(e.getMessage());
                        e.printStackTrace();
                    }
                }else{

                    try {
                        socket.close();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

        }
    }

}
