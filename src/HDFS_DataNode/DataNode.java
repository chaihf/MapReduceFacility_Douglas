package HDFS_DataNode;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import MetaData.DataNodeMeta;
import MetaData.FileBlockMeta;
import Util.DataNodeBlockTable;

public class DataNode {
	private DataNodeMeta _datanodemeta;
	
	public DataNode(){}
	public DataNode(DataNodeMeta meta){
		this._datanodemeta = meta;
	}
	
	public DataNodeMeta getMeta(){
		return this._datanodemeta;
	}
	
	public void setMeta(DataNodeMeta datanodemeta){
		this._datanodemeta = datanodemeta;
	}
	
	public static void main(String args[]) throws UnknownHostException, RemoteException, MalformedURLException{
		Properties properties = new Properties();
		try {
			properties.load(DataNode.class.getResourceAsStream("../config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String capacity = properties.getProperty("DATANODE_CAPACITY");
		String dfsDirectory = properties.getProperty("DFS_DIRECTORY");
		String localDirectory = properties.getProperty("LOCAL_DIRECTORY");
		
		
		/* initialization */
		ArrayList<String> filelist = new ArrayList<String>();
		String hostname = InetAddress.getLocalHost().getHostName();
		DataNodeBlockTable table = new DataNodeBlockTable(filelist);
		FileBlockMeta fileblockmeta = new FileBlockMeta(table);
		DataNodeMeta datanodemeta = new DataNodeMeta(hostname, fileblockmeta);
			
		ProcessData pd = new ProcessData(datanodemeta);
		Naming.rebind("rmi://"+hostname+"/ProcessData", pd);
		
		//DataNode thenode = new DataNode(datanodemeta);
	
		//TransferData td = new TransferData();
		//HeartBeat hb = new HeartBeat(datanodemeta);
		//Naming.rebind("rmi://"+hostname+"/TransferData", td);
		//Naming.rebind("rmi://"+hostname+"/HeartBeat", pd);
		
		System.out.println("Data Node "+hostname+" ready!");
	}
	
}
