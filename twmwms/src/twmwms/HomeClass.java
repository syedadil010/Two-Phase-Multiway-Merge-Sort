package twmwms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class HomeClass {
	
	public static int getItemCount() throws NumberFormatException, IOException
	{ 
		
		FileInputStream fr = new FileInputStream("testfile.txt");
 		BufferedInputStream bin = new BufferedInputStream(fr);
 		DataInputStream din=new DataInputStream(bin);
 		return Integer.parseInt(din.readLine());
		
	}
	public static int Phase1(int ITEM_COUNT) throws IOException
	{
		System.out.println(Runtime.getRuntime().freeMemory());
		System.out.println(" in split 2");
		FileReader fir=new FileReader("testfile.txt");
		BufferedReader bir=new BufferedReader(fir);
//			FileInputStream fis = new FileInputStream("testfile.txt");
//	 		BufferedInputStream bis = new BufferedInputStream(fis);
//	 		DataInputStream dis=new DataInputStream(bis);
			int ibs =(int) (Runtime.getRuntime().freeMemory()/Integer.SIZE);
			ibs++;
			ibs |=ibs >>1;
			System.out.println(ibs);
			ibs |=ibs >>2;
			System.out.println(ibs);
			ibs |=ibs >>4;
			System.out.println(ibs);
			ibs |=ibs >>8;
			System.out.println(ibs);

			ibs |=ibs >>16;
			System.out.println(ibs);

			ibs++;
			ibs=ibs/2;
			ibs=ibs+107900;
			System.out.println(ibs);
			int buffer[]=new int[ibs];
			int fileCount=0;
			System.out.println("sorted");
			int tmpItemCount=0;
			System.out.println(Integer.parseInt(bir.readLine()));
			System.out.println(bir.readLine());
			for(int k=1;k<=ITEM_COUNT;k++)
	 		{
				
	 			buffer[tmpItemCount++] = Integer.parseInt(bir.readLine());
	 			
	 			if(k%ibs==0)
	 			{
	 				fileCount++;
	 				Arrays.sort(buffer);
	 				tmpItemCount = 0;
	 				FileWriter fw=new FileWriter("C:\\Users\\syeda\\Documents\\New folder\\twmwms\\tempfiles\\tmp_"+ fileCount + ".txt");
	 				PrintWriter pw=new PrintWriter(fw);
	 				//	 				File tmpFile = new File("C:\\Users\\syeda\\Documents\\New folder\\twmwms\\tempfiles\\tmp_" + fileCount + ".txt");
//	 				FileOutputStream fw = new FileOutputStream(tmpFile);
//	 				BufferedOutputStream bout = new BufferedOutputStream(fw);
//	 				DataOutputStream dout=new DataOutputStream(bout);
//	 				
	 				for(int j=0;j<ibs;j++)
	 				{
	 					String item_tmp = String.valueOf(buffer[j]);
	 					pw.println(item_tmp);
	 				}
	 				
	 				pw.close();
	 				fw.close();
	 			}
	 		}
			bir.close();
			fir.close();
			
			return fileCount;
	}
	
	static boolean Phase2(int count,int tmpFileCount)
	{
		System.out.println(Runtime.getRuntime().freeMemory());
		BufferedReader[] brs=new BufferedReader[tmpFileCount];
		
		
		
//		File[] fileList = new File[tmpFileCount];
//		
//		FileInputStream[] fr = new FileInputStream[tmpFileCount];
// 		BufferedInputStream[] bin = new BufferedInputStream[tmpFileCount];
// 		DataInputStream[] din = new DataInputStream[tmpFileCount];
 		int[] items =  new int[tmpFileCount];
		for(int i=0;i<tmpFileCount;i++)
		{
			//System.out.println("  in for");
			try{
				brs[i] = new BufferedReader(new FileReader("C:\\Users\\syeda\\Documents\\New folder\\twmwms\\tempfiles\\tmp_" + String.valueOf(i+1) + ".txt"));
//				if(!fileList[i].exists())
//					return false;
//				fr[i] = new FileInputStream(fileList[i]);
//		 		bin[i] = new BufferedInputStream(fr[i],4096);
//		 		din[i] =new DataInputStream(fr[i]);
		 		items[i] = Integer.parseInt(brs[i].readLine());
				
			}catch(Exception e){
				return false;
			}
		}
		
		try{
			
			FileWriter fw=new FileWriter("C:\\Users\\syeda\\Documents\\New folder\\twmwms\\testfile(sorted).txt");
			PrintWriter pw=new PrintWriter(fw);
			//System.out.println("  before merging");
//			FileOutputStream fw = new FileOutputStream("C:\\Users\\syeda\\Documents\\New folder\\twmwms\\testfile(sorted).txt");
//			BufferedOutputStream bout = new BufferedOutputStream(fw);
//			DataOutputStream dout=new DataOutputStream(bout);
			
			System.out.println("  @@ Now Starting To Merge File¡­¡­ @@");
			long startCreateFile = System.currentTimeMillis();
			for(int k=0;k<count;k++)
			{
				//System.out.println("sorting1");
				int minItem = items[0];
				int minItemIndex = 0;
				for(int j=1;j<tmpFileCount;j++)
				{
					if(items[j]<minItem)
					{
						minItem = items[j];
						minItemIndex = j;
					}
				}
				
				String item_tmp = String.valueOf(items[minItemIndex]);
				pw.println(item_tmp);
				//System.out.println(item_tmp);
				
				String nextItem = brs[minItemIndex].readLine();
				if(nextItem!=null)
					items[minItemIndex] = Integer.parseInt(nextItem);
				else
					items[minItemIndex] = Integer.MAX_VALUE;
			}
			pw.close();
			fw.close();
			
			long endCreateFile = System.currentTimeMillis();
			System.out.println("  @@ End, Merging File. Costs:" + (endCreateFile - startCreateFile) + "ms @@");
			System.out.println("*************************************************");
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		int count = getItemCount();
		System.out.println(count + "item count");
		int fc=Phase1(count);
		System.out.println(fc+"file count");
		boolean p2=Phase2(count,fc);
		System.out.println(p2+"");
		
		
	}

}
