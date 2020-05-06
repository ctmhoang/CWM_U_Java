import java.text.NumberFormat;
import java.util.Date;

public class Types {

  public static void main(String[] args) {
    // write your code here
    byte age = 30; // -128 -> 127 (1 bytes)
    // short -32K -> 32K (2 bytes)
    // int -2B -> 2B (4 bytes)
    // Use under_score to separate log number after 3 digits
    // Add suffice
    long viewsCount = 3_213_456_789L; // 8 bytes
    float price = 10.99F; // 4 bytes
    // double 8 bytes
    char letter = 'A'; // 2 bytes
    boolean isEligible = true; // 1 bytes

    Date now = new Date(); // allocate memory has members
    // Argument != parameter

    // Format Number
    // use NumberFormat and factory method
    NumberFormat.getCurrencyInstance().format(123_434_123.98);
    // method chaining

    // Switch cannot use long type

    // Abstraction reduce coupling
  }
}
