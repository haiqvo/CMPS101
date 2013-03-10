public class HashTables {
      private final static int TABLE_SIZE = 4096;
 
      HashEntry[] table;
      double count;//the count to keep track of how many insert have been made
      double length;//the current length of table
      int h1;//the first hash key
      int h2;//the second hash key
      
      HashTables() {
        count = 0;
        length = TABLE_SIZE;
        h1 = 4093;
        h2 = 4092;
        table = new HashEntry[(int)length];
        for (int i = 0; i < length; i++)//initializing the table
              table[i] = null;
      }
      
      public void put(int key, StringBuffer value){
          int hash = key % h1;//getting the hash key
          int offset = 1+(key % h2);
          if(table[hash] == null || !table[hash].getValue().equals(value.toString())){
              while (table[hash] != null){//check to see if there is a null
                  hash = hash + offset;//double hash if something is already there
              }
              table[hash] = new HashEntry(key, value);//insert need entry
              count++;
              //System.out.println(count);
              if(this.count() >= length){//check if it is under 0.2
                  HashEntry[] newTable = new HashEntry[(int)length*4];
                  for (int i = 0; i<length*4; i++){
                      newTable[i] = null;//initialize temp table
                  }
                  for(int j = 0; j<length; j++){
                      if(table[j] != null){
                          int tempKey = table[j].getKey();
                          int hashIndex = tempKey % h1;
                          newTable[hashIndex] = table[j];//set temp to table value
                      }
                  }
                  length = length*4;//increase length
                  table = newTable;//set table to new table
              }
          }
      }
      
      public boolean get(int key, StringBuffer value){//search for a value
          int hash = key % h1;
          int offset = 1+ (key % h2);
          //System.out.println("B: Hash " + hash );
          while(table[hash] != null){
              if(table[hash].getValue().equals(value.toString())){
                  //System.out.println("match found");
                  return true;//return true if macth is found
              }
              hash = hash + offset;
          }
          return false;// return false if there is a null
      }
      
      public int count(){
          int countNum = 0;
          for(int j = 0; j<length; j++){
              if(table[j] != null){
                  countNum++;
                  //System.out.println("Hash "+j+" Key "+table[j].getKey()+" String "+table[j].getValue());
              }
          }
          return countNum;
      }
 
}
