package terminalLayer;

import tranferLayer.JSONparse;
import tranferLayer.socketConection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 19.7.2016.
 */
public class Main {
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
            //socket.connect();



            final JSONparse parse=new JSONparse();
            message=scan.nextLine();
            while(message!="-1"){

                Thread tre= new Thread() {
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
                tre.start();
                message=scan.nextLine();
            }
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
                String message=scan.next();
                while(message!="-1"){
                    client.setMessage(message);

                    Thread temp= new Thread(client);
                    temp.start();

                    message=scan.nextLine();
                }

                System.out.println(client.disconnect());

                break;
            }
            if(side=="s"){
                System.out.println("Type port number");
                ServerThred serv=new ServerThred(scan.next());

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
