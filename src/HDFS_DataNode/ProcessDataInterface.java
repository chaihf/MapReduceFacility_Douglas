package HDFS_DataNode;

import java.rmi.Remote;

import MetaData.DataNodeMeta;

public interface ProcessDataInterface extends Remote {
	public void PlaceSplittedFile(String filename, byte[] filecontent);
	public void TransferSplittedFile(String filename, String datanodename);
	public DataNodeMeta SendHeartBeat();
}
