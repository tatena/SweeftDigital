import java.util.Scanner;

public class Fibonacci {

    public static final int SENTINEL = -1;

    public static void main (String args[]) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter the number (n) to calculate the n-th member "
                                + "of Fibonacci sequence (indexed from 0)  or -1 to exit: ");
            int input = scanner.nextInt();
            if(input == SENTINEL) {
                break;
            }
            System.out.println(getFibonacci(input));
        }
    }

    private static long getFibonacci(int n) {
        return fastFibonacci(1, 1, n);
    }

    // recursive helper function.
    private static long fastFibonacci(long  twoBefore, long oneBefore, int n) {
        if (n <= 1 ) { return oneBefore; }
        return fastFibonacci(oneBefore, oneBefore + twoBefore, n - 1);
    }

}
