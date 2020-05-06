import java.text.NumberFormat;
import java.util.Scanner;

public class InputChecker
{
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
}
