package HDFS_NameNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NameNode {
	public NameNode() {}
	
	public void ConsoleHandler() throws IOException  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input;
		System.out.print("==>");
		while((input = in.readLine())!=null) {
			
			
			//System.out.println(s);
			System.out.print("==>");
		}
	}
	
	public static void main(String[] arg) throws InterruptedException, IOException {
		if(arg == null || arg.length != 3) {
			System.err.println("Usage: ProcessManagerServer hostname port");
		}
		
		NameNode nn = new NameNode();
		
		nn.ConsoleHandler();

		
		
	}
}
