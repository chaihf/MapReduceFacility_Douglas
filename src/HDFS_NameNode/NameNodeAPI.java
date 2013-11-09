package HDFS_NameNode;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import MetaData.DataNodeMeta;
import MetaData.FileMeta;

public class NameNodeAPI extends UnicastRemoteObject implements NameNodeAPIInterface{
	public NameNodeAPI() throws RemoteException {
		
	}
	
	public FileMeta GetFileInfo(String fileName) throws RemoteException {
		return new FileMeta();
	}
	public DataNodeMeta GetNodeInfo(String nodeName) throws RemoteException {
		return new DataNodeMeta();
	}
	public Boolean TransferFile(String fileName, String DataNode1, String DataNode2) throws RemoteException {
		return true;
	}
}
