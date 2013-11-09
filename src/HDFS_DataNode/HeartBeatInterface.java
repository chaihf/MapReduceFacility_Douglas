package HDFS_DataNode;

import java.rmi.Remote;

import MetaData.DataNodeMeta;

public interface HeartBeatInterface extends Remote{
	public DataNodeMeta SendHeartBeat();
}
