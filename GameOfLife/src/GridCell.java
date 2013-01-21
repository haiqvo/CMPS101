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
    ArrayList<Cells> collectionsOfCells = new ArrayList();
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
        for(int i = 0; i<aliveCells.size();i++){
            CheckForRepeats(i, -1, -1);
            CheckForRepeats(i, 0, -1);
            CheckForRepeats(i, 1, -1);
            CheckForRepeats(i, -1, 0);
            CheckForRepeats(i, 1, 0);
            CheckForRepeats(i, -1, 1);
            CheckForRepeats(i, 0, 1);
            CheckForRepeats(i, 1, 1);
        }
        
    }
    public void CheckForRepeats(int i, int x, int y){
        int xCor = aliveCells.get(i).x;
        int yCor = aliveCells.get(i).y;
        if(collectionsOfCells.indexOf(grid[xCor+x][yCor+y]) == -1){
            if(xCor+x < aliveCells.size() || xCor+x > 0){
                if(yCor+y < aliveCells.size() || yCor+y > 0){
                    collectionsOfCells.add(grid[xCor+x][yCor+y]);
                }
            }
        }
    }
    public void printTest(){
        for(int k = 0; k<grid.length; k++){
            for(int j = 0; j < grid.length; j++){
                System.out.println(k+","+j+":"+grid[k][j].alive);
            }
        }
    }
    public void printTest2(){
        System.out.println(collectionsOfCells);
        System.out.println(grid[19][20]);
        
    }
}
