/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.io.PrintStream;

/**
 *
 * @author hai
 */
public class GridCell {
    int xCor;
    int yCor;
    int counts;
    int status;
    public void test(){
        System.out.println("hello World");
        System.out.printf("%d, %d, %d, %d, \n", xCor, yCor, counts, status);
    }
}
