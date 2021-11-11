package model;

import java.io.File;

public class FilePly {

    private File file;

    public FilePly(File file) {
        this.file = file;
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

    @Override
    public String toString() {
        return "FilePly:" + getName();
    }

}
