package MetaData;

import Util.DataNodeBlockTable;

/*
 * File Block Metadata:
 * 	data node block table
 */

public class FileBlockMeta {
	public FileBlockMeta(){}
	
	private DataNodeBlockTable _dnbt;
	
	public FileBlockMeta(DataNodeBlockTable dnbt){
		this.setDnbt(dnbt);
	}

	public DataNodeBlockTable getDnbt() {
		return this._dnbt;
	}

	public void setDnbt(DataNodeBlockTable _dnbt) {
		this._dnbt = _dnbt;
	}
	
	
}
