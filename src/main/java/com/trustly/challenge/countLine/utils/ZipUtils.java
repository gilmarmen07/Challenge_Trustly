package com.trustly.challenge.countLine.utils;

import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.trustly.challenge.countLine.model.GithubFile;

public class ZipUtils {
	
	public static List<GithubFile> readZipStream(URL url) throws IOException {
		
		List<GithubFile> list = new ArrayList<GithubFile>();
		
		InputStream inputStream = url.openStream(); 
		
	    ZipInputStream zipIn = new ZipInputStream(inputStream);
	    
	    ZipEntry entry;
	    
	    while ((entry = zipIn.getNextEntry()) != null) {
	    	GithubFile githubFile = new  GithubFile();
	    	
	    	if(entry.getSize() > 0) {
		    	String[] path = entry.getName().split("/");
		    	String[] file = path[path.length - 1].split("[.]");
		    	
		    	githubFile.setDescription(entry.getName());
		    	githubFile.setExtension(file[file.length - 1]);
		    	githubFile.setSize(entry.getSize());
		    	long quantLines = readContents(new FilterInputStream(zipIn) {
		            @Override
		            public void close() throws IOException {
		                zipIn.closeEntry();
		            }
		        });
		    	githubFile.setQuantLines(quantLines);
		    	
		    	list.add(githubFile);
	    	}
	    }
	    return list;
	}
	
	private static long readContents(InputStream is) throws IOException {
		long quantLines = 0;		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));		
		quantLines = reader.lines().count();    
	    return quantLines;
	}

}
