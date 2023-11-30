class ScanResult {
    private String ipAddress;
    private int port;

    public ScanResult(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "IP: " + ipAddress + ", Port: " + port + ", Status: " + "Open";
    }

    public String toCsvString() {
        return "IP: " + ipAddress + ", Port: " + port + ", Status: " + "Open";
    }
}
