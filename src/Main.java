import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter IP-address for scan: ");
        String ipAddress = scanner.nextLine();

        System.out.println("Starting a Scan");

        List<ScanResult> scanResults = scanIpAddress(ipAddress);

        if (!scanResults.isEmpty()) {
            System.out.println("Opened ports for IP-address: " + ipAddress);
            for (Object result : scanResults) {
                System.out.println(result);
            }

            System.out.print("Do you want to save result? (y/n): ");
            String saveToFile = scanner.nextLine();

            if (saveToFile.equalsIgnoreCase("y")) {
                saveResultsToFile(scanResults);
            }
        } else {
            System.out.println("Not results");
        }

        scanner.close();
    }

 private static List<ScanResult> scanIpAddress(String ipAddress) {
      int timeout = 150;

        List<ScanResult> results = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(20);
           for (int port = 1; port <= 3000; port++) {
               service.submit(new PortScanner(ipAddress, port, results));
        }

        service.shutdown();

      while (!service.isTerminated()) {
      }
        return results;
    }


    private static void saveResultsToFile(List<ScanResult> results) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("File format (txt/csv): ");
        String fileFormat = scanner.nextLine();

        System.out.print("Enter FileName: ");
        String fileName = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + "." + fileFormat))) {
            if (fileFormat.equalsIgnoreCase("csv")) {
                writer.println("IP Address,Port,Status");
            }

            for (ScanResult result : results) {
                if (fileFormat.equalsIgnoreCase("csv")) {
                    writer.println(result.toCsvString());
                } else {
                    writer.println(result.toString());
                }
            }

            System.out.println("Results saved: " + fileName + "." + fileFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


