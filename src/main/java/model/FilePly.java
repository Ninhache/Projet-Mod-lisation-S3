package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FilePly {

    private File file;
    
    public FilePly(File file) {
    	this.file=file;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return file.getName();
    }

    public String getPath() {
        return "./exemples/" + this.getName();
    }
    
    public String getFaces() {
    	PlyReader plyR=null;
    	int res=0;
    	try {
			plyR=new PlyReader("./exemples/"+this.file.getName());
			plyR.readPly();
			res=plyR.getFacesList().size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ""+res;
    }
    
    public String getVertices() {
    	PlyReader plyR=null;
    	int res=0;
    	try {
			plyR=new PlyReader("./exemples/"+this.file.getName());
			plyR.readPly();
			res=plyR.getVerticesList().size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ""+res;
    }
	


    @Override
    public String toString() {
        return "FilePly:" + getName();
    }

}
