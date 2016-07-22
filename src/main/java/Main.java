import terminalLayer.ClientSide;
import terminalLayer.ServerSide;
import terminalLayer.abstractSide;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 22.7.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        Scanner scan=new Scanner(System.in);
        System.out.println("Are you server or socket c/s");
        String chouseSide=scan.next();
        abstractSide side;
        while(true){
            if(chouseSide.equals("c")) {
                side = new ClientSide();

                side.inputData();
                break;
            }
            if(chouseSide.equals("s")){
                side = new ServerSide();

                side.inputData();
                break;
            }
            System.out.println("Type just c or s");
        }
        return ;
    }
}
