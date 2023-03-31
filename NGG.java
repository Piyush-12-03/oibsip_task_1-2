import java.util.Random;
import java.util.Scanner;

public class NGG {

  public static void main(String[] args) {

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    
    int randomNumber = rand.nextInt(100) + 1;
  
    
    int tryCount = 0;
    while(true) {
      System.out.println("Enter a Number you wanna guess between(1-100):");
      
      int playerGuess = scanner.nextInt();
      tryCount++;
      
      if (playerGuess == randomNumber) {
        System.out.println("Huurraayy ! You Guessed it right!");
        System.out.println("You took " + tryCount + " tries");
        break;
      }
      else if(randomNumber > playerGuess) {
        System.out.println("Ohh No! The number is higher than your guessed number. Guess again you can do it.");
      }
      else {
        System.out.println("Oops ! The number is lower than your guessed number. Guess again you can do it.");
      }
    }
    scanner.close();
    
  }
}