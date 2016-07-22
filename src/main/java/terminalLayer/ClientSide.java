package terminalLayer;

import tranferLayer.JSONparse;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 19.7.2016.
 */
public class ClientSide extends abstractSide {

    public void inputData() {
        Scanner scan=new Scanner(System.in);

        System.out.println("Type username");
        String user=new String(scan.nextLine());

        System.out.println("Type ip");
        String ip=new String(scan.nextLine());

        System.out.println("Type port");
        String port=new String(scan.nextLine());


        JSONparse input=null;
        try {
            input = new JSONparse(ip,port,user,this);


        while(true) {
            System.out.println("Type message");
            String message = new String(scan.nextLine());
                input.toJSON(message);
        }
        } catch (IOException e) {
            printStatus(e.getMessage());
        }

    }

    public void printMsg(String username, String msg){
        System.out.println("-" + username + "-");
        System.out.println(msg);
    }

    public void printStatus(String status){
        System.out.println(status);
    }
}
