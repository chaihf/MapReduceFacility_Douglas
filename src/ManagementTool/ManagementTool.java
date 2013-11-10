package ManagementTool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import HDFS_NameNode.*;
import HDFS_DataNode.*;



public class ManagementTool {
	
	private final String PUT = "-put";
	private final String LS = "-ls";
	private String NAMENODE_RMI;
	private int REPLICATION_FACTOR;
	private int BLOCKSIZE;
	private ArrayList<ProcessDataInterface> DATANODE_STUB = new ArrayList<ProcessDataInterface>(); 
	private NameNodeAPIInterface NAMENODE_STUB;
	
	public ManagementTool() throws MalformedURLException, RemoteException, NotBoundException {
		ParseConfiguration();
		Initialization();
	}
			
	
	public void ParseConfiguration() {
		
	}
	

	public void Initialization() throws MalformedURLException, RemoteException, NotBoundException {
		this.NAMENODE_STUB = (NameNodeAPIInterface) Naming.lookup(this.NAMENODE_RMI);
		
	}
	
	
	public void ConsoleHandler() throws IOException, NotBoundException  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input;
		System.out.println("Management Tool starts up:");
		System.out.print("==>");
		while((input = in.readLine())!=null) {
			if(input.split(" ")[0].toLowerCase().equals(PUT)) 
				PutFileIntoHDFS(input);
			else
				if(input.split(" ")[0].toLowerCase().equals(LS))
					ListFile();
			
			//System.out.println(s);
			System.out.print("==>");
		}
	}
	
	
	public void PutFileIntoHDFS(String input) throws IOException, NotBoundException {
		PlaceFileStrategy pfs = new PlaceFileStrategy(this.REPLICATION_FACTOR);
		
		
		//Update to the latest DataNode list.
		this.DATANODE_STUB.clear();
		ArrayList<String> dnrmi = this.NAMENODE_STUB.GetDataNodeRMI();		
		for(int i = 0; i< dnrmi.size(); i++) {
			ProcessDataInterface pdi = (ProcessDataInterface) Naming.lookup(dnrmi.get(i));
			this.DATANODE_STUB.add(pdi);
		}
		
		int dataNodeNum = this.DATANODE_STUB.size();
		
		
		//Split file according to the block size and replicated factor
		File f = new File(input);
		BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f));
        FileOutputStream out;
        String name = f.getName();
        int partCounter = 1;
        int sizeOfFiles = this.BLOCKSIZE;// Size of Each Block
        byte[] buffer = new byte[sizeOfFiles];
        int tmp = 0;
        while ((tmp = bis.read(buffer)) > 0) {        	
        	int ran = (int) (Math.random()*dataNodeNum);
        	this.DATANODE_STUB.get(ran%dataNodeNum).PlaceSplittedFile(name+String.format("%03d", partCounter++), buffer);
        	this.DATANODE_STUB.get((ran+1)%dataNodeNum).PlaceSplittedFile(name+String.format("%03d", partCounter++), buffer);
        }		
	}
	
	public void ListFile() {
		
	}
	
	public void splitFile(File f) throws IOException {
        
    }

	
	public static void main(String[] arg) throws InterruptedException, IOException, NotBoundException {
		if(arg == null || arg.length != 3) {
			System.err.println("Usage: ProcessManagerServer hostname port");
		}
		
		ManagementTool mmt = new ManagementTool();
		
		mmt.ConsoleHandler();

		
		
	}
}
