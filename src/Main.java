import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String a = scanner.nextLine();
            System.out.println(Calc.calc(a));
        }

    }
}
