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
    static int numOfCells = 1;
    
    /**
     * @param args the command line arguments
     */
    static void scanfile (Scanner input, String filename) {
        GridCell grid = new GridCell();
        int lineNumber = 0;
        grid.createCells(50, 50);
        while (input.hasNext()){
            if (lineNumber == 0) {
                int num = input.nextInt();
                generations = num;
            } else if (lineNumber == 1) {
                int num = input.nextInt();
                numOfCells = num;
            } else {
                numOfCells--;
                int num = input.nextInt();
                int nextNum = input.nextInt();
                grid.fillCells(num, nextNum);
            }
            if(numOfCells == 0){
                break;
            }
            //System.out.println(num + "=" + lineNumber);
            lineNumber++;
        }
        grid.printGrid();
        for(int j = 0; j<4; j++){
            System.out.println();
            grid.cycleCells();
            //grid.printTest3();
            grid.ruling();
            grid.printGrid();
            grid.finishCycle();
        }
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