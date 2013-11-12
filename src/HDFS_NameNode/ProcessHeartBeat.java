package HDFS_NameNode;

import java.net.ServerSocket;
import java.rmi.Naming;
import java.util.ArrayList;

import HDFS_DataNode.ProcessDataInterface;
import MetaData.DataNodeMeta;

public class ProcessHeartBeat implements Runnable{
	
	private NameNode _nn;
	/**
	 * Get Heart Beat from Data Nodes
	 */
	public ProcessHeartBeat() {}
	public ProcessHeartBeat(NameNode n) {
		this._nn = n;
	}
	@Override
	public void run() {
		while(true) {
			IterateDataNode();
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void IterateDataNode() {
		ArrayList<String> arr = this._nn.GetNameNodeStubTable().GetStubList();
		for(String str : arr) {
			DataNodeMeta dnm = null;
			try {
				String temp = "rmi://"+str+"/ProcessData";
				ProcessDataInterface pdi = (ProcessDataInterface) Naming.lookup(temp);
				dnm = pdi.GetHeartBeat();
				if(this._nn.GetNameNodeWorkerFileMapper().containsNode(dnm.getHostname()))
					this._nn.GetNameNodeWorkerFileMapper().update(dnm);
				else
					HandleDataNodeRecovery(dnm);
					//
			} catch (Exception e) {
				HandleDataNodeFailure(dnm);
			}
		}
	}
	
	public void HandleDataNodeFailure(DataNodeMeta dnm) {
		
	}
	
	public void HandleDataNodeRecovery(DataNodeMeta dnm) {
		
	}
}
