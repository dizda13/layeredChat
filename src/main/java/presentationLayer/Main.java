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
                ClientThred cli=new ClientThred(scan.next(),scan.next());

                System.out.println(cli.connect());
                System.out.println(cli.sendLine());
                System.out.println(cli.disconnect());

                break;
            }
            if(side=="s"){
                System.out.println("Type port number");
                ServerThred serv=new ServerThred(scan.next());
                Thread someLine= new Thread(serv);
                someLine.start();
                break;
            }
            System.out.println("Its easy, just type c or s");
        }


    }
}
