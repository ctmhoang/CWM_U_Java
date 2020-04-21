import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int key = input.nextInt();

    StringBuilder res = new StringBuilder("");

    if (key % 5 == 0) res.append("Fizz");
    if (key % 3 == 0) res.append("Buzz");

    System.out.println(res.length() > 1 ? res.toString() : key);
  }
}
