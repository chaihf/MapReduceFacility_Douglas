package HDFS_DataNode;

import java.net.InetAddress;
import java.rmi.Naming;

import MetaData.DataNodeMeta;
import MetaData.FileBlockMeta;

public class DataNode {
	private DataNodeMeta _datanodemeta;
	
	public DataNode(){}
	public DataNode(DataNodeMeta meta){
		this._datanodemeta = meta;
	}
	
	public DataNodeMeta getMeta(){
		return this._datanodemeta;
	}
	
	public void setMeta(DataNodeMeta datanodemeta){
		this._datanodemeta = datanodemeta;
	}
	
	public void main(String args[]){
		/* initialization */
		String hostname = InetAddress.getLocalHost().getHostName();
		Table table = new Table();
		FileBlockMeta fileblockmeta = new FileBlockMeta(table);
		DataNodeMeta datanodemeta = new DataNodeMeta(hostname, fileblockmeta);
		
		//DataNode thenode = new DataNode(datanodemeta);
		
		ProcessData pd = new ProcessData(datanodemeta);
		HeartBeat hb = new HeartBeat(datanodemeta);
		
		Naming.rebind("rmi://"+hostname+"/ProcessData", pd);
		Naming.rebind("rmi://"+hostname+"/HeartBeat", pd);
		
		System.out.println("Data Node "+hostname+" ready!");
	}
	
}
