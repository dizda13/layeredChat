package terminalLayer;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dino on 22.7.2016.
 */
public abstract class abstractSide {
    public abstract void inputData() throws InterruptedException, ExecutionException, IOException;
    public abstract void printMsg(String username, String msg);
    public abstract void printStatus(String satus);
}
