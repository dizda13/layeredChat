package terminalLayer;

import org.json.JSONObject;
import tranferLayer.JSONparse;
import tranferLayer.socketConection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 19.7.2016.
 */
public class ClientSide {
    private static String message;
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);

        System.out.println("Type username");
        final String user=new String(scan.nextLine());

        System.out.println("Type ip");
        final String ip=new String(scan.nextLine());

        System.out.println("Type port");
        final String port=new String(scan.nextLine());

        final socketConection socket=new socketConection(ip,port);
        try {
            socket.connect();


            final JSONparse parse=new JSONparse();

                Thread listen=new Thread(){
                    public void run(){
                        try {
                            while(true) {

                                JSONObject writer = parse.fromJSON(socket.getSocket());
                                if(writer!=null) {
                                    System.out.println("-" + writer.get("Username") + "-");
                                    System.out.println(writer.get("Message"));
                                }
                            }

                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                listen.start();
            message=scan.nextLine();
            while(message!="-1"){

                Thread write= new Thread() {
                    public void run()

                    {
                        try {
                            System.out.println(parse.toJSON(message, user, socket.getSocket()));
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                write.start();
                message=scan.nextLine();
            }
            socket.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        /*while(true){
            String side=scan.nextLine();
            if(side=="c"){



                sendLine client=new sendLine(ip,port);
                System.out.println(client.connect());

                System.out.println("Type -1 to stop sending messages");
                System.out.println("Type text");
                String ServerSide=scan.next();
                while(ServerSide!="-1"){
                    client.setMessage(ServerSide);

                    Thread temp= new Thread(client);
                    temp.start();

                    ServerSide=scan.nextLine();
                }

                System.out.println(client.disconnect());

                break;
            }
            if(side=="s"){
                System.out.println("Type port number");
                getLine serv=new getLine(scan.next());

                String msg=serv.connect();
                System.out.println(msg);
                if(msg!="Connected") {
                    Thread someLine = new Thread(serv);
                    someLine.start();
                }

                break;
            }
            System.out.println("Its easy, just type c or s");
        }*/


    }
}
