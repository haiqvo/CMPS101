import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
import static java.lang.System.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package gameoflife;

/**
 *
 * @author hai
 */
public class GameOfLife {
    static int generations;
    static int numOfCells;
    
    /**
     * @param args the command line arguments
     */
    static void scanfile (Scanner input, String filename) {
        GridCell grid = new GridCell();
        int lineNumber = 0;
        grid.createCells(31, 31);
        while (input.hasNext()){
            if (lineNumber == 0) {
                int num = input.nextInt();
                generations = num;
            } else if (lineNumber == 1) {
                int num = input.nextInt();
                numOfCells = num;
            } else {
                int num = input.nextInt();
                int nextNum = input.nextInt();
                grid.fillCells(num, nextNum);
            }
            
            
            //System.out.println(num + "=" + lineNumber);
            lineNumber++;
        }
        for(int i=1; i<=1; i++){
                grid.cycleCells();
        }
        grid.printTest2();
    }
    public static void main(String[] args) {
      try {
        if (args.length == 0) {
            scanfile (new Scanner (in), "<stdin>");
        }else {
            try {
                String filename = args[0];
                Scanner input = new Scanner (new File (filename));
                scanfile (input, filename);
                input.close();
            }catch (IOException error) {
                messages.warn (error.getMessage());
            }
        }
      }catch(PatternSyntaxException error) {
         messages.die (error.getMessage());
      }
        //System.out.println("Hello World");
        //GridCell Grid = new GridCell(1, 2, 3, 4);
        //Grid.test();
        
        // TODO code application logic here
    }

}