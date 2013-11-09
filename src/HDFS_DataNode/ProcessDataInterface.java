package HDFS_DataNode;

import java.rmi.Remote;

public interface ProcessDataInterface extends Remote {
	public void PlaceSplittedFile(String filename, byte[] filecontent);
	public byte[] GetSplittedFile(String filename);
}
