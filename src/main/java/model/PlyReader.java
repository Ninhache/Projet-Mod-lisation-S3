package model;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * A PlyReader is used to read a .ply file and get a model out of it
 *
 * @author Simon LAGNEAU
 * @version %I%, %G%
 */
public class PlyReader {

	private String fileName, authorName, description;
	private File file;
	private final String END_HEADER = "end_header"; // < Simplfied into normal string, idk why this is used...
	private int nbVertex,nbFaces,vertexLength;
	private ArrayList<Vertex> verticesList;
	private ArrayList<Face> facesList;
	private ArrayList<int[]> rgbList;
	private ArrayList<Double> alphaList;
	private int colorParser = 0;
	
	private double barycenterX = 0;
	private double barycenterY = 0;
	private double barycenterZ = 0;

	private boolean isColored = false,
					alpha = false;
	
	/**
	 * <b>Constructor of a PlyReader</b>
	 * 
	 * <p>
	 * Thanks to the ply reader you're able to read a .ply file and get a model out of it
	 * </p>
	 *
	 * @param file the file to read
	 * @throws Exception
	 */
	public PlyReader(File file) throws Exception{
		if(file.exists()) {
			if(file.getName().endsWith(".ply")) {
				this.file = file;
				this.fileName = file.getName();
			} else {
				throw new Exception("Mauvais type de fichier");
			}
		} else {
			this.fileName = "none";
			throw new FileNotFoundException();
		}
		verticesList = new ArrayList<Vertex>();
		facesList = new ArrayList<Face>();
	}

	/**
	 * <b>Constructor of a PlyReader</b>
	 * 
	 * <p>
	 * Constructor using only the name of the file to read: that file must be in the resources folder otherwise it will be null
	 * </p>
	 *
	 * @param fileName the name of the file to read
	 * @throws Exception
	 */
	public PlyReader(String fileName) throws Exception {
		this(new File("src/main/resources/"+fileName+".ply"));
	}
	
	/**
	 * <b>Constructor of a PlyReader</b>
	 * 
	 * <p>
	 * Path-based constructor : the path must lead to a correct ply file in order to work
	 * </p>
	 * 
	 * @param path the path towards the file to read
	 * @throws Exception
	 */
	public PlyReader(Path path) throws Exception {
			this(path.toFile());
	}

