package com.mx.smarttools.admin.common.beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myFileReader")
public class MyFileReader {

	@Value("D:\\TestMotoDB\\compra.csv")
	private String pathFile;
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public List<String> readFile() throws IOException{
		List<String> renglonList =  null;
//		renglonList= Files.readAllLines(getFile().toPath());
		renglonList= Files.readAllLines((new File(getPathFile())).toPath(),ENCODING);
		return renglonList;
	}
	
	public void writeFile(String renglon) throws IOException {
		String newline = System.getProperty("line.separator");
//		Writer output;
//		output = new BufferedWriter(new FileWriter(getPathFile(),true));
//		output.append(renglon);
//		output.append(newline);
//		output.close();
		
		BufferedWriter output;
		output = Files.newBufferedWriter((new File(getPathFile())).toPath(), 
				ENCODING, StandardOpenOption.APPEND);
		output.append(renglon);
		output.append(newline);
		output.close();
	}
	
	public void overwriteRow(String renglon, String newRow) throws IOException {
		List<String> renglonList =  null;
		File file = new File(getPathFile());
		renglonList= Files.readAllLines(file.toPath());
		int numRow = 0;
		
		Iterator iterRow = renglonList.iterator();
		
		while(iterRow.hasNext()){
			String row = (String)iterRow.next();
			
			if(row.equals(renglon)){
				renglonList.set(numRow, newRow);
				break;
			}
			numRow++;
		}
		
		Files.write(file.toPath(), renglonList, ENCODING);
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
}
