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
            System.out.println(chouseSide);
            if(chouseSide.equals("c")) {
                System.out.println("doslo");
                side = new ClientSide();
                try {
                    side.inputData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //break;
            }
            if(chouseSide.equals("s")){
                side = new ServerSide();
                try {
                    side.inputData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //break;
            }
            System.out.println("Type just c or s");
        }
    }
}
