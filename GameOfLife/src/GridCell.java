/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.PrintStream;
import java.util.*;

/**
 *
 * @author hai
 */
public class GridCell {
    int counter = 0;
    Cells[][] grid;
    //private class aliveCells{
        
    //}
    ArrayList<Cells> aliveCells = new ArrayList();
    ArrayList<Cells> CollectionsOfCells = new ArrayList();
    public void createCells(int size1, int size2){
        grid = new Cells[size1][size2];
        for(int k = 0; k<size1; k++){
            for(int j = 0; j < size2; j++){
                grid[k][j] = new Cells();
            }
        }
    }
    public void fillCells(int x,int y ){
        grid[x][y].x = x;
        grid[x][y].y = y;
        aliveCells.add(grid[x][y]);
        int location = aliveCells.size()-1;
        grid[x][y].pointer = aliveCells.get(location);
        //System.out.println(counter+":"+x+" "+y);
        counter++;
        //System.out.println(x+","+y);
    }
    
    public void cycleCells(){
        CollectionsOfCells.addAll(aliveCells);
        
    }
    public void printTest(){
        for(int k = 0; k<grid.length; k++){
            for(int j = 0; j < grid.length; j++){
                System.out.println(k+","+j+":"+grid[k][j].alive);
            }
        }
    }
    public void printTest2(){
        System.out.println(CollectionsOfCells.get(1).x);
        System.out.println(grid[21][20].pointer);
    }
}
