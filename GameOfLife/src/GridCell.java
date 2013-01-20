/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.PrintStream;

/**
 *
 * @author hai
 */
public class GridCell {
    int[][] grid = new int[100][100];
    int counts;
    int alive = 1;
    public void test(){
        System.out.println("hello World");
        //System.out.printf("%d, %d, %d, %d, \n", counts, status);
    }
    public void fillCells(int x,int y){
        grid[x][y] = alive;
        System.out.println(x+","+y);
    }
}
