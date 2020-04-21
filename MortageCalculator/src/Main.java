import java.text.NumberFormat;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Main {
  public static void main(String[] args) {
    // Avoid mystery number
    final byte MONTHS_PER_YEAR = 12;
    final byte PERCENTAGE_POINTS = 100;

    Scanner input = new Scanner(System.in);
    NumberFormat format = NumberFormat.getNumberInstance();

    BiFunction<Double, Double, Double> getInputInRange =
        (low, high) -> {
          double temp = input.nextDouble();
          while (temp < low || temp > high) {
            System.out.println(
                "Enter a value between "
                    + format.format(low)
                    + " and "
                    + format.format(high)
                    + " .");
            temp = input.nextDouble();
          }
          return temp;
        };

    BiFunction<Double, Double, Double> getInputInDomain =
        (low, high) -> {
          double temp = input.nextDouble();
          while (temp <= low || temp >= high) {
            System.out.println(
                "Enter a value greater than "
                    + format.format(low)
                    + " and less than or equal to "
                    + format.format(high)
                    + " .");
            temp = input.nextDouble();
          }
          return temp;
        };
    System.out.print("Principal($1K - $1M): ");
    double totalAmount = getInputInRange.apply(1_000D, 1_000_000D);

    System.out.print("Annual Interest Rate: ");
    double annualRate = getInputInDomain.apply(0D, 30D);
    double monthlyRate = annualRate / MONTHS_PER_YEAR / PERCENTAGE_POINTS;

    System.out.print("Period (Year): ");
    short periods = getInputInDomain.apply(0D, 30D).shortValue();
    short timesToPay = (short) (periods * MONTHS_PER_YEAR);
    System.out.println("Enter a value between 1 and 30.");

    double monthlyPaid =
        totalAmount
            * (monthlyRate * Math.pow(1 + monthlyRate, timesToPay))
            / (Math.pow(1 + monthlyRate, timesToPay) - 1);
    String res = NumberFormat.getCurrencyInstance().format(monthlyPaid);
    System.out.println("Montage: " + res);
  }
}
