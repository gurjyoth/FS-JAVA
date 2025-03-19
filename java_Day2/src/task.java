package taskz;
import java.util.Scanner;
public class punda {
public static void main (String args[]) {
	
	Scanner scanner = new Scanner(System.in);
	System.out.println("enter the sentence ");
	String input= scanner.nextLine();
	 int l=0;
			 
			for(char w :input. toCharArray()) {
	 if ( w == 'a' || w == 'e' || w == 'i' || w == 'o' || w == 'u' ||
			 w == 'A' || w == 'E' || w == 'I' || w == 'O' || w == 'U' )
	 {
		 l++;
			 }
	  System.out.println("Occurrences of vowels: " + l); 
      scanner.close(); 
	 
}}}
