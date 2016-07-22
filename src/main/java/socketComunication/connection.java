package socketComunication;

import tranferLayer.JSONparse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
    }


    public void sendLine(String msg){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public class reciver implements Runnable{

        public void run() {
            while(true){

                try {BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String clientData="dino";

                    //String ip=socket.getInetAddress().getHostAddress();

                    while(clientData==null) {
                        clientData = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
