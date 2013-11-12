package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import HDFS_DataNode.*;

public class NameNodeStubTable {

//	private HashMap<String, ProcessDataInterface> _stubTable;
	private ArrayList<String> _stubTable;
	public NameNodeStubTable() {
		this._stubTable = new ArrayList<String>();
	}
	
	

//	public int size() {
//		// TODO Auto-generated method stub
//		return this._stubTable.size();
//	}
//	
//	public ArrayList<ProcessDataInterface> GetStubList() {
//		ArrayList<ProcessDataInterface> arr = new ArrayList<ProcessDataInterface>();
//		Iterator it = this._stubTable.entrySet().iterator();
//		while(it.hasNext()) {
//			Map.Entry<String, ProcessDataInterface> entry = (Map.Entry<String, ProcessDataInterface>) it.next();
//			arr.add(entry.getValue());
//		}
//		return arr;
//	}
	
	public ArrayList<String> GetStubList() {
		return this._stubTable;
	}
	
	public void add(String s) {
		this._stubTable.add(s);
	}
}
