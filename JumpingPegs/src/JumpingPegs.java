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
        String numberOfpegs = input.nextLine();
        StringBuffer strbuf;
        strbuf = new StringBuffer(numberOfpegs);
        //System.out.print(numberOfpegs);
        Pegs jumpPegs = new Pegs(strbuf);
        jumpPegs.allPossibleSolution();
//        jumpPegs.jumpingLeft(4);
//        jumpPegs.printString();
//        jumpPegs.jumpingRight(2);
//        jumpPegs.printString();
//        jumpPegs.jumpingRight(0);
//        jumpPegs.printString();
//        jumpPegs.jumpingLeft(4);
//        jumpPegs.printString();
        //int initialPegs = jumpPegs.numberOfIndex();
        //jumpPegs.recursiveBackTracking(0, initialPegs);
    }
//    public void findSafeSpot(int index) {
//        if (index == lastOfIndex) { // base case: a solution!
//            solutionsFound++;
//            displayBoard();
//            if (solutionsFound >= solutionTarget)
//                System.exit(0);
//            return;
//        } 
//        for (int col = 0; col < lastOfIndex; col++) {
//            if (isSafe(index, col)) {
//                placeQueen(index, col);
//                // Move onto the next row.
//                findSafeSpot(index + 1);
//                // If we get here, we’ve backtracked.
//                removeQueen(index, col);
//            }
//        }
//    }
    
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
