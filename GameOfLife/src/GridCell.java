/**
 * @desription GridCells class where the program performs most of 
 * its actions.
 * @author Hai Vo
 * @email hqvo@ucsc.edu
 */

import java.util.*;

public class GridCell {
    int[][] grid;//the 2D array 
    
    //the unintialize array of live Cells
    ArrayList<Cells> aliveCells = new ArrayList<Cells>();
    
    //the unintialize array of neighboring Cells
    ArrayList<Cells> collectionsOfCells = new ArrayList<Cells>();
    
    /**
     * createCells creates a intialized 2D array to start the 
     * progam
     * @param size is a int that is used to read in a size 
     * of the 2D array
     */
    public void createCells(int size){
        grid = new int[size][size];//creates the size of the 2D array Cells
        for(int k = 0; k<size; k++){//intializes it 
            for(int j = 0; j < size; j++){
                grid[k][j] = 0;
            }
        }
    }
    
    /**
     * fillCells fills the unintialize alive array with its first values 
     * this is only called once in a single run and only in the 
     * beginning.
     * @param int x and y is the number taken from a file or stdin to tell
     * where the alive cells intial locations are.
     */
    public void fillCells(int x,int y ){
        aliveCells.add(new Cells(x,y));//have the unintialize point to cells
        int location = aliveCells.size()-1;
        //have cell point to unintialize
        try{
        grid[x][y] = location;
        }catch ( Exception err ) {
            System.out.println( err.getMessage( ) + 
                    " : x or y Coor is too large"+
                    " increase grid size check README" );
            System.exit(1);
        }
    }
    
    /**
     * cycleCells adds all the neighbors of alive array into the unint 
     * neighbor array.
     */
    public void cycleCells(){
        //goes through all of the unint alive Cells array

        for(int i = 0; i<aliveCells.size();i++){
            //8 possible squares
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
    
    /**
     * CheckForRepeats is used by the cycleCells to count and add the 
     * neighbor of alive arrays and also count the alive arrays.
     * @param int x and y is going to be 0,1,and -1 also it is all 
     * the combinations of squared around the alive array.
     * int i is the index of which alive array it is currently on.
     */
    public void CheckForRepeats(int i, int x, int y){
        int xCor = aliveCells.get(i).x + x;//get the x Coor of alive cells
        int yCor = aliveCells.get(i).y + y;//get the y Coor of alive cells
        int index = grid[xCor][yCor];
        if(alive(xCor, yCor)){//if it alive increase the count
            aliveCells.get(index).counts++;
        }else{
            if(isNeighbor(xCor, yCor)){
                //if it is already in there increase the count
                collectionsOfCells.get(index).counts++;
            }else{
                Cells newCells = new Cells(xCor,yCor);
                newCells.counts++;//increase the count
                collectionsOfCells.add(newCells);//storing the Cells
                //pointing it back
                grid[xCor][yCor] = collectionsOfCells.size()-1;
            }
        }
    }
    
    /*
     * Check to see if the alive cells is already there.
     */
    public boolean alive(int xcoord,int ycoord){
        int index = grid[xcoord][ycoord];
        if(index < aliveCells.size() && aliveCells.get(index).x 
                == xcoord && aliveCells.get(index).y == ycoord)
            return true;
        else
            return false;
    }
    
    /*
     * Check to see if the neighbor cells is already there.
     */
    public boolean isNeighbor(int xcoord, int ycoord){
        int index = grid[xcoord][ycoord];
        if(index < collectionsOfCells.size() && collectionsOfCells.get(index).x 
                == xcoord && collectionsOfCells.get(index).y == ycoord){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * printGrid print a 30x30 grid using System.out.print
     */
    public void printGrid(){
        for(int k = 0; k<grid.length; k++){
            for(int j = 0; j<grid.length; j++){
                    if(alive(k,j)){
                        System.out.print("X");//if its alive
                    }else{
                        System.out.print("O");//if its dead
                    }
            }
            System.out.println();//seperates the rows
        }
        for(int k = 0; k<30; k++){
            System.out.print("-");//seperates the grids
        }
        System.out.println();//seperates the grid
    }
    
    /**
     * ruling is the function that check if the cell will dead, stay
     * alive, or live in the next cycle.
     */
    public void ruling(){
        int size = aliveCells.size();//store the intial size
        for(int i = 0; i<size;i++){
            if(aliveCells.get(i).counts == 2 || aliveCells.get(i).counts == 3){
                continue;//if the cell is a 2 or 3 it stays alive
            }else{
                aliveCells.get(i).alive = 0;//change to die
                aliveCells.get(i).counts = 0;//restarts the count
                removeCell(i);
                size--;//once removed the size will change.
                i--;//the index has also shifted
            }
        }
        //goes through all the neighbor cells and check if they become alive
        for(int i = 0; i<collectionsOfCells.size();i++){
            //if it has 3 alive cell next to it, it becomes alive
            if(collectionsOfCells.get(i).counts == 3){
                collectionsOfCells.get(i).alive = 1;
                //add it to unint alive array
                aliveCells.add(collectionsOfCells.get(i));
                //point the cell to the unint array
                grid[aliveCells.get(aliveCells.size()-1).x]
                        [aliveCells.get(aliveCells.size()-1).y]
                        = aliveCells.size()-1;
            }
        } 
         
    }
    
    /*
     * How to remove a dead cell for the alive cell array
     */
    public void removeCell(int index){
        if(index < aliveCells.size()){
            int xcoord = aliveCells.get(aliveCells.size()-1).x;
            int ycoord = aliveCells.get(aliveCells.size()-1).y;
            grid[xcoord][ycoord] = index;
            //sway the one to remove with the last so it does hurt the pointers.
            aliveCells.set(index, aliveCells.get(aliveCells.size()-1));
            aliveCells.remove(aliveCells.size()-1);
        }
    }
    
    /**
     * finishCycle once the ruling is finish and all of the cells 
     * that will become alive is stored it is called to reset the
     * the counts and clear the unint neighboring array
     */
    public void finishCycle(){
        for(int i = 0; i<collectionsOfCells.size();i++){
            //change the counts to zero
            collectionsOfCells.get(i).counts = 0;
        }
        collectionsOfCells.clear(); //clear the neighbor array
        for(int i = 0; i<aliveCells.size();i++){
            aliveCells.get(i).counts = 0;//change count to zero
        }
    }
    /*
     * Used to debug the Coordinates of alive cells
     */
    public void test1(){
        for(int i = 0; i<aliveCells.size();i++){
            System.out.println(grid[aliveCells.get(i).x][aliveCells.get(i).y]);
        }
    }
    /*
     * Used to debug counts of alive and neighbor Cells
     */
    public void test2(){
        for(int i = 0; i<aliveCells.size();i++){
            System.out.println(aliveCells.get(i).counts);
        }
        System.out.println();
        for(int i = 0; i<collectionsOfCells.size();i++){
            System.out.println(collectionsOfCells.get(i).counts);
        }
    }
}
