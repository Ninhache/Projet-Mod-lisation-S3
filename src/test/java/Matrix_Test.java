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
        Assertions.assertTrue(m4.canMultiply(m5));
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


}
