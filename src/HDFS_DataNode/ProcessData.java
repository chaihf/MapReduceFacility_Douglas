package HDFS_DataNode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
			if (!newfile.exists()){
				newfile.createNewFile();
				FileOutputStream fos = new FileOutputStream(newfile);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bos.write(filecontent);
				this._meta.getFBMeta().getDnbt().add(filename);
			}
			else
				System.out.println("File already exist!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void TransferSplittedFile(String filename, String datanodename){
		File file = new File(filename);
		if (!file.exists())
			System.out.println("No such file!");
		byte[] filecontent = new byte[(int) file.length()];
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(filecontent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ProcessDataInterface pd = (ProcessDataInterface) Naming.lookup("rmi://"+datanodename+"/pd");
			pd.PlaceSplittedFile(filename, filecontent);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DataNodeMeta SendHeartBeat(){
		return this.getMeta();
	}
}
