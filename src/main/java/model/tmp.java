package model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class tmp {

    public static void main(String[] args) {



        System.out.println("youpi");
        Matrix m1 = new Matrix(new double[][] {
                {1.3,1,1.006},
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
       /* System.out.println(m1.canMultiply(m2));
        System.out.println(m1.canSum(m2));*/
        /*Matrix m3 = m1.sumMatrix(m2);
        System.out.println(m3);
        Matrix m4 = new Matrix(new double[][] {{1d,5d},{2d,3d},{1d,7d}});
        Matrix m5 = new Matrix(new double[][] {{1d, 2d, 3d},{5d, 2d, 8d}});
        System.out.println(m4.canMultiply(m5));
        Matrix m6 = m4.multiplyMatrix(m5);
        System.out.println(m6);*/
    }

}

