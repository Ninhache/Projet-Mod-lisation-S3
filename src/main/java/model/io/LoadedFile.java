package model.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Use that class to load data
 *
 * @author Néo ALMEIDA, Matteo MACIEIRA
 */
public class LoadedFile {

    private File file;
	private PlyReader plyReader;
    
    public LoadedFile(File file) {

        try {

            this.file=file;
            this.plyReader = new PlyReader("./exemples/"+this.file.getName());

            new Thread(this::loadData).start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  This function should be used asynchronous.
     *
     *  The function is reading the header of the loaded file.
     *  We used it for list all files and print all files data
     *
     * @see {@link PlyReader}
     */
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
    
    public String getAuthor() {
    	return plyReader.getAuthorName();
    }
    
    public String getFaces() {
		return "" + plyReader.getNbFaces();
    }
    
    public String getVertices() {
        return String.valueOf(plyReader.getNbVertex());
    }

    public String getDescription() {
        return plyReader.getDescription();
    }
	


    @Override
    public String toString() {
        return "FilePly:" + getName();
    }

}
