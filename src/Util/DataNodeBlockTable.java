package Util;

import java.util.ArrayList;
import java.util.List;

public class DataNodeBlockTable {
		private ArrayList<String> _blocklist;
		
		public DataNodeBlockTable(){
			this._blocklist = new ArrayList<String>();
		}
                        
        public DataNodeBlockTable(ArrayList<String> blocklist){
                this.setBlocklist(blocklist);
        }

        public ArrayList<String> getBlocklist() {
                return this._blocklist;
        }

        public void setBlocklist(ArrayList<String> _blocklist) {
            this._blocklist = new ArrayList<String>(_blocklist);            	
        }
        
        public void add(String filename){
                this._blocklist.add(filename);
        }
}