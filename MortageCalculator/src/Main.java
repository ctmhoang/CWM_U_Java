import java.text.NumberFormat;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    // Avoid mystery number
    double totalAmount = InputChecker.getNumInput("Principal($1K - $1M)", 1_000D, 1_000_000D);
    double annualRate = InputChecker.getNumInput("Annual Interest Rate", 1, 30);
    byte periods = (byte) InputChecker.getNumInput("Period (Year)", 1, 30);

    var mortgageCalculator = new MortgageCalculator(totalAmount, annualRate, periods);
    mortgageCalculator.printMortgageSummary();
    mortgageCalculator.printPaymentSchedule();
  }
}
