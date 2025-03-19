package taskz;
import java.util.Scanner;
public class task2 {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter a string: ");
	        String input = scanner.nextLine();
	        System.out.print("Enter character to count: ");
	        char target = scanner.nextLine().charAt(0);
	        System.out.println("Occurrences: " + input.chars().filter(c -> c == target).count());
	        scanner.close();
	    }
	}
	