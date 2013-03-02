import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
import static java.lang.System.*;
import java.lang.Math;
import java.util.*;

/*
 * The main class for my game of life.
 */

public class JumpingPegs {
    
    static void scanfile (Scanner input, String filename) {
        int numberOfpegs = input.nextInt();
        String pegs = input.nextLine();
        pegs = input.nextLine();
        pegs = pegs.replaceAll("\\s", "");
        int pegsInt = Integer.parseInt(pegs.toString());
        StringBuffer strbuf;
        strbuf = new StringBuffer(pegs);
//        HashTables table = new HashTables();
//        table.put(pegsInt, strbuf);
//        if(table.get(pegsInt, strbuf)){
//            System.out.println("found");
//        }
        Pegs jumpPegs = new Pegs(strbuf);
        System.out.println(pegs);
        if(jumpPegs.recursiveBackTracking(0)){
            jumpPegs.printStack();
        }
    }

    
    /*
     * main is the main function in the program and is mostly 
     * use to read in a file or stdin and check if the file 
     * exists. All of the call are done in scanfile
     * @param args is where the user can enter in a file they
     * plan to read.
     */
    public static void main(String[] args) {
      try {
        if (args.length == 0) {// if no arguements take from stdin
            scanfile (new Scanner (in), "<stdin>");
        }else {
            try {
                String filename = args[0];//read in file
                Scanner input = new Scanner (new File (filename));
                scanfile (input, filename);
                input.close();//close file
            }catch (IOException error) {//error checking
                System.err.println(error);
            }
        }
      }catch(PatternSyntaxException error) {//error checking
         System.err.println(error);
      }
    }

}
