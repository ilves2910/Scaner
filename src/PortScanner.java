import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

class PortScanner implements Runnable {
    private String ipAddress;
    private int port;
    private List<ScanResult> openPorts;

    public PortScanner(String ipAddress, int port, List<ScanResult> openPorts) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.openPorts = openPorts;
    }

    @Override
    public void run() {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            InetSocketAddress socketAddress = new InetSocketAddress(address, port);
            Socket socket = new Socket();
            socket.connect(socketAddress, 100);

            openPorts.add(new ScanResult(ipAddress, port));

            socket.close();
        } catch (IOException e) {
            }
    }
}
