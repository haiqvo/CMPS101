public class HashEntry {
      private int key;//the key to hash
      private StringBuffer value;//the stringBuffer 
 
      HashEntry(int key, StringBuffer value) {
            this.key = key;
            this.value = value;
      }
 
      public String getValue() {
            return value.toString();//return the value as a string
      }
 
      public void setValue(StringBuffer value) {
            this.value = value;
      }
 
      public int getKey() {
            return key;//return key
      }
}