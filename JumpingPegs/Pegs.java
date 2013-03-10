
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
    private StringBuffer pegs;//the pegs that will be use to jump
    StringBuffer original;//same as pegs but use in the end to show solution
    Moves[] possibleMoves;//the uninitilize array 
    Moves[] setMoves;//the initilize array used for the foreach loop
    Stack<Moves> stack;//keep all the correct moves
    HashTables table;//table to store all the bad solution
    Pegs(StringBuffer num){
        original = new StringBuffer(num);//initilize the stringBuffer
        pegs = num;//initilize the stringBuffer
        table = new HashTables();
        
        stack = new Stack<Moves>();
        //System.out.println(pegs);
        
    }
    
    
    public int possibleJump(int c, int d){
        if(c < pegs.length() ){//check if c is with in range
            int compare = Character.getNumericValue(pegs.charAt(c));
            if(compare == 1){//if there is an X return 0
                return 0;
            }
            else if (compare == 0){//if it is a 0 continue
                if(c<pegs.length() && c>=0){
                    if(d==1)
                        c++;
                    else
                        c--;
                    if(c<pegs.length() && c>=0){//if the next one is also 0 return 1
                        compare = Character.getNumericValue(pegs.charAt(c));
                        if(compare == 0){
                            return 1;
                        }
                    }
                }
            }
        }
        return 2;//else return 2
    }
    public int allPossibleSolution(){//finds all possible solutions
        possibleMoves = new Moves[pegs.length()*2];//make an uninitialize 
        for (int i = 0; i<pegs.length()*2; i++){
            possibleMoves[i] = new Moves(0,0);
        }
        int count = 0;
        for(int i = 0 ; i<pegs.length(); i++){
            int initial = Character.getNumericValue(pegs.charAt(i));
            if(initial == 0){//make sure the peg is a single peg
                if(possibleJump(i+1,1) == 0){//check if jumpable
                    if(i+2 < pegs.length()){//make sure if in range
                        int compare = Character.getNumericValue(pegs.charAt(i+2));
                        if(compare == 0){
                            possibleMoves[count] =new Moves(i, i+2);//set new move
                            count++;
                            possibleMoves[count] =new Moves(i+2, i);//set inverse of it
                            count++;
                        }
                    }
                }

                if(possibleJump(i+1,1) == 1){//this time check if there is a X
                    if(i+3 < pegs.length()){
                        int compare = Character.getNumericValue(pegs.charAt(i+3));
                        if(compare == 0){
                            possibleMoves[count] =new Moves(i, i+3);//set new move
                            count++;
                            possibleMoves[count] =new Moves(i+3, i);//set inverse of it 
                            count++;
                        }
                    }
                }
            }
        }
        setMoves = new Moves[count];//set the initialize array of fixed size
        System.arraycopy(possibleMoves, 0, setMoves, 0, count); //check if it is possible
        return count;
    }
    
    
    public boolean complete(){//check if it is complete
        for(int i = 0; i<pegs.length(); i++){
            if(Character.getNumericValue(pegs.charAt(i)) == 0){
                return false;
            }
        }
        return true;
    }
    public boolean jump(int a, int b){//pick up a peg and move it to b pegs
        if(a != b){
            pegs.setCharAt(b, '1');
            pegs.deleteCharAt(a);
            return true;
        }
        return false;
    }
    
    public void jumpBack(){//go backwards
        Moves moves = stack.pop();//pop a and b from stack
        this.pegs.insert(moves.first, "0");//put a back
        if(Character.getNumericValue(pegs.charAt(moves.second)) == 1){
            this.pegs.setCharAt(moves.second, '0');//remove the extra peg from b
        }else{
            this.pegs.deleteCharAt(moves.first);
        }
        
    }
    
    public boolean recursiveBackTracking(){//the recursive backtracking 
        boolean successful;
        successful = false;//initilize the function to false
            if(this.complete()){//check if it is complete
                return true;//if so return true
            }else{
                int pegsInt = Integer.parseInt(pegs.toString(),2);//get key for hash
                //System.out.println("B: "+pegsInt);
                if(!table.get(pegsInt, pegs)){//check hash table for value
                //System.out.println(pegs+"checked");
                    int count = this.allPossibleSolution();//look for all possible solution
                        //System.out.println(count);
                    for(Moves move : setMoves ){//use for each loop to go through all possible moves
                        //System.out.println("inside");
                        if(this.jump(move.first,move.second)){//jump peg
                            stack.push(move);//push move to stack
                        }
                        //this.printString();
                        successful = this.recursiveBackTracking();//recursive call
                        if(!successful){//if it is false go backwards
                            //System.out.println(pegs+"inserted");
                            this.insertHashTable();//insert the no solution in the hash table
                            this.jumpBack();//move backwards
                        }else{
                            return successful;//return true is done
                        }
                    }
                }
        }       
        return successful;//return if false
    }
    
    public void insertHashTable(){//insert table 
        int pegsInt = Integer.parseInt(pegs.toString(),2);
        //System.out.println("A: "+pegsInt);
        table.put(pegsInt, pegs);//insert into table
        //table.debug();
    }
    
    public void printStack(){//print out solution
        Moves[] finalMoves = new Moves[stack.size()];//make a array of correct moves
        int count = 0;
        while(!stack.empty()){
            Moves temp = stack.pop();
            finalMoves[count] = new Moves(temp.first, temp.second);//storing the correct moves
            count++;
        }
        int length = finalMoves.length-1;
        //System.out.println(this.original);
        while(length>=0){//print out correct answer
            original.setCharAt(finalMoves[length].second, '1');
            original.deleteCharAt(finalMoves[length].first);
            length--;
            System.out.println(this.original);
        }
    }
    
    public void printString(){//print string
        System.out.println(pegs);
    }
}
