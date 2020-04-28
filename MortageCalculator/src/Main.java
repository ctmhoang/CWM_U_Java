import java.text.NumberFormat;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Main {
  public static void main(String[] args) {
    // Avoid mystery number
    double totalAmount = getNumInput("Principal($1K - $1M)", 1_000D, 1_000_000D);

    double annualRate = getNumInput("Annual Interest Rate", 1, 30);

    byte periods = (byte) getNumInput("Period (Year)", 1, 30);

    double monthlyPaid = calMortgage(totalAmount, annualRate, periods);

    String res = NumberFormat.getCurrencyInstance().format(monthlyPaid);
    System.out.println("Montage: " + res);
  }

  public static double getNumInput(String prompt, double low, double high) {
    Scanner input = new Scanner(System.in);
    NumberFormat format = NumberFormat.getNumberInstance();
    double val;

    while (true) {
      System.out.print(prompt + ": ");
      val = input.nextDouble();
      if (val >= low && val <= high) break;
      System.out.printf(
          "Enter a value between %s and %s .\n", format.format(low), format.format(high));
    }
    return val;
  }

  public static double calMortgage(double principle, double annualInterest, byte years) {

    final byte MONTHS_PER_YEAR = 12;
    final byte PERCENTAGE_POINTS = 100;

    double monthlyRate = annualInterest / MONTHS_PER_YEAR / PERCENTAGE_POINTS;
    short timesToPay = (short) (years * MONTHS_PER_YEAR);

    return principle
        * (monthlyRate * Math.pow(1 + monthlyRate, timesToPay))
        / (Math.pow(1 + monthlyRate, timesToPay) - 1);
  }
}
