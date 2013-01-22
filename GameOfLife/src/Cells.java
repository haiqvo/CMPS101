/**
 * @desription Cells class which is the object that used for 
 * both the 2D array (grid) and the unintialize arrays.
 * @author Hai Vo
 * @email hqvo@ucsc.edu
 */
public class Cells {
    //the counts of alive Cells around it.
    int counts = 0;
    
    //use to see if it is alive or not 0=die 1=alive
    int alive = 0;
    
    //the x Coordinate
    int x;
    
    //y Coordinate
    int y;
    
    //a pointer to the unintialized array
    Cells pointer;
    
    /*
     * the constructor of the Cells class that is used to initialize
     * the 2D array for the first time.
     */
    public Cells(int x, int y, int count){
        this.x = x;
        this.y = y;
        counts = count;
    }
}
