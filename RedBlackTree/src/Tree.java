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
    final int BLACK = 1;
    final int RED = 0;
    
    Tree(){
        nil = new node();
        nil.left = null;
        nil.right = null;
        nil.color = nil.BLACK;
        root = nil;
        
    }
    
    public void rotateLeft(node current){
        node tmp = current.right;
        transplant(current, tmp);
        setRightNode(current, tmp.left);
        setLeftNode(tmp, current);
    }
        
    public void rotateRight(node current){
        node tmp = current.left;
        transplant(current, tmp);
        setLeftNode(current, tmp.right);
        setRightNode(tmp, current);
    }
    
    private void transplant(node oldNode, node newNode) {
        if (oldNode.p != nil) {
            if (oldNode.equals(oldNode.p.left)) {
                setLeftNode(oldNode.p, newNode);
            } else {
                setRightNode(oldNode.p, newNode);
            }
        } else {
            root = newNode;
            root.p = nil;
        }
 
    }
    

    
    private void setLeftNode(node current, node newLeft) {
        current.left = newLeft;
        newLeft.p = current;
    }
    
    private void setRightNode(node current, node newRight) {
        current.right = newRight;
        newRight.p = current;
    }
   
    
    
    
    public void insertion(node z){
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
        System.out.println("Hello");
        //this.rotateLeft(z);
        this.insertionfix(z);
    }
    
    public void insertionfix(node z){
        while( z.p.color == RED){
            System.out.print("in : ");
            if(z.p == z.p.p.left){
                System.out.println("2");
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
                System.out.print("1");
                node y = z.p.p.left;
                if(y.color == RED){
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                }
                else if(z == z.p.left){
                    System.out.println("5");
                    z = z.p;
                    this.rotateRight(z);
                }else{
                    System.out.print("4");
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    this.rotateLeft(z.p.p);
                }
            }
        }
        root.color = BLACK;
    }
    
    public void printOut(){
        recusiveCall(root);
        System.out.println();
    }
    
    public void recusiveCall(node p){
        if(p==nil){
            return;
        }
        recusiveCall(p.left);
        System.out.println(p.key+ " : " + p.color);
        recusiveCall(p.right);
    }
}
