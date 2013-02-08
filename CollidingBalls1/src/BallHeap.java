/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hai
 */
public class BallHeap {
    private CollisionData[] cd;
    private int numNodes = 0;
    
    public BallHeap(int size){
        cd = new CollisionData[size];
    }
    
    public double min( ) {
        if( isEmpty( ) ) {
            throw new RuntimeException( "Heap is empty!" );
        }
        return cd[0].collisionTime;
    }
    
    public double getMin(){
        double min = cd[0].collisionTime;
        cd[0].collisionTime = Integer.MAX_VALUE;
        shiftdown(0);
        return min;
    }
    
    public void delete(int index){
        cd[index] = cd[numNodes-1];
        if(--numNodes>1) shiftdown(index);
    }
    
    public int getI(int i){
        int temp = cd[i].first;
        return temp;
    }
    public int getJ(int i){
        int temp = cd[i].second;
        return temp;
    }
    public int getIndex(){
        int temp = numNodes;
        return temp;
    }
    
    public int getSortedIndex(int x, int y){
        for(int i = 0; i<numNodes; i++){
            if(cd[i].first == x && cd[i].second ==y){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public void add ( int i, int j, double time){
        CollisionData temp = new CollisionData(i, j, time);
        cd[numNodes] = temp;
        shiftup(numNodes);
        numNodes++;
    }
    
    private void shiftup(int index){
        if(index>0){
            int parent = (index-1)/2;
            if(cd[parent].collisionTime > cd[index].collisionTime){
                swap(parent, index);
                shiftup(parent);
            }
        }
        
    }
    
    private void shiftdown(int index){
        int leftChild = 2*index+1;
        int rightChild = 2*index+2;
        
        if (rightChild >= numNodes && leftChild >= numNodes)return;
        int smallestChild =
                cd[rightChild].collisionTime > cd[leftChild].collisionTime
                ? leftChild : rightChild;
        if(cd[index].collisionTime>cd[smallestChild].collisionTime){
            swap(smallestChild, index);
            shiftdown(smallestChild);
        }
    }
    
    private void swap(int a, int b){
        CollisionData temp = cd[a];
        cd[a] = cd[b];
        cd[b] = temp;
    }
    public boolean isEmpty( ) {
        return numNodes == 0;
    }
    
    public void print(){
        for(int i = 0; i<numNodes; i++){
            if(cd[i].collisionTime != Integer.MAX_VALUE){
                System.out.print("["+ cd[i].collisionTime +"]");
            }
        }
        System.out.println("---------------------");
    }
}
