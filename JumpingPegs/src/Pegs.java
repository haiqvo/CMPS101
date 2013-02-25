/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hai
 */
public class Pegs {
    private StringBuffer pegs;
    int numPegs;
    Pegs(StringBuffer num){
        pegs = num;
        //System.out.println(pegs);
    }
    
    public int numberOfIndex(){
        return pegs.length();
    }
    
    public int possibleJump(int c, int d){
        int compare = Character.getNumericValue(pegs.charAt(c));
        if(compare == 1){
            return 0;
        }else{
            if(d == 1){
                c++;
            }else{
                c--;
            }
            compare = Character.getNumericValue(pegs.charAt(c));
            if(compare == 0){
                return 1;
            }
        }
        return 2;
    }
    
    public void jumpingRight(int a){
        if(a == 1){
            return;
        }
        int c = a + 1;
        int b;
        int jumpPossible;
        if(c < this.numberOfIndex()){
            jumpPossible = this.possibleJump(c, 1);
            if(jumpPossible == 0){
                b = a + 2;
            }
            else if(jumpPossible == 1){
                b = a + 3;
            }else{
                return;
            }
        }else{
            return;
        }
        if(b < this.numberOfIndex()){
            int initial = Character.getNumericValue(pegs.charAt(a));
            int compare = Character.getNumericValue(pegs.charAt(b));
            //System.out.println(initial +" "+ compare);
            if(compare == 0){
                pegs.setCharAt(a, '1');
                pegs.deleteCharAt(b);
            }
            
        }
    }  
    
    public void jumpingLeft(int a){
        if(a == 1){
            return;
        }
        int c = a - 1;
        int b;
        int jumpPossible;
        if(c >= 0){
            jumpPossible = this.possibleJump(c, 0);
            if(jumpPossible == 0){
                b = a - 2;
            }
            else if(jumpPossible == 1){
                b = a - 3;
            }else{
                return;
            }
        }else{
            return;
        }
        if(b >= 0){
            int initial = Character.getNumericValue(pegs.charAt(a));
            int compare = Character.getNumericValue(pegs.charAt(b));
            //System.out.println(initial +" "+ compare);
            if(compare == 0){
                pegs.setCharAt(a, '1');
                pegs.deleteCharAt(b);
            }
            
        }
    }
    
    public void printString(){
        System.out.println(pegs);
    }
}
