package businessLayer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Dino on 19.7.2016.
 */
public class ClientThred {

    String port;
    String ip;
    Socket socket;

    public ClientThred(){
     port=new String();
        ip=new String();
    }
    public ClientThred(String newPort, String newIP){
        port=newPort;
        ip=newIP;
    }

    public void changeIP(String newIP){
        ip=newIP;
    }

    public void changePort(String newPort){
        port=newPort;
    }

    public String connect()  {
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

    public String sendLine(){
        Scanner scan=new Scanner(System.in);
        String line;
        try {
            line=scan.nextLine();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(line);

        } catch (IOException e) {
            return e.getMessage();
        }

        return "Send";
    }
}
