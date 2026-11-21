
import java.util.*;

public class ArithmeticEncoding {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        Map<Character, Integer> freq = new LinkedHashMap<>();

        for (char ch : message.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        double low = 0.0;
        double high = 1.0;

        int total = message.length();

        for (char ch : message.toCharArray()) {

            double range = high - low;
            double cumulative = 0.0;

            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {

                double probability = (double) entry.getValue() / total;

                if (entry.getKey() == ch) {
                    high = low + range * (cumulative + probability);
                    low = low + range * cumulative;
                    break;
                }

                cumulative += probability;
            }
        }

        double code = (low + high) / 2;

        System.out.println("Final Range: [" + low + ", " + high + ")");
        System.out.println("Encoded Value: " + code);

        sc.close();
    }
}
