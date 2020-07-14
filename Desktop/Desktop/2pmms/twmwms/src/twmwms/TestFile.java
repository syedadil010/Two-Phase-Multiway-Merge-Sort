package twmwms;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFile {

	
	public static void main(String args[]) throws IOException
	{
		 File oriFile;
		
	File file = new File("");
	//File file = new File(FILE_PATH);
	if(!file.exists())
	{
		FileOutputStream fw = new FileOutputStream("testfile.txt");
		BufferedOutputStream bout = new BufferedOutputStream(fw);
		DataOutputStream dout=new DataOutputStream(bout);
		System.out.println("******************************************************************");
		System.out.println("  @@ Now Starting To Create File¡­¡­ @@");
               
        long startCreateFile = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
		{
			int ger = (int) (Math.random() * 10000000 + 1);
			ger = ger < 0 ? -ger : ger;
			String item = "";
			for(int j=0;j<1;j++)
			{
				item += String.valueOf(ger);
			}
			dout.writeBytes(item+"\r\n");
		}
		
		dout.flush();
		bout.flush();
		fw.flush();
		
		dout.close();
		bout.close();
		fw.close();
		long endCreateFile = System.currentTimeMillis();
		System.out.println("  @@ End, Creating File. Costs:" + (endCreateFile - startCreateFile) + "ms @@");
		System.out.println("******************************************************************\n");
		
		oriFile = new File("");
	}
	else
	{
		System.out.println("File Exists...");
		System.out.println("*************************************************");
		oriFile = new File("testfile.txt");
	}
}
}