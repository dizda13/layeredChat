package socketComunication;

import tranferLayer.JSONtranslator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dino on 22.7.2016.
 */
public class Connection {

    private String ip;
    private String port;
    //String msg;
    private Socket socket;
    private JSONtranslator json;
    private Thread lisener;
    private Boolean connected=false;

    public Connection(String ip, String port, JSONtranslator json) throws IOException {
        this.ip=ip;
        this.port=port;
        this.json=json;
        socket=new Socket(ip, Integer.parseInt(port));
        connected=true;
        Thread lisener=new Thread(new reciver());
        lisener.start();
        json.sendStatus("Connected");
    }

    public Connection(String port, JSONtranslator json) throws IOException {

        this.port=port;
        this.json=json;
        ServerSocket server=new ServerSocket(Integer.parseInt(port));
        socket=server.accept();
        connected=true;
        lisener=new Thread(new reciver());
        lisener.start();
        json.sendStatus("Connected");
    }

    public void sendLine(String msg){
        if(connected) {
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

    public void closeConnection() throws IOException {
        connected=false;
    }

    public class reciver implements Runnable{

        public void run() {
            while (true) {
                BufferedReader reader;
                if(connected) {
                    String clientData;
                    try {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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
