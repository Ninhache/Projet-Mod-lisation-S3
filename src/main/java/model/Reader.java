package model;

import java.io.*;

public class Reader {
	
	private String fileName;
	private File file;
	private final String END_HEADER = "end_header";
	private int nbPoints;
	private int nbFaces;
	
	public Reader(String fileName, File file){
		this.fileName = fileName;
		this.file = file;
	}
	
	public Reader(String fileName) {
		this(fileName, new File("src/main/resources/"+fileName+".ply"));
	}
	
	public void read() {
		try {
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line;
			
			while((line=br.readLine())!=null && !line.equals(END_HEADER)) {
				
				if(line.contains("element face ")) {
					
					nbFaces=Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
					
				} else if (line.contains("element vertex ")) {
					
					nbPoints=Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
					
				}
					sb.append(line);
					sb.append("\n");
				
			}
			
			fr.close();
			System.out.printf("contenu :\n%sFINI WOLA", sb.toString());
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
		
	}
	
	public static void main(String args[]) {
		Reader reader = new Reader("test");
		reader.read();
	}
}
