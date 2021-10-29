package model;

/* FUNCTIONS TODO :
*   mutliplication(Matrice m1, scalaire)
*   translation(vecteur???)
*   rotation(x/y/z ; degree) >>> faire une enum pour la rotation (voir mon git)
* */

/**
 * This class handle all matrix calculation we need for the project
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
     * A Matrix is an multidimensional array
     *
     * @param values list of values of the Matrix
     */
    public Matrix(double[][] values) {

    	this.defaultValues = values.clone();

        this.values = values;
    }
   

    /**
     * Mulitply matrix by another one
     * <p>
     * The fonction is executed only if both matrix can be multiplied
     *
     * <b><u>For exemple :</u></b>
     * If you try multiply these 2 matrix, you will get a new one :
     * { 7 , 3 , 2 }         { 1 , 1 , 3 }              { 19 , 26 , 35 }
     * { 5 , 9 , 2 }    x    { 2 , 5 , 2 }      =       { 29 , 54 , 41 }
     * { 0 , 0 , 1 }         { 3 , 2 , 4 }              {  3 ,  2 ,  4 }
     *
     * If you try multiply these 2 matrix, you will get an error, and get back the actual matrix
     * { 7 , 3 }         { 1 , 1 }              { 7 , 3 }
     * { 5 , 9 }    x    { 2 , 5 }      =       { 5 , 9 }
     * { 0 , 0 }         { 3 , 2 }              { 0 , 0 }
     *
     * @param other the other matrix
     * @return the result of the both matrix, in a new one
     */
    public Matrix multiplyMatrix(Matrix other) {
        // TODO : Create errors to check validity of both matrix
        if(!this.canMultiply(other)) {
            return this;
        }


        double vals[][];
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
        return new Matrix(vals);
    }

    /**
     * Sum matrix by another one
     * <p>
     * The fonction is executed only if both matrix can be summed
     *
     * <b><u>For exemple :</u></b>
     * If you try to sums these 2 matrix, you will get a new one :
     * { 7 , 3 , 2 }         { 1 , 1 , 3 }              {  8 ,  4 ,  5 }
     * { 5 , 9 , 2 }    +    { 2 , 5 , 2 }      =       {  7 , 14 ,  4 }
     * { 0 , 0 , 1 }         { 3 , 2 , 4 }              {  3 ,  2 ,  5 }
     *
     * If you try to sums these 2 matrix, you will get an error, and get back the actual matrix
     * { 7 , 3 }         { 1 , 1 }              { 7 , 3 }
     * { 5 , 9 }    +    { 2 , 5 }      =       { 5 , 9 }
     * { 0 , 0 }         { 3 , 2 }              { 0 , 0 }
     *
     * @param other the other matrix
     * @return the result of the both matrix, in a new one
     */
    public Matrix sumMatrix(Matrix other) {
        if(canSum(other)) {
            double[][] res = new double[this.getRowCount()][this.getColumnCount()];
            for (int row = 0; row <  this.getRowCount(); row++) {
                for (int col = 0; col < this.getColumnCount(); col++) {
                    res[row][col] = this.getValues()[row][col] + other.getValues()[row][col];
                }
            }
            return new Matrix(res);
        }else {
            throw new IllegalArgumentException("MatrixNotSummable");
        }
    }

    /**
     * Sub matrix by another one
     * <p>
     * The fonction is executed only if both matrix can be subbed
     *
     * <b><u>For exemple :</u></b>
     * If you try to subs these 2 matrix, you will get a new one :
     * { 7 , 3 , 2 }         { 1 , 1 , 3 }              {  6 ,  2 , -1 }
     * { 5 , 9 , 2 }    -    { 2 , 5 , 2 }      =       {  3 ,  4 ,  0 }
     * { 0 , 0 , 1 }         { 3 , 2 , 4 }              { -3 , -2 , -3 }
     *
     * If you try to sums these 2 matrix, you will get an error, and get back the actual matrix
     * { 7 , 3 }         { 1 , 1 }              { 7 , 3 }
     * { 5 , 9 }    -    { 2 , 5 }      =       { 5 , 9 }
     * { 0 , 0 }         { 3 , 2 }              { 0 , 0 }
     *
     * @param other the other matrix
     * @return the result of the both matrix, in a new one
     */
    public Matrix subMatrix(Matrix other) {
        if(canSum(other)) {
            double[][] res = new double[this.getRowCount()][this.getColumnCount()];
            for (int row = 0; row <  this.getRowCount(); row++) {
                for (int col = 0; col < this.getColumnCount(); col++) {
                    res[row][col] = this.getValues()[row][col] - other.getValues()[row][col];
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
        return new Matrix(vals).multiplyMatrix(this);
    }

    public Matrix translation(double t1, double t2, double t3) {
        double vals[][] = new double[][] {
                {1, 0, 0, t1},
                {0, 1, 0, t2},
                {0, 0, 1, t3},
                {0, 0, 0, 1}
        };
        return new Matrix(vals).multiplyMatrix(this);
    }
    // TODO : Check if this is correct later
    public Matrix translation(Vector v) {
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

        return new Matrix(vals).multiplyMatrix(this);
    }

    /**
     * Verifications of multiplications
     * <p>
     * The function check if two matrix can be multiplied
     *
     * @param other the other matrix
     * @return the result of the verification
     */
    public boolean canMultiply(Matrix other) {
        return this.getColumnCount() == other.getRowCount();
    }

    /**
     * Verifications of sum
     * <p>
     * The function check if two matrix can be summed
     *
     * @param other the other matrix
     * @return the result of the verification
     */
    public boolean canSum(Matrix other) {
        return this.getRowCount() == other.getRowCount() && this.getColumnCount() == other.getColumnCount();
    }

    /**
     * Verifications of sub
     * <p>
     * The function check if two matrix can be subbed
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
     * <b><u>For exemple :</u></b>
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