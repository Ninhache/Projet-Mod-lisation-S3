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
	 * Thanks to the reader you're able to read a .ply file and get a model out of it
	 *
	 * @param file File type argument is the file to read
	 */
	public Reader(File file){
		this.file = file;
		this.fileName = file.getName();
		points = new ArrayList<Vertex>();
		faces = new ArrayList<Face>();
	}

	/**
	 * <b>Constructor of a Reader</b>
	 *
	 * @param name of the file to read
	 */
	public Reader(String fileName) {
		this(new File("src/main/resources/"+fileName+".ply"));
	}
	
	/**
	 *<b>Constructor of a Reader</b>
	 * 
	 * <p>
	 * Empty constructor that still initializes the points and faces Lists to ArrayLists<br>
	 * Adding a file to the Reader is still possible
	 * </p>
	 */
	public Reader() {
		points = new ArrayList<Vertex>();
		faces = new ArrayList<Face>();
	}
    
	
	/**
	 * Reads a .ply file
	 * <p>
	 * The function creates a BufferedReader to be able to parse and read the file<br>
	 * <br>
	 * Separated into three functions that read respectively :<br>
	 * 		- the header<br>
	 * 		- the lines describing vertices<br>
	 * 		- the lines describing faces<br>
	 * 	of the file<br>
	 * <br>
	 * The same BufferedReader is kept for the three functions
	 * </p>
	 * 
	 * @return a Model constructed with the points and faces list of the Reader
	 * @throws FileNotFoundException
	 */
	public Model readPly() throws FileNotFoundException {
		
		Reader reader = new Reader(this.file);
		FileReader fr = new FileReader(reader.getFile());
		BufferedReader br = new BufferedReader(fr);
		
		readHeader(br);
		readVertexLines(br);
		readFaceLines(br);
		
		return new Model(points, faces);
	}
	
    /**
     * Reading of the header of a .ply file
     * <p>
     * The function reads only the header of a .ply file and puts the important informations into
     * the points and faces lists
     * </p>
     * 
     * @param br BufferedReader to be able to parse and read the file
     */
	private void readHeader(BufferedReader br) {
		
		try {
			
			StringBuilder sb = new StringBuilder();
			String line;

			while((line=br.readLine())!=null && !line.equals(END_HEADER)) {
				
				if(line.contains("element face "))
					nbFaces= Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
				else if (line.contains("element vertex "))
					nbVertex = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
				else if (line.contains("comment created by "))
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
	
	/**
	 * Checks if the number of lines describing a Vertex is the same as the number of Vertices declared in the header of the .ply file
	 * 
	 * @param nbVertexLines The number of lines describing a Vertex in the .ply file
	 * @return True if the two are equals
	 */
	public boolean verifyNbVertex(int nbVertexLines) {
		return nbVertexLines==this.nbVertex;
	}

	/**
	 * Checks if the number of lines describing a Face is the same as the number of Faces declared in the header of the .ply file
	 * 
	 * @param nbVertexLines The number of lines describing a Face in the .ply file
	 * @return True if the two are equals
	 */
	public boolean verifyNbFaces(int nbFacesLines) {
		return nbFacesLines==this.nbFaces;
	}
	
	/**
	 * Collects the information to create a Vertex
	 * <p>
	 * The line currently being read when this function is called describes a Vertex.<br>
	 * The function collects the coordinates in the line to create a Vertex and put it into the Vertex List, points.<br>
	 * </p>
	 * 
	 * @param line The line describing a Vertex currently being read
	 */
	public void collectVertexInfo(String[] line) {
		points.add(new Vertex(Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2])));
	}

	/**
	 * Collects the information to create a Face
	 * <p>
	 * The line currently being read when this function is called describes a Face.<br>
	 * The function collects the coordinates in the line to create a Face and put it into the Face List, faces.<br>
	 * </p>
	 * 
	 * @param line The line describing a Face currently being read
	 */
	public void collectFaceInfo(String[] line) {
		ArrayList<Vertex> pointsOfFace = new ArrayList<>();

		for(int i = 1 ; i <= Integer.parseInt(line[0]) ; i++) {
			pointsOfFace.add(points.get(Integer.parseInt(line[i])));
		}
		
		faces.add(new Face(pointsOfFace)); 
	}

    /**
     * Reading of the Vertex part of a .ply file
     * <p>
     * The function reads only the part describing Vertices of a .ply file.
     * It adds the Vertices into the Vertex List points using the collectVertexInfo function
     * </p>
     * 
     * @param br BufferedReader to be able to parse and read the file
     */
	private void readVertexLines(BufferedReader br) {
		
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
	
	private void readFaceLines(BufferedReader br) {
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
	
	/**
	 * Sets a new file into the Reader
	 * 
	 * <p>
	 * The Reader will now read another file when the readPly is called
	 * 
	 * The function also empties all the information containers attributes of the Reader
	 * </p>
	 * 
	 * 
	 * @param newFile New file to read
	 */
	public void setFile(File newFile) {
		this.nbFaces = -1;
		this.nbVertex = -1;
		this.vertexLength = -1;
		this.authorName = "";
		this.faces = new ArrayList<>();
		this.points = new ArrayList<>();
		this.fileName = newFile.getName();
		this.file = newFile;
	}
	
	/**
	 * Sets a new file into the Reader
	 * 
	 * <p>
	 * The Reader will now read another file when the readPly is called
	 * 
	 * The function also empties all the information containers attributes of the Reader
	 * </p>	
	 *  
	 * @param fileName name of the new file to read
	 */
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
	
	/**
	 * @return the name of the author if mentioned in the .ply file
	 */
	public String getAuthorName() {
		return "Created by " + authorName;
	}

	/**
	 * @return the file that is currently in the Reader
	 */
	public File getFile() {
		return file;
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
