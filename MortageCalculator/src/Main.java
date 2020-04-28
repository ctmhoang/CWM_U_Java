import java.text.NumberFormat;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class Main {

  public static final byte MONTHS_PER_YEAR = 12;
  public static final byte PERCENTAGE_POINTS = 100;

  public static void main(String[] args) {
    // Avoid mystery number
    double totalAmount = getNumInput("Principal($1K - $1M)", 1_000D, 1_000_000D);

    double annualRate = getNumInput("Annual Interest Rate", 1, 30);

    byte periods = (byte) getNumInput("Period (Year)", 1, 30);

    printMortgageSummary(totalAmount, annualRate, periods);

    printPaymentSchedule(totalAmount, annualRate, periods);
  }

  private static void printPaymentSchedule(double totalAmount, double annualRate, byte periods) {
    printHeader("payment schedule");
    IntStream.range(1, periods * 12 + 1)
        .mapToObj(
            k ->
                NumberFormat.getCurrencyInstance()
                    .format(getRemainingLoanBal(totalAmount, annualRate, periods, (short) k)))
        .forEachOrdered(System.out::println);
  }

  private static void printMortgageSummary(double totalAmount, double annualRate, byte periods) {
    double monthlyPaid = calMortgage(totalAmount, annualRate, periods);
    printHeader("Mortgage");
    String res = NumberFormat.getCurrencyInstance().format(monthlyPaid);
    System.out.println("Monthly Payment: " + res);
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

    double monthlyRate = annualInterest / MONTHS_PER_YEAR / PERCENTAGE_POINTS;
    short timesToPay = (short) (years * MONTHS_PER_YEAR);

    return principle
        * (monthlyRate * Math.pow(1 + monthlyRate, timesToPay))
        / (Math.pow(1 + monthlyRate, timesToPay) - 1);
  }

  public static double getRemainingLoanBal(
      double principle, double annualRates, byte period, short alreadyPaidTimes) {
    double monthlyRates = annualRates / MONTHS_PER_YEAR / PERCENTAGE_POINTS;
    short totPaidTimes = (short) (period * MONTHS_PER_YEAR);

    return principle
        * (Math.pow(1 + monthlyRates, totPaidTimes) - Math.pow(1 + monthlyRates, alreadyPaidTimes))
        / (Math.pow(1 + monthlyRates, totPaidTimes) - 1);
  }

  public static void printHeader(String content) {
    System.out.println(content.toUpperCase());
    System.out.println(new String(new char[content.length()]).replace('\0', '-'));
  }
}
