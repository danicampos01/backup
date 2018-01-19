/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.contoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class ReadFiles  {
 private static File file = new File("C:/var/log.txt");
	private static final void read(File file) throws IOException{
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String data = null;
		while((data = reader.readLine()) != null){
			System.out.println(data);
		}
		fileReader.close();
		reader.close();
	}
	public static void main(String[] args) {
		try{
			ReadFiles.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
