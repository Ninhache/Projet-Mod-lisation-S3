package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: ADD DESC
 *
 * @author Simon LAGNEAU
 * @version %I%, %G%
 */
public class Reader {

	private String fileName,authorName;
	private File file;
	private final String END_HEADER = "end_header";
	private int nbVertex,nbFaces,vertexLength;
	private ArrayList<Vertex> points;
	private ArrayList<Face> faces;

	/**
	 * <b>Constructor of a Reader</b>
	 *
	 * Thanks to the reader you're able to read a file.ply and get a model
	 *
	 * @param file file to read
	 */
	public Reader(File file){
		this.file = file;
		this.fileName = file.getName();
		points = new ArrayList<Vertex>();
		faces = new ArrayList<Face>();
	}

	public Reader(String fileName) {
		this(new File("src/main/resources/"+fileName+".ply"));
	}

	public Reader() {
		points = new ArrayList<Vertex>();
		faces = new ArrayList<Face>();
	}
    
    
	public void readHeader(BufferedReader br) {
		
		try {
			
			StringBuilder sb = new StringBuilder();
			String line;
			
			while((line=br.readLine())!=null && !line.equals(END_HEADER)) {
				
				if(line.contains("element face ")) {
					nbFaces= Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
				
				} else if (line.contains("element vertex ")) {
					nbVertex = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
					
				} else if (line.contains("comment created by "))
					authorName = line.substring(line.lastIndexOf(" ")+1);

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
	
	public boolean verifyNbVertex(int nbVertexLines) {
		return nbVertexLines==this.nbVertex;
	}

	public boolean verifyNbFaces(int nbFacesLines) {
		return nbFacesLines==this.nbFaces;
	}
	
	public void collectVertexInfo(String[] line) {
		points.add(new Vertex(Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2])));
	}
	
	public void collectFaceInfo(String[] line) {
		
		ArrayList<Vertex> pointsOfFace = new ArrayList<>();
		for(int i = 1 ; i <= Integer.parseInt(line[0]) ; i++) {
			pointsOfFace.add(points.get(Integer.parseInt(line[i])));
		}
		
		faces.add(new Face(pointsOfFace)); 
	}

	public void readVertexLines(BufferedReader br) {
		
		int nbVertexLines = 0;
		
		try {
			
			String line = br.readLine();
			String[] splittedLine = line.split(" ");

			StringBuilder sb = new StringBuilder();
			
			boolean testIfVertex = false;
			if(splittedLine.length == 3) testIfVertex = true;			
			int i = 1;
			while(testIfVertex) {

				vertexLength++;
				nbVertexLines++;		
				collectVertexInfo(splittedLine);
				
				sb.append(line);
				sb.append("\n");
				
				br.mark(vertexLength);
				line=br.readLine();
				splittedLine = line.split(" ");
				if( splittedLine.length != 3 )
					testIfVertex = false;
			}
		
			if (nbVertexLines != nbVertex)
				throw new Exception("element Vertex = " + nbVertex + " && nombre de Lignes de Vertex = " + (i-1) + "");

//			System.out.printf("contenu Vertex :\n\n%s________________________________________________________\n", sb.toString());
		
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
			 String[] splittedLine;
			 
			 StringBuilder sb = new StringBuilder();
			 br.reset();
			 
			 boolean testIfFace = true;
			 int i = 1;
			 
			 while((line=br.readLine())!=null && testIfFace) {
				
				splittedLine = line.split(" ");		
				collectFaceInfo(splittedLine);
				nbFaceLines++;
				i++;
				
				sb.append(line);
				sb.append("\n");
				
				if( splittedLine.length != 4 ) testIfFace= false;
				
			 }
			
			 if (nbFaceLines != nbFaces) throw new Exception("element Face = " + nbFaces + " && nombre de Lignes de Face = " + nbFaceLines + ""); 

//			 System.out.printf("contenu Faces :\n\n%s\nNombre d'itérations : %d\n________________________________________________________\n\n", sb.toString(), i-1);
		 } catch(Exception e){
			 e.printStackTrace();
		 
		 }
	}
	
	public Model readPly() throws FileNotFoundException {
		
		Reader reader = new Reader(this.file);
		FileReader fr = new FileReader(reader.getFile());
		BufferedReader br = new BufferedReader(fr);
		
		readHeader(br);
		readVertexLines(br);
		readFaceLines(br);
		
		return new Model(points, faces);
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.nbFaces = -1;
		this.nbVertex = -1;
		this.vertexLength = -1;
		this.authorName = "";
		this.faces = new ArrayList<>();
		this.points = new ArrayList<>();
		this.fileName = file.getName();
		this.file = file;
	}
	public void setFile(String fileName) {
		this.nbFaces = -1;
		this.nbVertex = -1;
		this.vertexLength = -1;
		this.authorName = "";
		this.faces = new ArrayList<>();
		this.points = new ArrayList<>();
		this.fileName = fileName;
		this.file = new File(fileName);
	}
	public String getAuthorName() {
		return "Created by " + authorName;
	}
	
//	public static void main(String args[]) throws FileNotFoundException{
//		File test = new File("D:\\eclipse-workspace\\projetmodeg5\\src\\main\\resources\\test.ply");
//		Reader reader = new Reader(test);
//		reader.readPly();
//		System.out.println(reader.getAuthorName());
//		reader.setFile("D:\\eclipse-workspace\\projetmodeg5\\src\\main\\resources\\test3.ply");
//		reader.readPly();
//		System.out.println(reader.getAuthorName());
//		reader.setFile("D:\\eclipse-workspace\\projetmodeg5\\src\\main\\resources\\test3.ply");
//		reader.readPly();
//	}
}
