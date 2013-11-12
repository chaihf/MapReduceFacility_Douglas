package Util;
import java.util.HashMap;

import MetaData.*;
public class NameNodeWorkerFileMapper {
	private HashMap<String, DataNodeBlockTable> _workerFileTable;
	public NameNodeWorkerFileMapper() {
		this._workerFileTable = new HashMap<String, DataNodeBlockTable>();
	}
	
	public boolean containsNode(String s) {
		return this._workerFileTable.containsKey(s);
	}
	
	public void update(DataNodeMeta dnm) {
		this._workerFileTable.put(dnm.getHostname(), dnm.getDataNodeBlockTable());
	}
}
