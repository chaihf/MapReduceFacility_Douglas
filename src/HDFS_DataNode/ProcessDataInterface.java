package HDFS_DataNode;

import java.rmi.Remote;

public interface ProcessDataInterface extends Remote {
	public void PlaceSplittedFile(String filename, byte[] filecontent);
	public void TransferSplittedFile(String fromfilename, String tofilename, String datanodename);
}
