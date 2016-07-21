package terminalLayer;

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

        String message=new String();
        while(true){
            message=scan.nextLine();

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
