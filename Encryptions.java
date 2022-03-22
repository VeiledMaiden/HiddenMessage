import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SecretCodeEncryptions {
  public static void main(String[] args) throws FileNotFoundException {
    //Declare variables
    String message;
    short secretKey;
    String yesNo;
    Boolean printDoc = false;
    int shiftValueEq;
    byte shiftValue;
    char letter;
    String word;
    String messageFinal = "";
    char wordFinal;
    int which;
    Scanner userInput = new Scanner(System.in);
   
    System.out.print("1)Encrypt or 2)Unencrypt ");
    which = userInput.nextInt();

    if (which == 1) {
      System.out.print("Enter a text file to encrypt: ");
      message = userInput.next();
      Scanner file = new Scanner(new File(message));
      System.out.print("Enter an encryption key between 1-1000: ");
      secretKey = userInput.nextShort();
      System.out.print("Would you like to print in a document? ");
      yesNo = userInput.next();
      PrintWriter write = new PrintWriter(new File("encrypted_" + message));
      if (yesNo.equalsIgnoreCase("Yes")) {
        printDoc = true;
      }

      shiftValueEq = secretKey % 43;
      shiftValue = (byte)shiftValueEq;
  
      System.out.println("\nYour encrypted message is: ");
    
      while (file.hasNext()) {
        word = file.next();
        for (int i=0; i < word.length(); i++) {
          letter = word.charAt(i);
          
          if (i % 5 == 1) {
            letter -= shiftValue + 3;
          } else if ( i % 5 == 2) {
            letter -= shiftValue + 5;
          } else if ( i % 5 == 3) {
            letter -= shiftValue - 6;
          } else if ( i % 5 == 4) {
            letter -= shiftValue + 4;
          } else if ( i % 5 == 0) {
            letter -= shiftValue + 2;
          }

          System.out.print(letter);
          if (printDoc == true) {
            write.print(letter);  
          }
        }
        System.out.print(" ");
        shiftValue++;
        if (printDoc == true) {
          write.print(" "); 
        }
      }
      if (printDoc == true) {
        write.close();
        file.close();
      } else {
        
      }
      System.out.print("\nDo you want to decrypt something? ");
      yesNo = userInput.next();
      if (yesNo.equalsIgnoreCase("yes")) {
        which = 2;
      }
    }
    
    if (which == 2) {
      printDoc = false;
      System.out.print("Enter a text file to unencrypt: ");
      message = userInput.next();
      Scanner unfile = new Scanner(new File(message));
      System.out.print("Enter the encryption key between 1-1000: ");
      secretKey = userInput.nextShort();
      System.out.print("Would you like to keep in a document? ");
      yesNo = userInput.next();
      PrintWriter writer = new PrintWriter(new File("un" + message));
      if (yesNo.equalsIgnoreCase("Yes")) {
        printDoc = true;
      }

      shiftValueEq = secretKey % 43;
      shiftValue = (byte)shiftValueEq;
  
      System.out.println("\nYour unencrypted message is: ");
    
      while (unfile.hasNext()) {
        word = unfile.next();
        for (int i=0; i < word.length(); i++) {
          letter = word.charAt(i);
          
          if (i % 5 == 1) {
            letter += shiftValue + 3;
          } else if ( i % 5 == 2) {
            letter += shiftValue + 5;
          } else if ( i % 5 == 3) {
            letter += shiftValue - 6;
          } else if ( i % 5 == 4) {
            letter += shiftValue + 4;
          } else if ( i % 5 == 0) {
            letter += shiftValue + 2;
          }

          System.out.print(letter);
          if (printDoc == true) {
            writer.print(letter);
          }
        }
        System.out.print(" ");
        shiftValue++;
        if (printDoc == true) {
          writer.print(" ");
        }
      }
      if (printDoc == true) {
        writer.close();
        unfile.close();
      } else {
      }
    }
  }
}