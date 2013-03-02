public class HashEntry {
      private int key;
      private StringBuffer value;
 
      HashEntry(int key, StringBuffer value) {
            this.key = key;
            this.value = value;
      }
 
      public String getValue() {
            return value.toString();
      }
 
      public void setValue(StringBuffer value) {
            this.value = value;
      }
 
      public int getKey() {
            return key;
      }
}