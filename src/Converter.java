import java.net.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Converter {

    private static double getCurrency() throws IOException {
        URL data = new URL("http://www.nbg.ge/rss.php");
        BufferedReader in = new BufferedReader(new InputStreamReader(data.openStream()));

        String line;
        int counter = 0;
        boolean readUSD = false;
        while ((line = in.readLine()) != null) {
            if (line.contains("USD"))
                readUSD = true;
            if (readUSD)
                counter++;
            if (counter == 3)
                break;
        }
        int startIndex = line.indexOf('>' );
        int endIndex = line.indexOf('<',startIndex);

        String resString = line.substring(startIndex + 1, endIndex );
        in.close();
        return Double.valueOf(resString);
    }

    public static double gelToUSD(double gel) throws IOException {
        double currency = getCurrency();
        return gel / currency;
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter amount in GEL (or -1 to quit): ");
            double gel = scanner.nextDouble();
            if (gel == -1)
                break;
            double usd = gelToUSD(gel);
            System.out.println(gel + " GEL = " + usd + " USD");
        }
    }
}