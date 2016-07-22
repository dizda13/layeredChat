package terminalLayer;

import org.json.JSONObject;
import tranferLayer.JSONparse;
import tranferLayer.socketConection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 20.7.2016.
 */
public class ServerSide extends abstractSide {
    public void inputData() throws InterruptedException, ExecutionException, IOException {
        Scanner scan=new Scanner(System.in);

        System.out.println("Type username");
        String user=new String(scan.nextLine());

        System.out.println("Type port");
        String port=new String(scan.nextLine());


        JSONparse input=new JSONparse(port,user,this);

        while(true) {
            System.out.println("Type message");
            String message = new String(scan.nextLine());
            input.toJSON(message);
            try {
                input.toJSON(message);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
