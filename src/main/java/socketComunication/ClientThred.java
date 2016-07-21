package socketComunication;

import java.io.*;
import java.net.Socket;

/**
 * Created by Dino on 19.7.2016.
 */
public class ClientThred implements Runnable {

    String port;
    String ip;
    Socket socket;
    String message;

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


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ClientThred(){
     port=new String();
        ip=new String();
        message= new String();
    }
    public ClientThred(String newPort, String newIP){
        port=newPort;
        ip=newIP;
        //message=newMessage;
    }


    public String connect()  {
        if(ip=="")
            return "You didn't set IP adress";
        if(port=="")
            return "You didn't set port";

        try {
            socket = new Socket(ip, Integer.parseInt(port));
        }
        catch(IOException e){
            return e.getMessage();
        }
        return "Connected";
    }

    public String disconnect(){
        try {
            socket.close();
        }
        catch(IOException e){
            return e.getMessage();
        }
        return "Closed";
    }

    public String sendLine() {
        if (socket.isConnected()) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println(message);

            } catch (IOException e) {
                return e.getMessage();
            }

            return "Send";
        }
        return "Not connected";
    }

    public void run(){

        System.out.println(sendLine());

    }

}
