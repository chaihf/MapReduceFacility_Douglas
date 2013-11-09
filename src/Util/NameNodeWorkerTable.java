package Util;
import java.util.HashMap;
import java.util.Map;

import MetaData.DataNodeMeta;

public class NameNodeWorkerTable {
	private Map<String, DataNodeMeta> workerTable;
	
	public NameNodeWorkerTable() {
		workerTable = new HashMap<String, DataNodeMeta>();
	}
	
}
