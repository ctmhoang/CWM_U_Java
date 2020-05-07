import java.text.NumberFormat;
import java.util.Arrays;

public class MortgageReport {

  private final MortgageCalculator mortgageCalculator;
  private final NumberFormat currency;

  public MortgageReport(MortgageCalculator mortgageCalculator) {
    this.mortgageCalculator = mortgageCalculator;
    currency = NumberFormat.getCurrencyInstance();
  }

  public void printPaymentSchedule() {
    printHeader("payment schedule");
    Arrays.stream(mortgageCalculator.getRemainingBalances())
        .mapToObj(currency::format)
        .forEachOrdered(System.out::println);
  }

  public void printMortgageSummary() {
    double monthlyPaid = mortgageCalculator.calMortgage();
    printHeader("Mortgage");
    String res = currency.format(monthlyPaid);
    System.out.println("Monthly Payment: " + res);
  }

  private static void printHeader(String content) {
    System.out.println(content.toUpperCase());
    System.out.println(new String(new char[content.length()]).replace('\0', '-'));
  }
}
