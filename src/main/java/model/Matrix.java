package model;

import java.util.Arrays;

/* FUNCTIONS TODO :
*   mutliplication(Matrice m1, scalaire)
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

    public Matrix subMatrix(Matrix o) {
        if(canSum(o)) {
            double[][] res = new double[this.getRowCount()][this.getColumnCount()];
            for (int row = 0; row <  this.getRowCount(); row++) {
                for (int col = 0; col < this.getColumnCount(); col++) {
                    res[row][col] = this.getValues()[row][col] - o.getValues()[row][col];
                }
            }
            return new Matrix(res);
        }else {
            throw new IllegalArgumentException("MatrixNotSummable");
        }
    }

    public Matrix homothety(double ratio) {
        double vals[][] = new double[][] {
                {ratio, 0, 0, 0},
                {0, ratio, 0, 0},
                {0, 0, ratio, 0},
                {0, 0, 0, 1}
        };
        return this.multiplyMatrix(new Matrix(vals));
    }

    public Matrix translation(double t1, double t2, double t3) {
        double vals[][] = new double[][] {
                {1, 0, 0, t1},
                {0, 1, 0, t2},
                {0, 0, 1, t3},
                {0, 0, 0, 1}
        };
        return this.multiplyMatrix(new Matrix(vals));
    }
    // TODO : Check if this is correct later
    public Matrix translation(Vecteur v) {
        return this.translation(v.getX(), v.getY(), v.getZ());
    }

    // TODO : Test will be hard to make...
    public Matrix rotation(Rotation r, double degre) {
        double vals[][] = new double[this.getRowCount()][this.getColumnCount()];
        if(r.equals(Rotation.X)) {
            vals = new double[][] {
                    {1, 0, 0, 0},
                    {0, Math.cos(Math.toRadians(degre)), -Math.sin(Math.toRadians(degre)), 0},
                    {0, Math.sin(Math.toRadians(degre)), Math.cos(Math.toRadians(degre)), 0},
                    {0, 0, 0, 1},
            };
        } else if(r.equals(Rotation.Y)) {
            vals = new double[][] {
                    {Math.cos(Math.toRadians(degre)), 0, Math.sin(Math.toRadians(degre)), 0},
                    {0, 1, 0, 0},
                    {-Math.sin(Math.toRadians(degre)), 0, Math.cos(Math.toRadians(degre)), 0},
                    {0, 0, 0, 1},
            };
        } else if(r.equals(Rotation.Z)) {
            vals = new double[][] {
                    {Math.cos(Math.toRadians(degre)), -Math.sin(Math.toRadians(degre)), 0, 0},
                    {Math.sin(Math.toRadians(degre)), Math.cos(Math.toRadians(degre)), 0, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 1},
            };
        } else {
            throw new IllegalArgumentException("Le type de rotation n'est pas valable.");
        }


        Matrix m = new Matrix(vals);
        //System.out.println("cos(90) = " + Math.cos(Math.toRadians(90.0)) + " sin(90) = " + Math.sin(Math.toRadians(degre)));
        return this.multiplyMatrix(m);
    }


    public boolean canMultiply(Matrix m) {
        return this.getRowCount() == m.getColumnCount();
    }

    public boolean canSum(Matrix m) {
        return this.getRowCount() == m.getRowCount() && this.getColumnCount() == m.getColumnCount();
    }

    public boolean canSub(Matrix m) {
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