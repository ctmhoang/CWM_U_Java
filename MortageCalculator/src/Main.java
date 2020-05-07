public class Main {

  public static void main(String[] args) {
    // Avoid mystery number
    double totalAmount = Console.getNumInput("Principal($1K - $1M)", 1_000D, 1_000_000D);
    double annualRate = Console.getNumInput("Annual Interest Rate", 1, 30);
    byte periods = (byte) Console.getNumInput("Period (Year)", 1, 30);

    var mortgageReport = new MortgageReport(new MortgageCalculator(totalAmount, annualRate, periods));
    mortgageReport.printMortgageSummary();
    mortgageReport.printPaymentSchedule();
  }
}
