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
        if (canMultiply(o)) {
            double[][] res = new double[this.getRowCount()][o.getColumnCount()];
            for (int row = 0; row < res.length; row++) {
                for (int col = 0; col < res[row].length; col++) {
                    System.out.println(row +" "+ col);
                    //res[row][col] = multiplyMatricesCell(this, o, row, col);
                }
            }
            return new Matrix(res);
        } else {
            throw new IllegalArgumentException("MatrixNotMultipliable");
        }
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
        StringBuilder sbResult = new StringBuilder();

        for(int i = 0; i < getRowCount();i++)
        {
            for(int j = 0; j < getColumnCount();j++)
            {
                sbResult.append(getValues()[i][j]);
                sbResult.append("\t");

                if(i == getRowCount() && j == getColumnCount())
                {
                    sbResult.append("\n");
                }
            }
        }

        return sbResult.toString();
    }

}