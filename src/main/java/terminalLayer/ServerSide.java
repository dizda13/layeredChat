package terminalLayer;

import org.json.JSONObject;
import tranferLayer.JSONparse;
import tranferLayer.socketConection;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 20.7.2016.
 */
public class ServerSide {
    private static String message;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Type username");
        final String user = new String(scan.nextLine());


        System.out.println("Type port");
        final String port = new String(scan.nextLine());

        final socketConection socket = new socketConection(port);
        try {
            socket.connectServer();


            final JSONparse parse = new JSONparse();

            Thread listen = new Thread() {
                public void run() {
                    try {
                        while (true) {

                            JSONObject writer = parse.fromJSON(socket.getSocket());

                            System.out.println("-" + writer.get("Username") + "-");
                            System.out.println(writer.get("Message"));

                        }

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            listen.start();
            message = scan.nextLine();
            while (message != "-1") {

                Thread write = new Thread() {
                    public void run()

                    {
                        try {
                            System.out.println(parse.toJSON(message, user, socket.getSocket()));
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                write.start();
                message = scan.nextLine();
            }
            socket.disconnectServer();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
