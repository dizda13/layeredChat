import consoleLayer.IConsoleLayer;
import consoleLayer.ServerSide;
import databaseLayer.Database;
import databaseLayer.IDatabaseLayer;
import socketComunication.Connection;
import socketComunication.ISocketComunication;
import tranferLayer.ITransferLayer;
import tranferLayer.JSONtranslator;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 22.7.2016.
 */
public class MainServer {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        IConsoleLayer iConsoleLayer=new ServerSide();
        ITransferLayer iTransferLayer=new JSONtranslator();
        ISocketComunication iSocketComunication=new Connection();
        IDatabaseLayer iDatabaseLayer=new Database();

        iConsoleLayer.setITransferLayer(iTransferLayer);

        iTransferLayer.setIConsoleLayer(iConsoleLayer);
        iTransferLayer.setISocketComunication(iSocketComunication);
        iTransferLayer.setIDatabaseLayer(iDatabaseLayer);

        iSocketComunication.setITransferLayer(iTransferLayer);

        iConsoleLayer.inputData();
    }
}
