package databaseLayer;

/**
 * Created by Dino on 25.7.2016.
 */
public interface IDatabaseLayer {
    public void start();
    public void create();
    public void addMsg(String sender, String msg);
    public void dajPoruke();
    public void stop();
}