	/**
	 *<b>Constructor of a PlyReader</b>
	 * 
	 * <p>
	 * Empty constructor that still initializes the vertices and faces Lists to ArrayLists<br>
	 * Adding a file to the PlyReader is still possible
	 * </p>
	 * 
	 */
	public PlyReader() {
		this.fileName = "none";
		verticesList = new ArrayList<>();
		facesList = new ArrayList<>();
		rgbList = new ArrayList<>();
		alphaList = new ArrayList<>();
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
	 * @return a Model constructed with the vertices and faces list of the Reader
	 * @throws FileNotFoundException
	 */
	public Model readPly() throws FileNotFoundException {
		
		FileReader fr = new FileReader(this.getFile());
		BufferedReader br = new BufferedReader(fr);
		
		readHeader(br);
		readVertexLines(br);
		readFaceLines(br);

		Model model = new Model(verticesList, facesList);
		model.setNameOfFile(this.getFileName());
		model.setAuthor(this.authorName);
		model.setDescription(this.description);

		model.setBarycenterX(this.barycenterX / this.verticesList.size());
		model.setBarycenterY(this.barycenterY / this.verticesList.size());
		model.setBarycenterZ(this.barycenterZ / this.verticesList.size());

		return model;
	}
	
    /**
     * Reading of the header of a .ply file
     * <p>
     * The function reads only the header of a .ply file and puts the important informations into
     * the vertices and faces lists
     * </p>
     * 
     * @param br BufferedReader to be able to parse and read the file
     */
	private void readHeader(BufferedReader br) {
		
		try {
			
			StringBuilder sb = new StringBuilder();
			StringBuilder sbComments = new StringBuilder();
			String line;

			while((line=br.readLine())!=null && !line.equals(END_HEADER)) {
				
				if(line.contains("element face "))
					nbFaces= Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
				else if (line.contains("element vertex "))
					nbVertex = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
				else if (line.contains("comment created by "))
					authorName = line.substring(line.lastIndexOf(" ")+1);
				else if(line.contains("comment")) {
					sbComments.append(line.substring(7) + "\n");
				}else if(!isColored) {
					
					if(line.contains("property uchar red"))
						isColored = true;
					else if(line.contains("property uchar green"))
						isColored = true;
					else if(line.contains("property uchar blue"))
						isColored = true;

				} else if(line.contains("property uchar alpha"))
						alpha = true;
				
				sb.append(line);
				sb.append("\n");
			}
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
	 * @param nbFacesLines The number of lines describing a Face in the .ply file
	 * @return True if the two are equals
	 */
	public boolean verifyNbFaces(int nbFacesLines) {
		return nbFacesLines == this.nbFaces;
	}
	
	/**
	 * Collects the information to create a Vertex
	 * <p>
	 * The line currently being read when this function is called describes a Vertex.<br>
	 * The function collects the coordinates in the line to create a Vertex and put it into the verticesList.<br>
	 * It's also build the barycenter of the model. The barycenter can be used to center the figure in the middle of the canvas.
	 * </p>
	 * 
	 * @param line The line describing a Vertex currently being read
	 */
	public void collectVertexInfo(String[] line) {
		double x = Double.parseDouble(line[0]);
		double y = Double.parseDouble(line[1]);
		double z = Double.parseDouble(line[2]);

		if(isColored) {
			int r = Integer.parseInt(line[3]);
			int g = Integer.parseInt(line[4]);
			int b = Integer.parseInt(line[5]);
			if(alpha) {
				alphaList.add(Double.parseDouble(line[6]));
			}else {
				rgbList.add(new int[] {r,g,b});
			}
		}
		
		this.verticesList.add(new Vertex(x,y,z));
		this.barycenterX += x;
		this.barycenterY += y;
		this.barycenterZ += z;
	}

	/**
	 * Collects the information to create a Face
	 * <p>
	 * The line currently being read when this function is called describes a Face.<br>
	 * The function collects the coordinates in the line to create a Face and put it into the faceList.<br>
	 * </p>
	 * 
	 * @param line The line describing a Face currently being read
	 */
	public void collectFaceInfo(String[] line) {
		ArrayList<Vertex> pointsOfFace = new ArrayList<>();

		int r = 0,
			g = 0,
			b = 0,
			a = 0;
		for(int i = 1 ; i <= Integer.parseInt(line[0]) ; i++) {
			int currenVertexIdx = Integer.parseInt(line[i]);
			pointsOfFace.add(verticesList.get(currenVertexIdx));
			
			if(isColored) {
				r += rgbList.get(currenVertexIdx)[0];
				g += rgbList.get(currenVertexIdx)[1];
				b += rgbList.get(currenVertexIdx)[2];
				if(alpha)
					a += alphaList.get(currenVertexIdx);
			}
		}
		if(isColored) {
			if(alpha)
				facesList.add(new Face(pointsOfFace, new int[]{r/3, g/3, b/3}, a/3));
			else 
				facesList.add(new Face(pointsOfFace, new int[]{r/3, g/3, b/3}));
		} else 
			facesList.add(new Face(pointsOfFace)); 

		colorParser++;
	}
	
    /**
     * Reading of the Vertex part of a .ply file
     * <p>
     * The function reads only the part describing Vertices of a .ply file.
     * It adds the Vertices into the verticesList using the collectVertexInfo function
     * </p>
     * 
     * @param br BufferedReader to be able to parse and read the file
     */
	private void readVertexLines(BufferedReader br) {
		
		int nbVertexLines = 0;

		try {
			String line = br.readLine();
			String[] splittedLine = line.split(" ");
			
			if(line.length()<=1)
				line = br.readLine();

			splittedLine = line.split(" ");

			StringBuilder sb = new StringBuilder();

			int nbColorInfo = 0;
			if(isColored)
				nbColorInfo+=3;
			if(alpha)
				nbColorInfo++;
			
			
			boolean testIfVertex = splittedLine.length == 3+nbColorInfo;

			int i = 1;
			while(testIfVertex) {

				vertexLength++;
				nbVertexLines++;		
				collectVertexInfo(splittedLine);
				
				sb.append(line);
				sb.append("\n");
				
				br.mark(vertexLength);
				line = br.readLine();
				splittedLine = line.split(" ");

				if(splittedLine.length != 3+nbColorInfo)
					testIfVertex = false;
			}
		
			if (nbVertexLines != nbVertex)
				throw new Exception("element Vertex = " + nbVertex + " && nombre de Lignes de Vertex = " + (i-1) + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Reading of the Face part of a .ply file
     * <p>
     * The function reads only the part describing Faces of a .ply file.
     * It adds the Faces into the facesList using the collectFaceInfo function
     * </p>
     * 
     * @param br BufferedReader to be able to parse and read the file
     */
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
				
				
				
				if(splittedLine.length != 4) testIfFace= false;
				
			 }
			
			 if (nbFaceLines != nbFaces) throw new Exception("element Face = " + nbFaces + " && nombre de Lignes de Face = " + nbFaceLines + "");
		 } catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	/**
	 * Sets a new file into the Reader
	 * 
	 * <p>
	 * The Reader will now read another file when the readPly is called<br>
	 * The function also empties all the information container attributes of the Reader<br>
	 * <br>
	 * It will work only if the given file exists,<br>
	 * otherwise the old file will be kept into the PlyReader
	 * </p>
	 * 
	 * 
	 * @param file New file to read
	 * @throws FileNotFoundException 
	 */
	public void setFile(File file) throws Exception {
		if(file.exists()) {
			if(file.getName().endsWith(".ply")) {
				this.nbFaces = -1;
				this.nbVertex = -1;
				this.vertexLength = -1;
				this.authorName = "";
				this.facesList = new ArrayList<>();
				this.verticesList = new ArrayList<>();
				this.fileName = file.getName();
				this.file = file;
			} else
				throw new Exception("Format incorrect");
		} else
			throw new FileNotFoundException("File not found");
	}
	
	/**
	 * Sets a new file into the Reader
	 * 
	 * <p>
	 * The Reader will now read another file when the readPly is called<br>
	 * The function also empties all the information containers attributes of the Reader<br>
	 * <br>
	 * It will work only if a file with the given name exists in the resources directory of the project,<br>
	 * otherwise the old file will be kept into the PlyReader
	 * </p>	
	 *  
	 * @param fileName name of the new file to read
	 * @throws FileNotFoundException 
	 */
	public void setFile(String fileName) throws FileNotFoundException {
		File file = new File("./exemples/"+fileName+".ply");
		if(file.exists()) {
			this.nbFaces = -1;
			this.nbVertex = -1;
			this.vertexLength = -1;
			this.authorName = "";
			this.facesList = new ArrayList<>();
			this.verticesList = new ArrayList<>();
			this.fileName = fileName;
			this.file = new File("./exemples/"+fileName+".ply");
		} else
			throw new FileNotFoundException("There's no file existing in the resources directory with the given name");
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

	public String getFileName() {
		return fileName;
	}
}