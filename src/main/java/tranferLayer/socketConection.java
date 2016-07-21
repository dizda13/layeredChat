package tranferLayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dino on 21.7.2016.
 */
public class socketConection {

    private String port;
    private String ip;
    private Socket socket;
    private ServerSocket server;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public socketConection(String ip, String port){
        this.ip=ip;
        this.port=port;
    }

    public socketConection(String port){
        this.port=port;
    }

    public String connect() throws IOException, IllegalArgumentException  {
        if(ip=="")
            throw new IllegalArgumentException("You didn't set IP adress");
        if(port=="")
            throw new IllegalArgumentException("You didn't set port");

        socket = new Socket(ip, Integer.parseInt(port));

        return "Connected";
    }

    public String connectServer() throws IOException, IllegalArgumentException  {
        if(port=="")
            throw new IllegalArgumentException("You didn't set port");

        server = new ServerSocket(Integer.parseInt(port));
        socket=server.accept();
        return "Connected";
    }



    public String disconnect() throws IOException {

            socket.close();

        return "Closed";
    }

    public String disconnectServer() throws IOException {

        server.close();

        return "Closed";
    }


}
