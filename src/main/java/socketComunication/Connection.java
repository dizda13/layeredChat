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
        ip="";
        this.port=port;
        connected=true;
        connectServer();
    }

    public void setConnectionParametars(String ip, String port) throws IOException {
        this.ip=ip;
        connected=true;
        this.port=port;
        connect();
    }


    public void closeConnection() throws IOException {
        connected=false;
        //sendLine("stop");
        socket.close();
        iTransferLayer.sendStatus("By by...");
    }

    public void closeSecondSide() throws IOException {
        connected=false;
        socket.close();
        iTransferLayer.sendStatus("Connection closed by by...");
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
            BufferedReader reader = null;
            try {

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (connected) {
                    String clientData;
                    try {

                        clientData = reader.readLine();
                        /*if(clientData!=null)
                            System.out.println(clientData);
                        if(clientData.equals("stop")){
                            closeSecondSide();
                            break;
                        }*/

                        if (clientData != null)
                            iTransferLayer.fromJSON(clientData);



                    } catch (IOException e) {
                        if(connected==true){
                            try {
                                closeConnection();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        break;
                        }
                    }

            }

        }
    }

