package base;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;



public class TextNote extends Note {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	public TextNote(String title) {
		super(title);
	}
	public TextNote(String title, String content) {
        super(title);
        this.content = content;
    }
	public TextNote(File f) {
		super(f.getName());
		this.content=getTextFromFile(f.getAbsolutePath());
	}
	private String getTextFromFile(String absolutePath) {
		String result = "";
		String line = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(absolutePath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
			isr.close();
			fis.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
    		e.printStackTrace();
    	}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		if(pathFolder ==""){
			pathFolder = ".";
		}
		
		String fileTitle = this.getTitle().replaceAll(" ", "_");
		File file = new File(pathFolder+File.separator+fileTitle+".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if(file.exists()==false) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(this.content);
			bw.close();
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public String getContent() {
		return content;
	}

}
