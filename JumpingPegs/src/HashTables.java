public class HashTables {
      private final static int TABLE_SIZE = 4096;
 
      HashEntry[] table;
      double count = 0;
      double length = TABLE_SIZE;
 
      HashTables() {
            table = new HashEntry[(int)length];
            for (int i = 0; i < length; i++)
                  table[i] = null;
      }
      
      public void put(int key, StringBuffer value){
          int hash = key % (int)length;
          while (table[hash] != null){
              hash = (hash+1) % (int)length;
          }
          table[hash] = new HashEntry(key, value);
          count++;
          if(count*5> length){
              HashEntry[] newTable = new HashEntry[(int)length*2+1];
              for (int i = 0; i<length*2+1; i++){
                  newTable[i] = null;
              }
              for(int j = 0; j<length; j++){
                  if(table[j] != null){
                      int tempKey = table[j].getKey();
                      int hashIndex = tempKey % ((int)length*2+1);
                      newTable[hashIndex] = table[j];
                  }
              }
              length = length*2+1;
              table = newTable;
          }
      }
      
      public boolean get(int key, StringBuffer value){
          int hash = key % (int)length;
          while(table[hash] != null){
              if(table[hash].getValue().equals(value.toString())){
                  return true;
              }
              hash = hash+1 % (int)length;
          }
          return false;
      }
      
      public void debug(){
          for(int j = 0; j<length; j++){
              if(table[j] != null){
                  System.out.println("Hash "+j);
                  System.out.println("Key "+table[j].getKey()+" String "+table[j].getValue());
              }
          }
      }
 
}
