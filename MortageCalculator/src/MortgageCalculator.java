import java.text.NumberFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MortgageCalculator {
  private static final byte MONTHS_PER_YEAR = 12;
  private static final byte PERCENTAGE_POINTS = 100;

  private double principle;
  private double monthlyRate; // Or extract to private method  getMonthlyInterest
  private short timesToPay;

  public MortgageCalculator(double principle, double annualRate, byte years) {
    setPrinciple(principle);
    setMonthlyRate(annualRate);
    setTimesToPay(years);
  }

  public double calMortgage() {

    return principle
        * (monthlyRate * Math.pow(1 + monthlyRate, timesToPay))
        / (Math.pow(1 + monthlyRate, timesToPay) - 1);
  }

  public double getRemainingLoanBal(short alreadyPaidTimes) {

    return principle
        * (Math.pow(1 + monthlyRate, timesToPay) - Math.pow(1 + monthlyRate, alreadyPaidTimes))
        / (Math.pow(1 + monthlyRate, timesToPay) - 1);
  }

  public double[] getRemainingBalances() {
    return IntStream.rangeClosed(1, timesToPay)
        .mapToDouble(k -> getRemainingLoanBal((short) k))
        .toArray();
  }


  private void setPrinciple(double principle) {
    if (principle < 1_000D || principle > 1_000_000D)
      throw new IllegalArgumentException("Principle value must be in range ($1K,$1M)");
    this.principle = principle;
  }


  private void setMonthlyRate(double annualRates) {
    if (annualRates < 1 || annualRates > 30)
      throw new IllegalArgumentException("Annual Rate value must be in range (1,30)");
    this.monthlyRate = annualRates / MONTHS_PER_YEAR / PERCENTAGE_POINTS;
  }


  private void setTimesToPay(byte years) {
    if (years < 1 || years > 30)
      throw new IllegalArgumentException("Years must be in range (1,30)");
    this.timesToPay = (short) (years * MONTHS_PER_YEAR);
  }
}
