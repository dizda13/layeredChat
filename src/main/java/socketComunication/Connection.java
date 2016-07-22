package socketComunication;

import tranferLayer.ITransferLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dino on 22.7.2016.
 */
public class Connection implements  ISocketComunication {

    private ITransferLayer iTransferLayer;

    private String ip;
    private String port;
    private Socket socket;
    private Thread lisener;
    private Boolean connected=false;

    public void setITransferLayer(ITransferLayer iTransferLayer) {
        this.iTransferLayer=iTransferLayer;
    }

    public Connection(){

    }

    public void setConnectionParametars(String port) throws IOException {
        this.port=port;
        connectServer();
    }

    public void setConnectionParametars(String ip, String port) throws IOException {
        this.ip=ip;
        this.port=port;
        connect();
    }


    public void closeConnection() throws IOException {
        connected=false;
    }



    public void connectServer() throws IOException {
        ServerSocket server = new ServerSocket(Integer.parseInt(port));
        socket=server.accept();
        connected=true;
        lisener=new Thread(new reciver());
        lisener.start();
        iTransferLayer.sendStatus("Connected");
    }

    public void connect() throws IOException {
        socket=new Socket(ip, Integer.parseInt(port));
        connected=true;
        lisener=new Thread(new reciver());
        lisener.start();
        iTransferLayer.sendStatus("Connected");
    }

    public void sendLine(String msg){
        if(connected) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(msg);
                out.flush();

                iTransferLayer.sendStatus("Sent");
            } catch (IOException e) {
                iTransferLayer.sendStatus(e.getMessage());

            }
        }

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
                            iTransferLayer.fromJSON(clientData);


                    } catch (IOException e) {
                        iTransferLayer.sendStatus(e.getMessage());
                        e.printStackTrace();
                    }

                }
            }

        }
    }

}
