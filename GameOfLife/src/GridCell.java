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
    Cells[][] grid;
    public void createCells(int size1, int size2){
        grid = new Cells[size1][size2];
        for(int k = 0; k<size1; k++){
            for(int j = 0; j < size2; j++){
                grid[k][j] = new Cells(0, 0);
            }
        }
    }
    public void fillCells(int x,int y ){
        grid[x][y].alive = 1;
        System.out.println(x+","+y);
    }
    public void printTest(){
        for(int k = 0; k<grid.length; k++){
            for(int j = 0; j < grid.length; j++){
                System.out.println(k+","+j+":"+grid[k][j].alive);
            }
        }
    }
}
