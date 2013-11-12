package HDFS_NameNode;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import MetaData.DataNodeMeta;
import MetaData.FileMeta;

public class NameNodeAPI extends UnicastRemoteObject implements NameNodeAPIInterface{
	private NameNode _nn;
	
	public NameNodeAPI() throws RemoteException {
		
	}
	public NameNodeAPI(NameNode n) throws RemoteException{
		this._nn = n;		
	}
	
	public FileMeta GetFileInfo(String fileName) throws RemoteException {
		return new FileMeta();
	}
//	public DataNodeMeta GetNodeInfo(String nodeName) throws RemoteException {
//		return new DataNodeMeta();
//	}
//	public Boolean TransferFile(String fileName, String DataNode1, String DataNode2) throws RemoteException {
//		return true;
//	}
	
	public ArrayList<String> GetDataNodeRMI() {
		return this._nn.GetNameNodeStubTable().GetStubList();
	}
}
