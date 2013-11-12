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
        private int _capacity;
        private String _dfsDirectory;
        private String _localDirectory;
        private ProcessData _pd;
        private String _hostname;
        
        
        private final String DFS_DIRECTORY = "DFS_DIRECTORY";
        private final String DATANODE_CAPACITY = "DATANODE_CAPACITY";
        private final String LOCAL_DIRECTORY = "LOCAL_DIRECTORY";
        
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
        
        public void ParseConfiguration() {        	
        	 Properties properties = new Properties();
             try {
                     properties.load(DataNode.class.getResourceAsStream("../config.properties"));
             } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
             }
             this._capacity = Integer.valueOf(properties.getProperty(this.DATANODE_CAPACITY));
             this._dfsDirectory = properties.getProperty(this.DFS_DIRECTORY);
             this._localDirectory = properties.getProperty(this.LOCAL_DIRECTORY);
        }
        
        public void InitialBlockTable() throws UnknownHostException, RemoteException {
        	 this._hostname = InetAddress.getLocalHost().getHostName();        	         	 
        	 this._datanodemeta = new DataNodeMeta(this._hostname);                           	 
        }
        
        public void BindToRegistry() throws RemoteException, MalformedURLException {  
        	this._pd = new ProcessData(this._datanodemeta);
            Naming.rebind("rmi://"+this._hostname+"/ProcessData", this._pd);                                  
            System.out.println("Data Node "+this._hostname+" ready!");
        }
        
        public static void main(String args[]) throws UnknownHostException, RemoteException, MalformedURLException{
                               
                DataNode dn = new DataNode();
                
                //Parse config
                dn.ParseConfiguration();
                                                
                // initialization 
                dn.InitialBlockTable();
                
                //Bind to rmi registry
                dn.BindToRegistry();               
        }
        
}