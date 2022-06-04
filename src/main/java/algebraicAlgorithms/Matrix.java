package algebraicAlgorithms;

import java.math.BigInteger;

public class Matrix {
    BigInteger x11;
    BigInteger x12;
    BigInteger x21;
    BigInteger x22;

    public Matrix(BigInteger x11, BigInteger x12, BigInteger x21, BigInteger x22) {
        this.x11 = x11;
        this.x12 = x12;
        this.x21 = x21;
        this.x22 = x22;
    }

    public static Matrix IDENTITY = new Matrix(new BigInteger("1"),
            new BigInteger("0"),
            new BigInteger("0"),
            new BigInteger("1"));

    public Matrix multiply(Matrix matrix) {
        BigInteger a = x11.multiply(matrix.x11).add(x12.multiply(matrix.x21));
        BigInteger b = x11.multiply(matrix.x12).add(x12.multiply(matrix.x22));
        BigInteger c = x21.multiply(matrix.x11).add(x22.multiply(matrix.x21));
        BigInteger d = x21.multiply(matrix.x12).add(x22.multiply(matrix.x22));

        x11 = a;
        x12 = b;
        x21 = c;
        x22 = d;
        return this;
    }
}
