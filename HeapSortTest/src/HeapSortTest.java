public class HeapSortTest {
 
    /** Fixed-size array heap representation */
    private int[ ] array;
    /** Number of nodes in the heap (array) */
    private int n = 0;
 
    /** Constructs a heap of specified size */
    public HeapSortTest( final int size ) {
        array = new int[ size ];
    }
 
    /** Returns (without removing) the smallest (min) element from the heap. */
    public int min( ) {
        if( isEmpty( ) ) {
            throw new RuntimeException( "Heap is empty!" );
        }
 
        return array[ 0 ];
    }
 
    /** Removes and returns the smallest (min) element from the heap. */
    public int removeMin( ) {
        if( isEmpty( ) ) {
            throw new RuntimeException( "Heap is empty!" );
        }
 
        final int min = array[ 0 ];
        array[ 0 ] = array[ n - 1 ];
        if( --n > 0 ) siftDown( 0 );
        return min;
    }
    
    public void delete(int index){
        array[index] = array[n-1];
        if(--n>1) siftDown(index);
    }
 
    /** Checks if the heap is empty. */
    public boolean isEmpty( ) {
        return n == 0;
    }
 
    /** Adds a new element to the heap and sifts up/down accordingly. */
    public int add( final int value ) {
        if( n == array.length ) {
            throw new RuntimeException( "Heap is full!" );
        }
 
        array[ n ] = value;
        int count = siftUp( n );
        n++;
        return count;
    }
 
    /**
     * Sift up to make sure the heap property is not broken.
     * This method is used when a new element is added to the heap 
     * and we need to make sure that it is at the right spot.
     */
    private int siftUp( int index ) {
       int count = 0;
       while(index>0){
            int parent = (index-1)/2;
            if(array[parent]> array[index]){
                swap(parent, index);
                count++;
            }
            index = parent;
            
        }
       return count;
    }
 
    /** 
     * Sift down to make sure that the heap property is not broken 
     * This method is used when removing the min element, and we need 
     * to make sure that the replacing element is at the right spot.
     */
    private void siftDown( int index ) {
 
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
 
        // Check if the children are outside the array bounds.
        if( rightChild >= n && leftChild >= n ) return;
 
        // Determine the smallest child out of the left and right children.
        int smallestChild = 
            array[ rightChild ] > array[ leftChild ] ? leftChild : rightChild;
        while(array[index]>array[smallestChild] && index<n){
            swap( smallestChild, index );
            index = smallestChild;
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            smallestChild = 
            array[ rightChild ] > array[ leftChild ] ? leftChild : rightChild;
        }
 
    }
 
    /** Helper method for swapping array elements */
    private void swap( int a, int b ) {
        int temp = array[ a ];
        array[ a ] = array[ b ];
        array[ b ] = temp;
    }
    public void print(){
        for(int i=0; i<n; i++){
            System.out.print(array[i]);
        }
        //System.out.print(array[0]);
        System.out.println();
        System.out.println("---------------------");
    }
 
    /** Test Method */
    public static void main( String[ ] args ) {
 
        int[ ] input = {6, 5, 3, 1, 8, 7, 2, 4};
        HeapSortTest heap = new HeapSortTest( input.length );
        for( int i = 0; i < input.length; i++ ) {
            int count = heap.add( input[ i ] );
            System.out.print(count);
        }
        System.out.println();
        while(!heap.isEmpty()){
            System.out.print(heap.removeMin());
        }
    }
}

