import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // Avoid mystery number
    final byte MONTHS_PER_YEAR = 12;
    final byte PERCENTAGE_POINTS = 100;

    Scanner input = new Scanner(System.in);

    System.out.print("Principal($1K - $1M): ");
    double totalAmount = () -> {
      double temp = input.nextDouble();
      while (temp < 1_000 && temp > 1_000_000)
    {
      temp = input.nextDouble();
    }
      return temp;
    };

    System.out.print("Annual Interest Rate: ");
    double monthlyRate = input.nextDouble() / MONTHS_PER_YEAR / PERCENTAGE_POINTS;

    System.out.print("Period (Year): ");
    short timesToPay = (short) (input.nextShort() * MONTHS_PER_YEAR);

    double monthlyPaid =
        totalAmount
            * (monthlyRate * Math.pow(1 + monthlyRate, timesToPay))
            / (Math.pow(1 + monthlyRate, timesToPay) - 1);
    String res = NumberFormat.getCurrencyInstance().format(monthlyPaid);
    System.out.println("Montage: " + res);
  }
}
