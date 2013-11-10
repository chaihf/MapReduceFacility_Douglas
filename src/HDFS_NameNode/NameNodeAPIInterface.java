package HDFS_NameNode;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import MetaData.*;

public interface NameNodeAPIInterface extends Remote{
	public FileMeta GetFileInfo(String fileName) throws RemoteException;
	public DataNodeMeta GetNodeInfo(String nodeName) throws RemoteException;
	public Boolean TransferFile(String fileName, String DataNode1, String DataNode2) throws RemoteException;
	public ArrayList<String> GetDataNodeRMI();
//	public FileMeta GetFileInfo(String name) throws RemoteException;

}
