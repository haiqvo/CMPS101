
import java.util.Stack;

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
    StringBuffer original;
    int numPegs;
    int initialPegs;
    int secondPegs;
    int first;
    int second;
    Moves[] possibleMoves;
    Moves[] setMoves;
    Stack<Moves> stack;
    Pegs(StringBuffer num){
        original = new StringBuffer(num);
        pegs = num;
        
        stack = new Stack<Moves>();
        //System.out.println(pegs);
        
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
                            //System.out.println(possibleMoves[count].first + "" +possibleMoves[count].second);
                            count++;
                            possibleMoves[count] =new Moves(i+2, i);
                            //System.out.println(possibleMoves[count].first + "" +possibleMoves[count].second);
                            count++;
                        }
                    }
                }

                if(possibleJump(i+1,1) == 1){
                    if(i+3 < pegs.length()){
                        int compare = Character.getNumericValue(pegs.charAt(i+3));
                        if(compare == 0){
                            possibleMoves[count] =new Moves(i, i+3);
                            //System.out.println(possibleMoves[count].first + "" +possibleMoves[count].second);
                            count++;
                            possibleMoves[count] =new Moves(i+3, i);
                            //System.out.println(possibleMoves[count].first + "" +possibleMoves[count].second);
                            count++;
                        }
                    }
                }
            }
        }
        setMoves = new Moves[count];
        System.arraycopy(possibleMoves, 0, setMoves, 0, count);
        return count;
    }
    
    
    public boolean complete(){
        for(int i = 0; i<pegs.length(); i++){
            if(Character.getNumericValue(pegs.charAt(i)) == 0){
                return false;
            }
        }
        return true;
    }
    public boolean jump(int a, int b){
        if(a != b){
            first = a;
            second = b;
            pegs.setCharAt(b, '1');
            pegs.deleteCharAt(a);
            return true;
        }
        return false;
    }
    
    public void jumpBack(){
        Moves moves = stack.pop();
        this.pegs.insert(moves.first, "0");
        if(Character.getNumericValue(pegs.charAt(moves.second)) == 1){
            this.pegs.setCharAt(moves.second, '0');
        }else{
            this.pegs.deleteCharAt(moves.first);
        }
        
    }
    
    public boolean recursiveBackTracking(int index){
        boolean successful;
        if(this.complete()){
            return true;
        }else{
            successful = false;
            int count = this.allPossibleSolution();
            //System.out.println(count);
            for(Moves move : setMoves){
                if(this.jump(move.first,move.second)){
                    stack.push(move);
                }   
                //this.printString();
                successful = this.recursiveBackTracking(index+1);
                if(!successful){
                    this.jumpBack();
                }else{
                    return successful;
                }
                
            }

            
      }
        return successful;
    }
    public void printStack(){
        Moves[] finalMoves = new Moves[stack.size()];
        int count = 0;
        while(!stack.empty()){
            Moves temp = stack.pop();
            finalMoves[count] = new Moves(temp.first, temp.second);
            count++;
        }
        int length = finalMoves.length-1;
        //System.out.println(this.original);
        while(length>=0){
            original.setCharAt(finalMoves[length].second, '1');
            original.deleteCharAt(finalMoves[length].first);
            length--;
            System.out.println(this.original);
        }
    }
    
    public void printString(){
        System.out.println(pegs);
    }
}
