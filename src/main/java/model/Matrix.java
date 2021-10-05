package model;

import java.util.Arrays;

/* FUNCTIONS TODO :
*   addition(Matrix m);
*   mutliplication(Matrice m1, Matrice m2)
*   mutliplication(Matrice m1, scalaire)
*   homothetie(rapport)
*   translation(vecteur???)
*   rotation(x/y/z ; degree) >>> faire une enum pour la rotation (voir mon git)
* */

public class Matrix {

    private double[][] values;

    public Matrix(double[][] values) {
        this.values = values;
    }

    public Matrix multiplyMatrix(Matrix o) {
        // TODO : Create errors to check validity of both matrix
        if(!this.canMultiply(o)) {
            return this;
        }


        double vals[][];
        int l1 = this.getRowCount();
        int c1 = this.getColumnCount();

        int l2 = o.getRowCount();
        int c2 = o.getColumnCount();

        vals = new double[l1][c2];

        for(int row = 0; row < l1; row++){
            for(int col = 0; col < c2; col++){
                for(int k = 0; k < c1; k++)
                    vals[row][col] += this.getValues()[row][k] * o.getValues()[k][col];
            }
        }
        return new Matrix(vals);
    }

    public Matrix sumMatrix(Matrix o) {
        if(canSum(o)) {
            double[][] res = new double[this.getRowCount()][this.getColumnCount()];
            for (int row = 0; row <  this.getRowCount(); row++) {
                for (int col = 0; col < this.getColumnCount(); col++) {
                    res[row][col] = this.getValues()[row][col] + o.getValues()[row][col];
                }
            }
            return new Matrix(res);
        }else {
            throw new IllegalArgumentException("MatrixNotSummable");
        }

    }

    /* ????? pq c'est la */
    public double multiplyMatricesCell(Matrix matrix, Matrix o, int row, int col) {
        double cell = 0;
        for (int i = 0; i < values.length; i++) {
            cell += matrix.getValues()[row][i] * o.getValues()[i][col];
        }
        return cell;
    }

    public boolean canMultiply(Matrix m) {
        return this.getRowCount() == m.getColumnCount();
    }

    public boolean canSum(Matrix m) {
        return this.getRowCount() == m.getRowCount() && this.getColumnCount() == m.getColumnCount();
    }

    public int getRowCount() {
        return values.length;
    }

    public int getColumnCount() {
        return values[1].length;
    }

    public double[][] getValues() {
        return values;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        String res = "";

        for(int i = 0 ; i < this.getRowCount() ; i ++ ) {
            for(int j = 0 ; j < this.getColumnCount() ; j ++ ) {
                res+="["+this.getValues()[i][j]+"]";
            }
            res+="\n";
        }

        return res;
    }

}