package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.api.SvgException;
import java.util.List;

/**
 * {@code Transform} class represents affine transformation.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public final class Transform {

	private static final String ERROR_MESSAGE = "Incorrect transform matrix: ";

	private static final int MATRIX_SIZE = 3;
	private static final int MATRIX_VALUES = 6;

	private final Matrix matrix;
	private final TransformType type;

	private Transform(Matrix matrix) {
		this.matrix = matrix;
		type = TransformType.of(matrix);
	}

	/**
	 * @param matrix matrix
	 * @return transform for given matrix
	 */
	public static Transform of(Matrix matrix) {
		ensure(matrix);
		return new Transform(matrix);
	}

	/**
	 * @param values matrix values
	 * @return transform for given matrix values
	 */
	public static Transform of(List<Double> values) {
		if (values.size() != MATRIX_VALUES) {
			throw new SvgException(ERROR_MESSAGE);
		}
		return of(matrix(values));
	}

	/**
	 * @param values matrix values
	 * @return transform for given matrix values
	 */
	public static Transform of(double... values) {
		if (values.length != MATRIX_VALUES) {
			throw new SvgException(ERROR_MESSAGE);
		}
		return of(matrix(values));
	}

	/**
	 * @param tx tx
	 * @param ty tx
	 * @return translate transform
	 */
	public static Transform translate(double tx, double ty) {
		return of(1, 0, 0, 1, tx, ty);
	}

	/**
	 * @param tx tx
	 * @return translate transform
	 */
	public static Transform translate(double tx) {
		return of(1, 0, 0, 1, tx, 0);
	}

	/**
	 * @param sx sx
	 * @param sy sy
	 * @return scale transform
	 */
	public static Transform scale(double sx, double sy) {
		return of(sx, 0, 0, sy, 0, 0);
	}

	/**
	 * @param sx sx
	 * @return scale transform
	 */
	public static Transform scale(double sx) {
		return of(sx, 0, 0, sx, 0, 0);
	}

	/**
	 * @param a  angle
	 * @param cx cx
	 * @param cy cy
	 * @return rotate transform
	 */
	public static Transform rotate(double a, double cx, double cy) {
		return translate(cx, cy).multiply(rotate(a)).multiply(translate(-cx, -cy));
	}

	/**
	 * @param a  angle
	 * @return rotate transform
	 */
	public static Transform rotate(double a) {
		double angle = Math.toRadians(a);
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		return of(c, s, -s, c, 0, 0);
	}

	/**
	 * @param a angle
	 * @return skew X transform
	 */
	public static Transform skewX(double a) {
		double angle = Math.toRadians(a);
		double t = Math.tan(angle);
		return of(1, 0, t, 1, 0, 0);
	}

	/**
	 * @param a angle
	 * @return skew Y transform
	 */
	public static Transform skewY(double a) {
		double angle = Math.toRadians(a);
		double t = Math.tan(angle);
		return of(1, t, 0, 1, 0, 0);
	}

	/**
	 * @return transformation matrix
	 */
	public Matrix toMatrix() {
		return matrix;
	}

	/**
	 * @param transform transform
	 * @return product of transformations
	 */
	public Transform multiply(Transform transform) {
		return new Transform(matrix.multiply(transform.matrix));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + matrix.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Transform other = (Transform) obj;
		return matrix.equals(other.matrix);
	}

	@Override
	public String toString() {
		return type.toString(this);
	}

	static void ensure(Matrix matrix) {
		if (matrix.size() != MATRIX_SIZE) {
			throw new SvgException(ERROR_MESSAGE + matrix);
		}
		if (!matrix.isZero(2, 0) || !matrix.isZero(2, 1) || !matrix.isOne(2, 2)) {
			throw new SvgException(ERROR_MESSAGE + matrix);
		}
		if (matrix.isSingular()) {
			throw new SvgException(ERROR_MESSAGE + matrix);
		}
	}

	private static Matrix matrix(List<Double> values) {
		double[][] m = new double[MATRIX_SIZE][MATRIX_SIZE];
		for (int i = 0; i < MATRIX_SIZE; i++) {
			m[0][i] = values.get(2 * i);
			m[1][i] = values.get(2 * i + 1);
		}
		m[2] = new double[] {0, 0, 1};
		return Matrix.of(m);
	}

	private static Matrix matrix(double[] values) {
		double[][] m = new double[MATRIX_SIZE][MATRIX_SIZE];
		for (int i = 0; i < MATRIX_SIZE; i++) {
			m[0][i] = values[2 * i];
			m[1][i] = values[2 * i + 1];
		}
		m[2] = new double[] {0, 0, 1};
		return Matrix.of(m);
	}
}
