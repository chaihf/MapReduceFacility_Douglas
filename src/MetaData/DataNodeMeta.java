package MetaData;

/*
 * Datanode Metadata: 
 * 	datanode hostname, fileblock meta
 */

public class DataNodeMeta {
	public DataNodeMeta(){}
	
	private String _hostname;
	private FileBlockMeta _fbmeta;
	
	public DataNodeMeta(String host, FileBlockMeta meta){
		this.setHostname(host);
		this.setFBMeta(meta);
	}

	public String getHhostname() {
		return _hostname;
	}

	public void setHostname(String _hostname) {
		this._hostname = _hostname;
	}

	public FileBlockMeta getFBMeta() {
		return _fbmeta;
	}

	public void setFBMeta(FileBlockMeta _fbmeta) {
		this._fbmeta = _fbmeta;
	}
}
