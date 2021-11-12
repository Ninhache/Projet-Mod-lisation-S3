package model_tests;
import model.Matrix;

import org.junit.jupiter.api.*;

public class Matrix_Test {

    private Matrix m1, m2, m3, m4, m5;

    @BeforeEach
    public void setUp() {
        /*
        *  Matrix are typed like this :
        *       { 0, 1, 2 }
        *       { 3, 4, 5 }
        * BUT THEY ARE LIKE THIS IN REALITY :
        *       { 0 3 }
        *       { 1 4 }
        *       { 2 5 }
        * */
        m1 = new Matrix(new double[][] {
                {1,1,1},
                {3,0,2}
        });
        m2 = new Matrix(new double[][] {
                {0,7,2},
                {0,5,1}
        });
        m3 = new Matrix(new double[][] {
                {0,7},
                {0,5},
                {2,2}
        });
        m4 = new Matrix(new double[][] {
                {4,1},
                {2,5},
                {3,6}
        });
        m5 = new Matrix(new double[][] {
                {4,1,1},
                {2,5,2},
                {3,6,3}
        });

    }

    @Test
    public void test_can_multiply_matrix() {
        Assertions.assertFalse(m1.canMultiply(m2));
        Assertions.assertTrue(m1.canMultiply(m3));
        Assertions.assertTrue(m1.canMultiply(m4));
        Assertions.assertTrue(m2.canMultiply(m3));
        Assertions.assertTrue(m2.canMultiply(m4));
        Assertions.assertFalse(m4.canMultiply(m5));
    }

    @Test
    public void test_can_sum_matrix() {
        Assertions.assertTrue(m1.canSum(m2));
        Assertions.assertTrue(m2.canSum(m1));
        Assertions.assertFalse(m1.canSum(m3));
        Assertions.assertFalse(m3.canSum(m1));
        Assertions.assertTrue(m3.canSum(m4));
        Assertions.assertFalse(m1.canSum(m5));
        Assertions.assertFalse(m3.canSum(m5));
        Assertions.assertTrue(m5.canSum(m5));
    }

    @Test
    public void test_can_sub_matrix() {
        Assertions.assertTrue(m1.canSub(m2));
        Assertions.assertTrue(m2.canSub(m1));
        Assertions.assertFalse(m1.canSub(m3));
        Assertions.assertFalse(m3.canSub(m1));
        Assertions.assertTrue(m3.canSub(m4));
        Assertions.assertFalse(m1.canSub(m5));
        Assertions.assertFalse(m3.canSub(m5));
        Assertions.assertTrue(m5.canSub(m5));
    }

    @Test
    public void test_sum_matrix_matrix() {
        Matrix tmpMatrixM1M2 = new Matrix(new double[][] {{1,8,3},{3,5,3}});

        //Assertions.assertArrayEquals(tmpMatrixM1M2.getValues(), m1.sumMatrix(m2).getValues());
        //Assertions.assertArrayEquals(tmpMatrixM1M2.getValues(), m2.sumMatrix(m1).getValues());
    }

    @Test
    public void test_multiply_matrix_matrix() {
        Matrix tmpMatrixM1M3 = new Matrix(new double[][] {{2,14},{4,25}});
        Matrix tmpMatrixM3M1 = new Matrix(new double[][] {{21,0,14}, {15,0,10},{8,2,6}});

        //Assertions.assertArrayEquals(tmpMatrixM1M3.getValues(), m1.multiplyMatrix(m3).getValues());
        //Assertions.assertArrayEquals(tmpMatrixM3M1.getValues(), m3.multiplyMatrix(m1).getValues());
    }

//    @Test
//    public void test_sub_matrix_matrix() {
//        Matrix tmpMatrixM1M2 = new Matrix(new double[][] {{1,-6,-1},{3,-5,1}});
//        Matrix tmpMatrixM2M1 = new Matrix(new double[][] {{-1,6,1},{-3,5,-1}});
//
//        Assertions.assertArrayEquals(tmpMatrixM1M2.getValues(), m1.subMatrix(m2).getValues());
//        Assertions.assertArrayEquals(tmpMatrixM2M1.getValues(), m2.subMatrix(m1).getValues());
//    }




}
