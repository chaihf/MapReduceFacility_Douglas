package Util;
import java.util.HashMap;
import java.util.Map;

import MetaData.FileMeta;

public class NameNodeFileTable {
	
	private Map<String, FileMeta> fileTable;
		
	public NameNodeFileTable() {
		this.fileTable = new HashMap<String, FileMeta>();
	}
}
