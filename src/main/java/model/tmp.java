package model;

public class tmp {

    public static void main(String[] args) {

/*

        System.out.println("youpi");
        Matrix m1 = new Matrix(new double[][] {
                {55551.3,1,1.006},
                {3.123,0,2}
        });
        Matrix m2 = new Matrix(new double[][] {
                {0,7,2},
                {0,5,1}
        });
        Matrix m3 = new Matrix(new double[][] {
                {0,7.555},
                {0,5},
                {2,2}
        });
        System.out.println(m3.multiplyMatrix(m1));
        */
       /* System.out.println(m1.canMultiply(m2));
        System.out.println(m1.canSum(m2));*/
        /*Matrix m3 = m1.sumMatrix(m2);
        System.out.println(m3);
        Matrix m4 = new Matrix(new double[][] {{1d,5d},{2d,3d},{1d,7d}});
        Matrix m5 = new Matrix(new double[][] {{1d, 2d, 3d},{5d, 2d, 8d}});
        System.out.println(m4.canMultiply(m5));
        Matrix m6 = m4.multiplyMatrix(m5);
        System.out.println(m6);*/
    	
    	/*
    	Reader reader = new Reader("test");
    	try {
    		System.out.println(reader.readPly().getMatrix());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    	//double ratio = 1080.0/1920.0;
    	//System.out.println("AAAAAAA"+ratio);
        Matrix m = new Matrix(new double[][] {
                {1d,0d,0d,250d},
                {0d,1d,0d,250d},
                {0d,0d,1d,0d},
                {0d,0d,0d,1d}});
        Matrix m2 = new Matrix(new double[][]
        {
            {1d,0d,-1d,0d,0d,0d},
            {0d,-1d,0d,1d,0d,0d},
            {0d,0d,0d,0d,1d,-1d},
            {1d,1d,1d,1d,1d,1d}
        });
        System.out.println(m);
        //printMatrix(m3.getValues(),m2.getRowCount(),m3.getColumnCount());
        //System.out.println(m2);
        System.out.println(m2);
        System.out.println("M2 avec M:" + m2.canMultiply(m));
        System.out.println("M avec M2:" + m.canMultiply(m2));
        //Matrix.printMatrix(m.getValues(), m.getRowCount(), m.getColumnCount());
        //System.out.println(m.multiplyMatrix(m2));

        //printMatrix(m2.getValues(), m2.getRowCount(), m2.getColumnCount());
    }

}

