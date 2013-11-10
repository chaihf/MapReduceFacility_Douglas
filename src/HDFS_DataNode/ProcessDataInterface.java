package HDFS_DataNode;

import java.rmi.Remote;
import java.util.ArrayList;

public interface ProcessDataInterface extends Remote {
	public void PlaceSplittedFile(String filename, byte[] filecontent);

	public byte[] GetSplittedFile(String filename);
	

	public void TransferSplittedFile(String fromfilename, String tofilename, String datanodename);

}
