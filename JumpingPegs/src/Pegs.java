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
    StringBuffer temp;
    int numPegs;
    int initialPegs;
    int secondPegs;
    Moves[] possibleMoves;
    Pegs(StringBuffer num){
        pegs = num;
        temp = num;
        //System.out.println(pegs);
        
    }
    
    public int numberOfIndex(){
        return pegs.length();
    }
    
    public int possibleJump(int c, int d){
        if(c < pegs.length() ){
            int compare = Character.getNumericValue(pegs.charAt(c));
            if(compare == 1){
                return 0;
            }
            else if (compare == 0){
                if(c<pegs.length() && c>=0){
                    if(d==1)
                        c++;
                    else
                        c--;
                    if(c<pegs.length() && c>=0){
                        compare = Character.getNumericValue(pegs.charAt(c));
                        if(compare == 0){
                            return 1;
                        }
                    }
                }
            }
        }
        return 2;
    }
    public int allPossibleSolution(){
        possibleMoves = new Moves[pegs.length()*2];
        for (int i = 0; i<pegs.length()*2; i++){
            possibleMoves[i] = new Moves(0,0);
        }
        int count = 0;
        for(int i = 0 ; i<pegs.length(); i++){
            int initial = Character.getNumericValue(pegs.charAt(i));
            if(initial == 0){
                if(possibleJump(i+1,1) == 0){
                    if(i+2 < pegs.length()){
                        int compare = Character.getNumericValue(pegs.charAt(i+2));
                        if(compare == 0){
                            possibleMoves[count] =new Moves(i, i+2);
                            System.out.println(possibleMoves[count].first + "" +possibleMoves[count].second);
                            count++;
                        }
                    }
                }
                if(possibleJump(i+1,1) == 1){
                    if(i+3 < pegs.length()){
                        int compare = Character.getNumericValue(pegs.charAt(i+3));
                        if(compare == 0){
                            possibleMoves[count] =new Moves(i, i+3);
                            System.out.println(possibleMoves[count].first + "" +possibleMoves[count].second);
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
    
    public boolean jumpingRight(int a){
        if(a<0 || a>this.numberOfIndex()){
            return false;
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
                return false;
            }
        }else{
            return false;
        }
        if(b < this.numberOfIndex()){
            int initial = Character.getNumericValue(pegs.charAt(a));
            int compare = Character.getNumericValue(pegs.charAt(b));
            //System.out.println(initial +" "+ compare);
            if(compare == 0 && initial == 0){
                pegs.setCharAt(b, '1');
                pegs.deleteCharAt(a);
                return true;
            }
        }
        return false;
    }  
    
    public boolean jumpingLeft(int a){
        if(a<0 || a>this.numberOfIndex()){
            return false;
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
                return false;
            }
        }else{
            return false;
        }
        if(b >= 0){
            int initial = Character.getNumericValue(pegs.charAt(a));
            int compare = Character.getNumericValue(pegs.charAt(b));
            //System.out.println(initial +" "+ compare);
            if(compare == 0 && initial == 0){
                pegs.setCharAt(b, '1');
                pegs.deleteCharAt(a);
                return true;
            }
            
        }
        return false;
    }
    
    public boolean complete(){
        for(int i = 0; i<pegs.length(); i++){
            if(pegs.charAt(i) == '0'){
                return false;
            }
        }
        return true;
    }
    public void jump(int a, int b){
        if(a != b){
            temp.setCharAt(b, '1');
            temp.deleteCharAt(a);
        }
    }
    
    public boolean recursiveBackTracking(int index, int initial){
        boolean successful;
        if(this.complete()){
            return true;
        }else{
            successful = false;
            int count = this.allPossibleSolution();
            if(count == 0){
                
            }
            for(int i = 0; i<count; i++){
                this.jump(this.possibleMoves[i].first, this.possibleMoves[i].second);
                this.printString();
                successful = this.recursiveBackTracking(index, initial);
            }
//            while(index<pegs.length()){
//                if(this.jumpingRight(index)){
//                    System.out.println(index+ " i");
//                    index++;
//                    this.printString();
//                    successful = this.recursiveBackTracking(index, initial);
//                    if(!successful){
//                        System.out.println(index);
//                        this.printString();
//                    }
//                }else if(this.jumpingLeft(index)){
//                    System.out.println(index+ " h");
//                    index++;
//                    this.printString();
//                    successful = this.recursiveBackTracking(index, initial);
//                    if(!successful){
//                        System.out.println(index);
//                        this.printString();
//                    }
//                }
//            }
            
      }
        return successful;
    }
    
    public void printString(){
        System.out.println(temp);
    }
}
