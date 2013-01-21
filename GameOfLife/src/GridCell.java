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
    ArrayList<Cells> aliveCells = new ArrayList<Cells>();
    ArrayList<Cells> collectionsOfCells = new ArrayList<Cells>();
    public void createCells(int size1, int size2){
        grid = new Cells[size1][size2];
        for(int k = 0; k<size1; k++){
            for(int j = 0; j < size2; j++){
                grid[k][j] = new Cells(k,j,0);
            }
        }
    }
    public void fillCells(int x,int y ){
        grid[x][y].x = x;
        grid[x][y].y = y;
        grid[x][y].alive = 1;
        grid[x][y].counts = 0;
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
        /*
        for(int i = 0; i<collectionsOfCells.size();i++){
            int xCor = collectionsOfCells.get(i).x;
            int yCor = collectionsOfCells.get(i).y;
            int index = collectionsOfCells.indexOf(grid[xCor][yCor]);
            collectionsOfCells.get(index).counts++;
        }
         * 
         */
    }
    public void CheckForRepeats(int i, int x, int y){
        int xCor = aliveCells.get(i).x;
        int yCor = aliveCells.get(i).y;
        int index = collectionsOfCells.indexOf(grid[xCor+x][yCor+y]);
        int aliveIndex = aliveCells.indexOf(grid[xCor][yCor]);
        if(!aliveCells.contains(grid[xCor+x][yCor+y])){
            if(!collectionsOfCells.contains(grid[xCor+x][yCor+y])){
                if(xCor+x < grid.length || xCor+x >= 0){
                    if(yCor+y < grid.length || yCor+y >= 0){
                        grid[xCor+x][yCor+y].counts++;
                        collectionsOfCells.add(grid[xCor+x][yCor+y]);
                        //collectionsOfCells.get(index).counts++;
                    }
                }
            }else{
                collectionsOfCells.get(index).counts++;
            }
        }else{
            aliveCells.get(aliveIndex).counts++;
        }
    }
    
    public void printGrid(){
        for(int k = 0; k<40; k++){
            for(int j = 0; j<40; j++){
                if(aliveCells.contains(grid[k][j].pointer)){
                    System.out.print("O");
                }else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
    public void ruling(){
        int size = aliveCells.size();
        for(int i = 0; i<size;i++){
            if(aliveCells.get(i).counts == 2 || aliveCells.get(i).counts == 3){
                continue;
            }else{
                aliveCells.get(i).alive = 0;
                aliveCells.get(i).counts = 0;
                aliveCells.remove(i);
                size--;
                i--;
            }
        }      
        for(int i = 0; i<collectionsOfCells.size();i++){
            if(collectionsOfCells.get(i).counts == 3){
                collectionsOfCells.get(i).alive = 1;
                aliveCells.add(collectionsOfCells.get(i));
                grid[aliveCells.get(aliveCells.size()-1).x]
                        [aliveCells.get(aliveCells.size()-1).y].pointer
                        = aliveCells.get(aliveCells.size()-1);
            }
        } 
         
    }
    public void finishCycle(){
        for(int i = 0; i<collectionsOfCells.size();i++){
            collectionsOfCells.get(i).counts = 0;
        }
        collectionsOfCells.clear();
        for(int i = 0; i<aliveCells.size();i++){
            aliveCells.get(i).counts = 0;
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
        
        for(int i = 0; i<collectionsOfCells.size();i++){
            System.out.println(collectionsOfCells.get(i).x+","+collectionsOfCells.get(i).y+":"+collectionsOfCells.get(i).counts);
        }
        System.out.println();
        for(int i = 0; i<aliveCells.size();i++){
            System.out.println(aliveCells.get(i).x+","+aliveCells.get(i).y+":"+aliveCells.get(i).counts);
        }
          
         
        //System.out.println(collectionsOfCells);
        //System.out.println(grid.length);
        
    }
    public void printTest3(){
        for(int i = 0; i<aliveCells.size();i++){
            System.out.println(aliveCells.get(i).x+","+aliveCells.get(i).y+":"+aliveCells.get(i).counts);
        }
        System.out.println();
        for(int i = 0; i<collectionsOfCells.size();i++){
            System.out.println(collectionsOfCells.get(i).x+","+collectionsOfCells.get(i).y+":"+collectionsOfCells.get(i).counts);
        }
    }
}
