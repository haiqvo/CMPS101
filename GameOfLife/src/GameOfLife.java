import gameoflife.GridCell;
import gameoflife.Cells;
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

    /**
     * @param args the command line arguments
     */
    static void scanfile (Scanner input, String filename) {
        while (input.hasNext()){
            String line = input.next();
            System.out.println(line);
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