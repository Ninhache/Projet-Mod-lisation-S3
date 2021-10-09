package model;

/**
 * This enumeration help for the define wich rotation to use with matrix calculations
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public enum Rotation {
    X("axeX"), Y("axeY"), Z("axeZ");

    private String s;

    private Rotation(String s) {
        this.s = s;
    }

    public String getRotation() {
        return this.s;
    }
}
