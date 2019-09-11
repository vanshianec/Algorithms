import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(consoleReader.readLine().split(": ")[1]);
        int items = Integer.parseInt(consoleReader.readLine().split(": ")[1]);
        List<String> priceAndWeight = new ArrayList<>();
        for (int i = 0; i < items; i++) {
            String itemParams = consoleReader.readLine();
            priceAndWeight.add(itemParams);
        }

        priceAndWeight.sort((a, b) -> Double.compare(getBestValue(b), getBestValue(a)));
        StringBuilder result = new StringBuilder();
        double totalPrice = 0;
        while (capacity > 0 && priceAndWeight.size() > 0) {
            String bestItem = priceAndWeight.get(0);
            double currentPrice = Double.parseDouble(bestItem.split(" -> ")[0]);
            double currentWeight = Double.parseDouble(bestItem.split(" -> ")[1]);
            if (currentWeight <= capacity) {
                result.append(String.format("Take 100%% of item with price %.2f and weight %.2f", currentPrice, currentWeight)).append(System.lineSeparator());
                capacity -= currentWeight;
            } else {
                double percentage = (100 * capacity) / currentWeight;
                result.append(String.format("Take %.2f%% of item with price %.2f and weight %.2f", percentage, currentPrice, currentWeight)).append(System.lineSeparator());
                currentPrice = (currentPrice * percentage) / 100;
                capacity = 0;
            }
            priceAndWeight.remove(0);
            totalPrice += currentPrice;
        }

        System.out.println(result.toString().trim());
        System.out.printf("Total price: %.2f", totalPrice);
    }

    private static double getBestValue(String itemParams) {
        double price = Double.parseDouble(itemParams.split(" -> ")[0]);
        double weight = Double.parseDouble(itemParams.split(" -> ")[1]);
        return price / weight;
    }
}
