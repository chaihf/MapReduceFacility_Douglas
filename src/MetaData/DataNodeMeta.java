package MetaData;
import Util.*;
/*
 * Datanode Metadata: 
 * 	datanode hostname, fileblock meta
 */

public class DataNodeMeta {
	public DataNodeMeta(){}
	
	private String _hostname;	
	private DataNodeBlockTable _table;
	
	public DataNodeMeta(String host){
		this.setHostname(host);
		this._table = new DataNodeBlockTable();
	}

	public String getHostname() {
		return _hostname;
	}

	public void setHostname(String _hostname) {
		this._hostname = _hostname;
	}

	public DataNodeBlockTable getDataNodeBlockTable() {
		return this._table;
	}


}
