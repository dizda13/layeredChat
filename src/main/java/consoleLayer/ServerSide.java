package consoleLayer;

import tranferLayer.ITransferLayer;
import tranferLayer.JSONtranslator;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Dino on 20.7.2016.
 */
public class ServerSide implements IConsoleLayer {

    private ITransferLayer iTransferLayer;

    public void setITransferLayer(ITransferLayer iTransferLayer){
        this.iTransferLayer=iTransferLayer;
    }


    public void inputData() {
        Scanner scan=new Scanner(System.in);

        System.out.println("Type username");
        String user=new String(scan.nextLine());

        System.out.println("Type port");
        String port=new String(scan.nextLine());


        try {
            iTransferLayer.sendConectionParamtars(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = "";
        while (!message.equals("-1")) {
            System.out.println("Type message/ -1 close");
            message = new String(scan.nextLine());
            iTransferLayer.toJSON(user, message);
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
