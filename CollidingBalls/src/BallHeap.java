/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hai Vo
 */
public class BallHeap {
    private CollisionData[] cd;
    private int numNodes = 0;
    public int shiftdownInt, shiftupInt;
    
    public BallHeap(int size){
        cd = new CollisionData[size];//the size of heap
    }
    
    public double min( ) {//find the min without removing
        return cd[0].collisionTime;
    }
    
    public double indexOf(int index){//find the collisionTime of a index
        return cd[index].collisionTime;
    }
    
    public double getMin(int[][] pointer){//find the min collisionTime and shift it down
        double min = cd[0].collisionTime;
        cd[0].collisionTime = Integer.MAX_VALUE;
        shiftdown(0, pointer);
        return min;
    }
    
    public void delete(int index, int[][] pointer){//shift the index down;
        cd[index].collisionTime = Integer.MAX_VALUE;
        shiftdown(index, pointer);
    }
    
    public int getI(int i){//get the i
        int temp = cd[i].first;
        return temp;
    }
    public int getJ(int i){//get j
        int temp = cd[i].second;
        return temp;
    }
    
    public int getIndex(){//get the index of a number 
        int temp = numNodes;
        return temp;
    }
    
    public void update(int i, double time, int[][] pointer){//update the collisionTime
        cd[i].collisionTime = time;
        sort(i, pointer);
    }
    
    private void sort(int index, int[][] pointer){
        int leftChild = 2*index+1;
        int rightChild = 2*index+2;
        int parent = (index-1)/2;
        //if the child are lower shift up
        if (rightChild >= numNodes && leftChild >= numNodes){
            shiftup(index, pointer);
        }
        //if the child are greater shiftdown
        else if(cd[rightChild].collisionTime<cd[index].collisionTime ||
                cd[leftChild].collisionTime<cd[index].collisionTime){
                shiftdown(index, pointer);
        }
        //if the parent is less than shift down
        else if (parent <= 0){
            shiftdown(index, pointer);
        }
        //if parent is greater than shift up
        else if(cd[index].collisionTime<cd[parent].collisionTime){
            shiftup(index, pointer);
        }
        //if equal to do nothing
        else if((cd[index].collisionTime==cd[parent].collisionTime)){
            return;
        }
        
        
        
    }
    //add a new node should only use at the beginning
    public int add ( int i, int j, double time, int[][] pointer){
        CollisionData temp = new CollisionData(i, j, time);
        cd[numNodes] = temp;
        int count = shiftup(numNodes, pointer);
        numNodes++;
        return count;
    }
    //sorts upwards
    private int shiftup(int index, int[][] pointer){
        int count = 0;
        while(index>0){
            int parent = (index-1)/2;
            if(cd[parent].collisionTime > cd[index].collisionTime){
                swap(parent, index, pointer);
                count++;
            }
            index = parent;
            
        }       
        return count;
    }
    //sort downward
    private void shiftdown(int index, int[][] pointer){
        int leftChild = 2*index+1;
        int rightChild = 2*index+2;
        
        if (rightChild >= numNodes && leftChild >= numNodes)return;
        int smallestChild =
                cd[rightChild].collisionTime > cd[leftChild].collisionTime
                ? leftChild : rightChild;
        if(cd[index].collisionTime>cd[smallestChild].collisionTime){
            swap(smallestChild, index, pointer);
            shiftdown(smallestChild, pointer);
        }
    }
    //swap the nodes and also manages the pointers to the table
    private void swap(int a, int b, int[][] pointer){
        CollisionData temp = cd[a];
        cd[a] = cd[b];
        cd[b] = temp;
        int index = pointer[cd[a].first][cd[a].second];
        pointer[cd[a].first][cd[a].second] = pointer[cd[b].first][cd[b].second];
        pointer[cd[b].first][cd[b].second] = index;
    }
    //check if it is empty
    public boolean isEmpty( ) {
        return numNodes == 0;
    }
    //print the heap
    public void print(){
        for(int i = 0; i<numNodes; i++){
            if(cd[i].collisionTime != Integer.MAX_VALUE){
                System.out.print("["+ cd[i].collisionTime +"]");
            }
        }
        System.out.println("---------------------");
    }
}
