/**
 * @desription GridCells class where the program performs most of 
 * its actions.
 * @author Hai Vo
 * @email hqvo@ucsc.edu
 */

import java.util.*;

public class GridCell {
    Cells[][] grid;//the 2D array 
    
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
        grid = new Cells[size][size];//creates the size of the 2D array Cells
        for(int k = 0; k<size; k++){//intializes it 
            for(int j = 0; j < size; j++){
                grid[k][j] = new Cells(k,j,0);
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
        try{
        //if(x<grid.length && x>= 0){//check if the x is within the limit
            grid[x][y].x = x;//set the x Coor
        //}
        }catch ( Exception err ) {
            System.out.println( err.getMessage( ) + " : x Coor is to large" +
                    " increase grid size" );
            System.exit(1);
        }
        try{
            grid[x][y].y = y;//set the y Coor
        }catch ( Exception err ) {
            System.out.println( err.getMessage( ) + " : y Coor is to large" +
                    " increase grid size" );
            System.exit(1);
        }
        grid[x][y].alive = 1;//set it to alive
        aliveCells.add(grid[x][y]);//have the unintialize point to cells
        int location = aliveCells.size()-1;
        //have cell point to unintialize
        grid[x][y].pointer = aliveCells.get(location);
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
        int xCor = aliveCells.get(i).x;//get the x Coor of alive cells
        int yCor = aliveCells.get(i).y;//get the y Coor of alive cells
        int index;
        //check if the index it within the graph
        if(xCor+x<grid.length && xCor+x>= 0 && yCor+y<grid.length && yCor+y>=0){
            //get the index of where the neighboring cell is store is it is 
            //already stored
            index = collectionsOfCells.indexOf(grid[xCor+x][yCor+y]);
        }else{
            return;//if the x or y is too big or small return
        }
        //get the index of where the alive cell is store in the unint array
        int aliveIndex = aliveCells.indexOf(grid[xCor][yCor]);
        //check if the neighboring cell is not already a alive array
        if(!aliveCells.contains(grid[xCor+x][yCor+y])){
            //check if the neighboring cell is not already in the array
            if(!collectionsOfCells.contains(grid[xCor+x][yCor+y])){
                //check if the neighboring array exists (corners)
                if(xCor+x < grid.length || xCor+x >= 0){
                    if(yCor+y < grid.length || yCor+y >= 0){
                        //increase the count by one
                        grid[xCor+x][yCor+y].counts++;
                        //add it to the unint neighbor array
                        collectionsOfCells.add(grid[xCor+x][yCor+y]);
                    }
                }
            }else{
                //if the neighbor array already exists increase the count
                collectionsOfCells.get(index).counts++;
            }
        }else{
            //if there is a alive array next to it increase its count
            aliveCells.get(aliveIndex).counts++;
        }
    }
    
    /**
     * printGrid print a 30x30 grid using System.out.print
     */
    public void printGrid(){
        for(int k = 0; k<grid.length; k++){
            for(int j = 0; j<grid.length; j++){
                if(aliveCells.contains(grid[k][j].pointer)){
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
                aliveCells.remove(i);//remove it from array
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
                        [aliveCells.get(aliveCells.size()-1).y].pointer
                        = aliveCells.get(aliveCells.size()-1);
            }
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
}
