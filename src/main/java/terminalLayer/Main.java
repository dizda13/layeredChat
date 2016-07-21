package terminalLayer;

import tranferLayer.JSONparse;
import tranferLayer.socketConection;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Dino on 19.7.2016.
 */
public class Main {
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);

        System.out.println("Type ip");
        String ip=new String(scan.nextLine());

        System.out.println("Type port");
        String port=new String(scan.nextLine());

        socketConection socket=new socketConection(ip,port);
        try {
            socket.connect();


            String message=new String();
            JSONparse parse=new JSONparse();
            while(message!="-1"){
                message=scan.nextLine();
                parse.toJSON(message, socket.getSocket());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*while(true){
            String side=scan.nextLine();
            if(side=="c"){



                ClientThred client=new ClientThred(ip,port);
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
