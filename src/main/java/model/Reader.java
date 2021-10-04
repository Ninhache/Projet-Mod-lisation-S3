package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	
	
	
	private String fileName,authorName;
	private File file;
	private final String END_HEADER = "end_header";
	private int nbVertex,nbFaces,headerLength,vertexLength;
	private List<Vertex> points;
	private List<Face> faces;
	
	public Reader(String fileName, File file){
		this.fileName = fileName;
		this.file = file;
		points = new ArrayList<Vertex>();
		faces = new ArrayList<Face>();
	}
	
	public Reader(String fileName) {
		this(fileName, new File("src/main/resources/"+fileName+".ply"));
	}
	
	public Reader(File file) {
		this("non", file);
	}
	
	public File getFile() {
		return file;
	}
	
	public void readHeader(BufferedReader br) {
		
		try {
			
			StringBuilder sb = new StringBuilder();
			String line;
			
			while((line=br.readLine())!=null && !line.equals(END_HEADER)) {
				
				headerLength++;
				if(line.contains("element face ")) {
					
					nbFaces=Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
					
				} else if (line.contains("element vertex ")) {
					
					nbVertex=Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
					
				} else if (line.contains("comment created by "))authorName = line.substring(line.lastIndexOf(" ")+1);
					sb.append(line);
					sb.append("\n");
				
			}

			System.out.printf("contenu Header :\n\n%s________________________________________________________\n", sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}

	}
	
	public boolean verifyNbVertex(int nbVertexLines) {return nbVertexLines==this.nbVertex;}
	public boolean verifyNbFaces(int nbFacesLines) {return nbFacesLines==this.nbFaces;}
	
	public void collectVertexInfo(String[] line) {points.add(new Vertex(Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2])));}
	public void collectFaceInfo(String[] line) {
		
		int un = Integer.parseInt(line[0]);
		int deux = Integer.parseInt(line[1]);
		int trois = Integer.parseInt(line[2]);
		
		faces.add(new Face(points.get(un), points.get(deux), points.get(trois)));
	}

	public void readVertexLines(BufferedReader br) {
		
		int nbVertexLines = 0;
		
		try {
			
			String line = br.readLine();
			String[] info = line.split(" ");

			StringBuilder sb = new StringBuilder();
			
			boolean testIfVertex = false;
			if(info.length == 3) testIfVertex = true;			
			int i = 1;
			while(testIfVertex) {

				vertexLength++;
				nbVertexLines++;		
				collectVertexInfo(info);
				
				sb.append(line);
				sb.append("\n");
				
				br.mark(vertexLength);
				line=br.readLine();
				info = line.split(" ");
				if( info.length != 3 ) testIfVertex = false;
				
			}
		
			if (nbVertexLines != nbVertex) throw new Exception("element Vertex = " + nbVertex + " && nombre de Lignes de Vertex = " + (i-1) + "");

			System.out.printf("contenu Vertex :\n\n%s________________________________________________________\n", sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void readFaceLines(BufferedReader br) {
		 int nbFaceLines = 0;
		 
		 try {
			 
			 String line = null;
			 String[] info;
			 
			 StringBuilder sb = new StringBuilder();
			 br.reset();
			 
			 boolean testIfFace = true;
			 int i = 1;
			 
			 while((line=br.readLine())!=null && testIfFace) {
				
				info = line.split(" ");		
				collectFaceInfo(info);
				nbFaceLines++;
				i++;
				
				sb.append(line);
				sb.append("\n");
				
				if( info.length != 4 ) testIfFace= false;
				
			 }
			
			 if (nbFaceLines != nbFaces) throw new Exception("element Face = " + nbFaces + " && nombre de Lignes de Face = " + nbFaceLines + ""); 

			 System.out.printf("contenu Faces :\n\n%s\nNombre d'itérations : %d\n________________________________________________________\n\n", sb.toString(), i-1);
		 } catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	public void readPly(BufferedReader br) {
		readHeader(br);
		readVertexLines(br);
		readFaceLines(br);
	}
	
	public String getAuthorName() {
		return "Created by " + authorName;
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		File test = new File("D:\\eclipse-workspace\\projetmodeg5\\src\\main\\resources\\test.ply");
		Reader reader = new Reader(test);
		FileReader fr = new FileReader(reader.getFile());
		BufferedReader br = new BufferedReader(fr);
		
		reader.readPly(br);
		System.out.println(reader.getAuthorName());
	}
}
