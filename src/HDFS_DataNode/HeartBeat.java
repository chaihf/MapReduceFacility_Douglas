package HDFS_DataNode;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import MetaData.DataNodeMeta;

@SuppressWarnings("serial")
public class HeartBeat extends UnicastRemoteObject implements HeartBeatInterface {
	private DataNodeMeta _meta;
	
	public HeartBeat(DataNodeMeta meta) throws RemoteException{
		this._meta = meta;
	};
	
	public DataNodeMeta SendHeartBeat(){
		return this._meta;
	}
	
}
