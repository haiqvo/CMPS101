/**
 * @title Conway's game of Life
 * @desription Hai Vo's version of Conway's game of Life 
 * it has a run time of O(n). More information can be 
 * found on the README.
 * @author Hai Vo
 * @email hqvo@ucsc.edu
 */

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
import static java.lang.System.*;

/*
 * The main class for my game of life.
 */
public class GameOfLife {
    //the number of Cycles the Cells will go to
    static int generations;
    static int gridsize = 30; //control the grid size.
    
    //the number of cells that will be used
    static int numOfCells = 1;
    
    /**
     * scanfile is where all of the grid calls are made as it
     * is made to read in files
     * @param input which is a Scanner that takes from a file or 
     * from stdin
     * filename is the name of the file being taking in mostly 
     * error handling.
     */
    static void scanfile (Scanner input, String filename) {
        GridCell grid = new GridCell();
        int lineNumber = 0; //used to keep track of the order of inputs
        grid.createCells(gridsize); //creates a 2D array of 50x50.
        while (input.hasNext()){ // read until end of file
            if (lineNumber == 0) {//the first line is the number of cycles
                int num = input.nextInt();
                generations = num;
            } else if (lineNumber == 1) {//the second line is the cell count
                int num = input.nextInt();
                numOfCells = num;
            } else {//after that all of the rest are cells
                numOfCells--;
                int num = input.nextInt();//the first number will be the x Coor
                int nextNum = input.nextInt();//the second will be the y Coor
                //use to fill the Grid for the first time
                grid.fillCells(num, nextNum);
            }
            //after the last Cells is enter start the program
            if(numOfCells == 0){
                //use for stdin mostly
                break;
            }
            lineNumber++;
        }
        grid.printGrid();//print the first grid
        for(int j = 1; j<generations; j++){// number of cycles to perform
            grid.cycleCells();//check and counts neighbors and live cells
            grid.ruling();//use the rule to see which will live
            grid.printGrid();//print the grid again
            grid.finishCycle();//clean the unintialize array to be used again
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
                messages.warn (error.getMessage());
            }
        }
      }catch(PatternSyntaxException error) {//error checking
         messages.die (error.getMessage());
      }
    }

}