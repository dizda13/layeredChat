package terminalLayer;

import tranferLayer.JSONparse;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Dino on 20.7.2016.
 */
public class ServerSide extends abstractSide {
    public void inputData() {
        Scanner scan=new Scanner(System.in);

        System.out.println("Type username");
        String user=new String(scan.nextLine());

        System.out.println("Type port");
        String port=new String(scan.nextLine());


        try {
            JSONparse input = new JSONparse(port,user,this);

            String message="";
            while(!message.equals("-1")) {

                message = new String(scan.nextLine());
                input.toJSON(message);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
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
