package model;

/* FUNCTIONS TODO :
*   mutliplication(Matrice m1, scalaire)
*   translation(vecteur???)
*   rotation(x/y/z ; degree) >>> faire une enum pour la rotation (voir mon git)
* */

/**
 * This class handle all matrix calculations we need for the project
 *
 * @author Néo ALMEIDA - Paul VANHEE
 * @version %I%, %G%
 */
public class Matrix {

    private double[][] values;
    private final double[][] defaultValues;

    /**
     * <b>Constructor of a Matrix</b>
     *
     * A Matrix is a multidimensional array
     *
     * @param values list of values of the Matrix
     */
    public Matrix(double[][] values) {

    	this.defaultValues = values.clone();

        this.values = values;
    }
    
    public Matrix(double[][] values, Rotation rotation, int degree) {
    	this.values=values;
    	this.rotation(rotation, degree);
    	this.defaultValues = this.values.clone();
    }
   
    /**
     * Multiplies matrix by another one
     * <p>
     * The function is executed only if both matrices can be multiplied
     *
     * <b><u>For example :</u></b>
     * If you try to multiply these 2 matrices, you will get a new one :
     * { 7 , 3 , 2 }         { 1 , 1 , 3 }              { 19 , 26 , 35 }
     * { 5 , 9 , 2 }    x    { 2 , 5 , 2 }      =       { 29 , 54 , 41 }
     * { 0 , 0 , 1 }         { 3 , 2 , 4 }              {  3 ,  2 ,  4 }
     *
     * If you try to multiply these 2 matrices, you will get an error, and get back the actual matrix
     * { 7 , 3 }         { 1 , 1 }              { 7 , 3 }
     * { 5 , 9 }    x    { 2 , 5 }      =       { 5 , 9 }
     * { 0 , 0 }         { 3 , 2 }              { 0 , 0 }
     *
     * @param other the other matrix
     * @return the result of the multiplication of both matrices, in a new one
     */
    public void multiplyMatrix(Matrix other) {
        // TODO : Create errors to check the validity of both matrices
        if(!this.canMultiply(other)) {
            return;
        }

        double[][] vals;
        int l1 = this.getRowCount();
        int c1 = this.getColumnCount();

        int l2 = other.getRowCount();
        int c2 = other.getColumnCount();

        vals = new double[l1][c2];

        for(int row = 0; row < l1; row++){
            for(int col = 0; col < c2; col++){
                for(int k = 0; k < c1; k++)
                    vals[row][col] += this.getValues()[row][k] * other.getValues()[k][col];
            }
        }
        this.values = vals;
    }

    public void multiplyMatrix(Matrix other, double scalaire) {
       double[][] vals = new double[other.getRowCount()][other.getColumnCount()];
       for(int i = 0 ; i < vals.length ; i++) {
           for(int j = 0 ; j < vals[0].length ; j++) {
               if(i == vals.length -1) {
                   vals[i][j] = other.getValues()[i][j];
               } else {
                   vals[i][j] = other.getValues()[i][j] * scalaire;
               }
           }
       }

        this.values = vals;
    }

    public void multiplyMatrix(double scalaire) {
        multiplyMatrix(this, scalaire);
    }


    /**
     * Sums matrix by another one
     * <p>
     * The function is executed only if both matrices can be summed
     *
     * <b><u>For example :</u></b>
     * If you try to sum these 2 matrices, you will get a new one :
     * { 7 , 3 , 2 }         { 1 , 1 , 3 }              {  8 ,  4 ,  5 }
     * { 5 , 9 , 2 }    +    { 2 , 5 , 2 }      =       {  7 , 14 ,  4 }
     * { 0 , 0 , 1 }         { 3 , 2 , 4 }              {  3 ,  2 ,  5 }
     *
     * If you try to sums these 2 matrices, you will get an error, and get back the actual matrix
     * { 7 , 3 }         { 1 , 1 }              { 7 , 3 }
     * { 5 , 9 }    +    { 2 , 5 }      =       { 5 , 9 }
     * { 0 , 0 }         { 3 , 2 }              { 0 , 0 }
     *
     * @param other the other matrix
     * @return the result of the sum of both matrices, in a new one
     */
    public void sumMatrix(Matrix other) {
        if(canSum(other)) {
            double[][] vals = new double[this.getRowCount()][this.getColumnCount()];
            for (int row = 0; row <  this.getRowCount(); row++) {
                for (int col = 0; col < this.getColumnCount(); col++) {
                    vals[row][col] = this.getValues()[row][col] + other.getValues()[row][col];
                }
            }
            this.values = vals;
        }else {
            throw new IllegalArgumentException("MatrixNotSummable");
        }
    }

    /**
     * Subtracts matrix by another one
     * <p>
     * The function is executed only if both matrices can be subtracted
     *
     * <b><u>For example :</u></b>
     * If you try to subtract these 2 matrices, you will get a new one :
     * { 7 , 3 , 2 }         { 1 , 1 , 3 }              {  6 ,  2 , -1 }
     * { 5 , 9 , 2 }    -    { 2 , 5 , 2 }      =       {  3 ,  4 ,  0 }
     * { 0 , 0 , 1 }         { 3 , 2 , 4 }              { -3 , -2 , -3 }
     *
     * If you try to subtract these 2 matrices, you will get an error, and get back the actual matrix
     * { 7 , 3 }         { 1 , 1 }              { 7 , 3 }
     * { 5 , 9 }    -    { 2 , 5 }      =       { 5 , 9 }
     * { 0 , 0 }         { 3 , 2 }              { 0 , 0 }
     *
     * @param other the other matrix
     * @return the result of the subtraction of both matrices, in a new one
     */
    public void subMatrix(Matrix other) {
        if(canSum(other)) {
            double[][] vals = new double[this.getRowCount()][this.getColumnCount()];
            for (int row = 0; row <  this.getRowCount(); row++) {
                for (int col = 0; col < this.getColumnCount(); col++) {
                    vals[row][col] = this.getValues()[row][col] - other.getValues()[row][col];
                }
            }
            this.values = vals;
        }else {
            throw new IllegalArgumentException("MatrixNotSummable");
        }
    }

