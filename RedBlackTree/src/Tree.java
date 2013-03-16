/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hai
 */
public class Tree {
    node root;
    node nil;
    node current_node;
    final int BLACK = 1;
    final int RED = 0;
    int max;
    int min;
    
    Tree(){
        nil = new node();
        nil.left = null;
        nil.right = null;
        nil.color = nil.BLACK;
        root = nil;
    }

    
    public void printNode(){
        if(current_node == nil){
            System.out.println("the current node is nil");
        }else{
            System.out.println("the current node's key:" + current_node.key);
            if(current_node.color == 1){
                System.out.println("the current node's color is BLACK");
            }else{
                System.out.println("the current node's color is RED");
            }
        }
    }
    
    public void search(int x){
        current_node = find(x);
    }
    
    private node find(int x) {
        node iterator = root;
        //Start travelling the tree from the root.
        while (true) {
            if (iterator != nil) {
                if (iterator.key == x) {
                    return iterator;
                } else if (iterator.key < x) {
                    iterator = iterator.right;
                } else {
                    iterator = iterator.left;
                }
            } else {
                return nil;
            }
        }
    }
    
    public void rotateLeft(node current){
        node tmp = current.right;
        swap(current, tmp);
        swapRightNode(current, tmp.left);
        swapLeftNode(tmp, current);
    }
        
    public void rotateRight(node current){
        node tmp = current.left;
        swap(current, tmp);
        swapLeftNode(current, tmp.right);
        swapRightNode(tmp, current);
    }
    
    private void swap(node oldNode, node newNode) {
        if (oldNode.p != nil) {
            if (oldNode.equals(oldNode.p.left)) {
                swapLeftNode(oldNode.p, newNode);
            } else {
                swapRightNode(oldNode.p, newNode);
            }
        } else {
            root = newNode;
            root.p = nil;
        }
 
    }
    
    private void swapLeftNode(node current, node newLeft) {
        current.left = newLeft;
        newLeft.p = current;
    }
    
    private void swapRightNode(node current, node newRight) {
        current.right = newRight;
        newRight.p = current;
    }
   
    public void insertion(node z){
        if(z.key > max){
            max = z.key;
        }
        if(z.key < min){
            min = z.key;
        }
        node y = this.nil;
        node x = this.root;
        while(x != this.nil){
            y = x;
            if(z.key < x.key){
                x = x.left;
            }else{
                x = x.right;
            }
        }
        z.p = y;
        if(y ==  this.nil){
            this.root = z;
        }
        else if(z.key<y.key){
            y.left = z;
        }
        else{
            y.right = z;
        }
        z.right = this.nil;
        z.left = this.nil;
        z.color = z.RED;
        //System.out.println("Hello");
        //this.rotateLeft(z);
        this.insertionfix(z);
    }
    
    public void insertionfix(node z){
        while( z.p.color == RED){
            //System.out.print("in : ");
            if(z.p == z.p.p.left){
                //System.out.println("2");
                node y = z.p.p.right;
                if(y.color == RED){
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                }
                else if(z == z.p.right){
                    z = z.p;
                    this.rotateLeft(z);
                }
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    this.rotateRight(z.p.p);
                
            }
            else if(z.p == z.p.p.right){
                //System.out.print("1");
                node y = z.p.p.left;
                if(y.color == RED){
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                }
                else if(z == z.p.left){
                    //System.out.println("5");
                    z = z.p;
                    this.rotateRight(z);
                }else{
                    //System.out.print("4");
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    this.rotateLeft(z.p.p);
                }
            }
        }
        root.color = BLACK;
    }
    
    public node predecessor(){
        if(current_node != nil){
            return current_node = current_node.p;
        }else{
            return current_node;
        }
    }
    
     public node successor() {
        node current = current_node;
        if (current != nil) {
            if (current.right != nil) {
                node temp = current.right;
                while (temp.left != nil) {
                    temp = temp.left;
                }
                return current_node = temp;
            } else {
                node currentParentNode = current.p;
                while (currentParentNode != nil && current == currentParentNode.right) {
                    if (currentParentNode == root) {
                        current = currentParentNode;
                        currentParentNode = current.p;
                    } else {
                        return current_node;
                    }
 
                }
                return current_node = currentParentNode;
            }
        } else {
            return current_node;
        }
    }
    
    public void printOut(){
        System.out.print("The in-order taversal: ");
        recusiveCall(root);
        System.out.println();
        System.out.print("The preorder taversal: ");
        preOrder(root);
        System.out.println();
        System.out.print("The postorder taversal: ");
        postOrder(root);
        System.out.println();
    }
    
    public void postOrder(node p){
        if(p == nil){
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        System.out.print(p.key+ " ");
    }
    
    public void preOrder(node p){
        if(p == nil){
            return;
        }
        System.out.print(p.key+ " ");
        preOrder(p.left);
        preOrder(p.right);
    }
    
    public void recusiveCall(node p){
        if(p==nil){
            return;
        }
        recusiveCall(p.left);
        System.out.print(p.key+ " ");
        recusiveCall(p.right);
    }
}
