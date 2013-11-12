package HDFS_NameNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

import HDFS_DataNode.DataNode;
import HDFS_DataNode.ProcessData;
import MetaData.DataNodeMeta;
import Util.*;

public class NameNode {
	
	private final String DFS_DIRECTORY = "DFS_DIRECTORY";
    private final String DATANODE_CAPACITY = "DATANODE_CAPACITY";
    private final String LOCAL_DIRECTORY = "LOCAL_DIRECTORY";
    private final String DATANODE_HOSTNAME = "DATANODE_HOSTNAME";
    
    private int _dataNodeCapacity;
    private String _dfsDirectory;
    private String _localDirectory;
    private String _hostname;
    
    private NameNodeAPI _nnAPI;
    
    
    private NameNodeFileTable _nnfileTable;
    private NameNodeWorkerFileMapper _nnWorkFileMapper;
    private NameNodeFileWorkerMapper _nnFileWorkerMapper;
    private NameNodeStubTable _nnStubTable;
    
	public NameNode() {
		this._nnfileTable = new NameNodeFileTable();
		this._nnWorkFileMapper = new NameNodeWorkerFileMapper();
		this._nnFileWorkerMapper = new NameNodeFileWorkerMapper();	
		this._nnStubTable = new NameNodeStubTable();
	}
	
	public NameNodeFileTable GetNameNodeFileTable() {
		return this._nnfileTable;
	}
	
	public NameNodeWorkerFileMapper GetNameNodeWorkerFileMapper() {
		return this._nnWorkFileMapper;
	}
	
	public NameNodeFileWorkerMapper GetNameNodeFileWorkerMapper() {
		return this._nnFileWorkerMapper;
	}
	
	public NameNodeStubTable GetNameNodeStubTable() {
		return this._nnStubTable;
	}
	
	public void ParseConfiguration() {        	
     	 Properties properties = new Properties();
          try {
                  properties.load(DataNode.class.getResourceAsStream("../config.properties"));
          } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
          }
          this._dataNodeCapacity = Integer.valueOf(properties.getProperty(this.DATANODE_CAPACITY));
          this._dfsDirectory = properties.getProperty(this.DFS_DIRECTORY);
          this._localDirectory = properties.getProperty(this.LOCAL_DIRECTORY);
          
          String[] dataNodetemp = properties.getProperty(this.DATANODE_HOSTNAME).split(",");
          for(String s: dataNodetemp)
        	  this._nnStubTable.add(s);          
     }
     
     
     public void BindToRegistry(NameNode n) throws RemoteException, MalformedURLException, UnknownHostException {  
    	this._hostname = InetAddress.getLocalHost().getHostName();
     	this._nnAPI = new NameNodeAPI(n);
         Naming.rebind("rmi://"+this._hostname+"/NameNode", this._nnAPI);                                  
         System.out.println("Data Node "+this._hostname+" ready!");
     }
		
	public static void main(String[] arg) throws InterruptedException, IOException {
		
		NameNode nn = new NameNode();
		
		nn.ParseConfiguration();				
		
		nn.BindToRegistry(nn);
		
		Thread HeartBeat = new Thread(new ProcessHeartBeat(nn));
		HeartBeat.start();
		
		
		
	}
}
