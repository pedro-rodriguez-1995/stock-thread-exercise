package com.sinensia.utils;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class CsvDownload {

	public static void download(String link, String filename) throws FileNotFoundException, IOException {
		
		try(BufferedInputStream in = new BufferedInputStream(new URL(link).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(filename)){
			
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while((bytesRead = in.read(dataBuffer,0,1024))!= -1) {
				
				fileOutputStream.write(dataBuffer,0,bytesRead);
				
				
			}
			
			
		}
		
		
	}
}
