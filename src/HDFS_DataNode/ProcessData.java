package HDFS_DataNode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import MetaData.DataNodeMeta;

@SuppressWarnings("serial")
public class ProcessData extends UnicastRemoteObject implements ProcessDataInterface {
	private DataNodeMeta _meta;
	public ProcessData(DataNodeMeta meta) throws RemoteException{
		this._meta = meta;
	}
	
	public DataNodeMeta getMeta(){
		return this._meta;
	}
	
	@SuppressWarnings("resource")
	public void PlaceSplittedFile(String filename, byte[] filecontent){
		File newfile = new File(filename);
		try {
			if (!newfile.exists())
				newfile.createNewFile();
			FileOutputStream fos = new FileOutputStream(newfile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(filecontent);
			this._meta.getblockmeta.add(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public byte[] GetSplittedFile(String filename){
		File file = new File(filename);
		if (!file.exists())
			return null;
		byte[] filecontent = new byte[(int) file.length()];
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(filecontent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filecontent;
	}
}
