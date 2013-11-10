package HDFS_DataNode;

import java.rmi.Remote;
import java.util.ArrayList;

public interface ProcessDataInterface extends Remote {
	public void PlaceSplittedFile(String filename, byte[] filecontent);
<<<<<<< HEAD
	public byte[] GetSplittedFile(String filename);
	
=======
	public void TransferSplittedFile(String fromfilename, String tofilename, String datanodename);
>>>>>>> 01faec38902ddf565b4e9b4d5a15672f66ad405d
}
