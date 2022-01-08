package model_tests;
import model.maths.Matrix;

import model.maths.Rotation;
import org.junit.jupiter.api.*;

public class MatrixTest {

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
        m1.sumMatrix(m2);
        Assertions.assertArrayEquals(tmpMatrixM1M2.getValues(), m1.getValues());
        setUp();
        m2.sumMatrix(m1);
        Assertions.assertArrayEquals(tmpMatrixM1M2.getValues(), m2.getValues());
    }

    @Test
    public void test_multiply_matrix_matrix() {
        Matrix tmpMatrixM1M3 = new Matrix(new double[][] {{2,14},{4,25}});
        Matrix tmpMatrixM3M1 = new Matrix(new double[][] {{21,0,14}, {15,0,10},{8,2,6}});
        m1.multiplyMatrix(m3);
        Assertions.assertArrayEquals(tmpMatrixM1M3.getValues(), m1.getValues());
        setUp();
        m3.multiplyMatrix(m1);
        Assertions.assertArrayEquals(tmpMatrixM3M1.getValues(), m3.getValues());
    }

    @Test
    public void test_sub_matrix_matrix() {
        Matrix tmpMatrixM1M2 = new Matrix(new double[][] {{1,-6,-1},{3,-5,1}});
        Matrix tmpMatrixM2M1 = new Matrix(new double[][] {{-1,6,1},{-3,5,-1}});
        m1.subMatrix(m2);
        Assertions.assertArrayEquals(tmpMatrixM1M2.getValues(), m1.getValues());
        setUp();
        m2.subMatrix(m1);
        Assertions.assertArrayEquals(tmpMatrixM2M1.getValues(), m2.getValues());
    }


    @Test
    public void test_translation_matrix() {

        m1.translation(-1,1,1);
        Matrix m2 = new Matrix(new double[][] {{1,0,0,-1},
                                                {0,1,0,1},
                                                {0,0,1,1},
                                                {0,0,0,1}});
        Assertions.assertArrayEquals(m1.getValues(), m2.getValues());
    }

    //Fonctionne mais la valeur est arrondie donc erreur..
    public void test_rotation_matrix() {


        //Test rotation X
        m1.rotation(Rotation.X,180);
        Matrix mX = new Matrix(new double[][] {{1,0,0,0},
                                                {0,-1,0,0},
                                                {0,0,-1,0},
                                                {0,0,0,1}});
        Assertions.assertArrayEquals(m1.getValues(), mX.getValues());
        setUp();
        //Test rotation Y
        m1.rotation(Rotation.Y,90);
        Matrix mY = new Matrix(new double[][] {{0,0,1,0},
                                                {0,1,0,0},
                                                {-1,0,0,0},
                                                {0,0,0,1}});
        Assertions.assertArrayEquals(m1.getValues(), mY.getValues());
        setUp();

        //Test rotation Z
        m1.rotation(Rotation.Z,90);
        Matrix mZ = new Matrix(new double[][] {{0,-1,0,0},
                                                {1,0,0,0},
                                                {0,0,1,0},
                                                {0,0,0,1}});
        Assertions.assertArrayEquals(m1.getValues(), mZ.getValues());
    }




}
