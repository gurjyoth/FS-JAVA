package taskz;
import java.util.Scanner;

public class task3 {
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine(); 
        
        int w = 0; 
        
        
        for (char c : input.toCharArray()) {
          
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
               w++; 
        }
        
        System.out.println("Occurrences of vowels: " + w); 
        scanner.close(); 
}}}