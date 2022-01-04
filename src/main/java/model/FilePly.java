package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FilePly {

    private final File file;
	private final PlyReader plyReader;
    
    public FilePly(File file) {
        PlyReader plyReaderTemp;
        this.file=file;

        try {
            plyReaderTemp = new PlyReader("./exemples/"+this.file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            plyReaderTemp = null;
        }

        this.plyReader = plyReaderTemp;
        new Thread(this::loadData).start();
    }

	public void loadData() {
		try {
			FileReader fr = new FileReader(this.plyReader.getFile());
			BufferedReader br = new BufferedReader(fr);

			this.plyReader.readHeader(br);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		return "" + plyReader.getNbFaces();
    }
    
    public String getVertices() {
		return "" + plyReader.getNbFaces();
    }
	


    @Override
    public String toString() {
        return "FilePly:" + getName();
    }

}
