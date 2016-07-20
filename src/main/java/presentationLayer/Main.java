package presentationLayer;

import businessLayer.ClientThred;
import businessLayer.ServerThred;

import java.util.Scanner;

/**
 * Created by Dino on 19.7.2016.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Do you want server side od client side c/s");
        Scanner scan=new Scanner(System.in);
        while(true){
            String side=scan.nextLine();
            if(side=="c"){
                System.out.println("Type ip adress and port number");
                String ip=scan.next();
                String port=scan.next();


                ClientThred client=new ClientThred(ip,port);
                System.out.println(client.connect());

                System.out.println("Type -1 to stop sending messages");
                System.out.println("Type text");
                String message=scan.next();
                while(message!="-1"){
                    client.setMessage(message);

                    Thread temp= new Thread(client);
                    temp.start();

                    message=scan.next();
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
        }


    }
}
