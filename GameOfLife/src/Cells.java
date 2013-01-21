/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hai
 */
public class Cells {
    int counts = 0;
    int alive = 0;
    int x;
    int y;
    Cells pointer;
    public Cells(int x, int y, int count){
        this.x = x;
        this.y = y;
        counts = count;
    }
}
