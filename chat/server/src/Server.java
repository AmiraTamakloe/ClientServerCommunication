public class Server {
    public void start() {
        ConnectionListener cl = new ConnectionListener();

        cl.start();
    }
}
