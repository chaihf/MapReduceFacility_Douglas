package Util;

import java.util.ArrayList;
import java.util.List;

public class DataNodeBlockTable {
	public DataNodeBlockTable(){}
	
	private List<String> _blocklist;
	
	public DataNodeBlockTable(ArrayList<String> blocklist){
		this.setBlocklist(blocklist);
	}

	public List<String> getBlocklist() {
		return this._blocklist;
	}

	public void setBlocklist(List<String> _blocklist) {
		this._blocklist = _blocklist;
	}
	
	public void add(String filename){
		this._blocklist.add(filename);
	}
}