    public void homothety(double ratio) {
        double[][] vals = new double[][] {
                {ratio,     0,     0,     0},
                {    0, ratio,     0,     0},
                {    0,     0, ratio,     0},
                {    0,     0,     0,     1}
        };

        Matrix matrixVal = new Matrix(vals);
        matrixVal.multiplyMatrix(this);

        this.values = matrixVal.values;
    }

    public void translation(double t1, double t2, double t3) {
        double[][] vals = new double[][] {
                {1, 0, 0, t1},
                {0, 1, 0, t2},
                {0, 0, 1, t3},
                {0, 0, 0, 1}
        };
        Matrix matrixVal = new Matrix(vals);
        matrixVal.multiplyMatrix(this);

        this.values = matrixVal.values;
    }
    
    // TODO : Check if this is correct later
    public void translation(Vector v) {
        this.translation(v.getX(), v.getY(), v.getZ());
    }

    // TODO : Test will be hard to make...
    public void rotation(Rotation r, double degre) {
        double[][] vals = new double[this.getRowCount()][this.getColumnCount()];
        if(r.equals(Rotation.X)) {
            vals = xRotationMatrix(degre);
        } else if(r.equals(Rotation.Y)) {
            vals = yRotationMatrix(degre);
        } else if(r.equals(Rotation.Z)) {
            vals = zRotationMatrix(degre);
        } else {
            throw new IllegalArgumentException("Le type de rotation n'est pas valable.");
        }

        Matrix matrixVal = new Matrix(vals);
        matrixVal.multiplyMatrix(this);

        this.values = matrixVal.values;

    }

    public double[][] xRotationMatrix(double degre){
    	return new double[][] {
            {1, 0, 0, 0},
            {0, Math.cos(Math.toRadians(degre)), -Math.sin(Math.toRadians(degre)), 0},
            {0, Math.sin(Math.toRadians(degre)), Math.cos(Math.toRadians(degre)), 0},
            {0, 0, 0, 1},
    	};
    }
    
    public double[][] yRotationMatrix(double degre){
    	return new double[][] {
            {Math.cos(Math.toRadians(degre)), 0, Math.sin(Math.toRadians(degre)), 0},
            {0, 1, 0, 0},
            {-Math.sin(Math.toRadians(degre)), 0, Math.cos(Math.toRadians(degre)), 0},
            {0, 0, 0, 1},
    	};
    }

    private double[][] zRotationMatrix(double degre) {
    	return new double[][] {
    		{Math.cos(Math.toRadians(degre)), -Math.sin(Math.toRadians(degre)), 0, 0},
    		{Math.sin(Math.toRadians(degre)), Math.cos(Math.toRadians(degre)), 0, 0},
    		{0, 0, 1, 0},
    		{0, 0, 0, 1},
    	};
    }
    
    /**
     * Verification of multiplications
     * <p>
     * The function checks if two matrices can be multiplied
     *
     * @param other the other matrix
     * @return the result of the verification
     */
    public boolean canMultiply(Matrix other) {
        return this.getColumnCount() == other.getRowCount();
    }

    /**
     * Verification of sum
     * <p>
     * The function checks if two matrices can be summed
     *
     * @param other the other matrix
     * @return the result of the verification
     */
    public boolean canSum(Matrix other) {
        return this.getRowCount() == other.getRowCount() && this.getColumnCount() == other.getColumnCount();
    }

    /**
     * Verification of subtractions
     * <p>
     * The function checks if two matrices can be subtracted
     *
     * @param other the other matrix
     * @return the result of the verification
     */
    public boolean canSub(Matrix other) {
        return this.getRowCount() == other.getRowCount() && this.getColumnCount() == other.getColumnCount();
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

    /**
     * Get the maximum length of the whole number part of a matrix
     * <p>
     * <b><u>For example :</u></b>
     *      - 2,0578  =>  1
     *      - 25,265  =>  2
     *      - 105,1   =>  3
     *
     * @return the length
     */
    public int getMaxDecimalPart() {
    	int length = 0;
    	for(double[] tab : this.getValues()) {
    		for(double tab2 : tab) {
    			int tmpLength = ((int)tab2 + "").length(); 
    			if(length < tmpLength) {
    				length = tmpLength;
    			}
    		}
    	}
		return length + 3; // 3 = {,00} format
    }
    
    public double[][] getDefaultValues() {
		return defaultValues;
	}

    public void resetToDefaultValues() {
        this.values = defaultValues.clone();
    }

    @Override
    public String toString() {
    	String res = "";
        for(int i = 0 ; i < this.getRowCount() ; i ++ ) {
            for(int j = 0 ; j < this.getColumnCount() ; j ++ ) {

                String simpliestFormat = String.format("%.2f", this.getValues()[i][j]).replace(",",".");

                while(simpliestFormat.length() != this.getMaxDecimalPart()) {
                    simpliestFormat = " " + simpliestFormat;
                }

            	res+= "[" + simpliestFormat + "]";
            
            }
            res+="\n";
            }
        return res;
    }

}