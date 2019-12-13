package com.github.jxen.svg.model.geometry;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.transform.Transform;
import com.github.jxen.svg.transform.TransformDecomposition;
import com.github.jxen.svg.transform.TranslateLastDecomposer;

/**
 * {@code Ellipse} class represents ellipse shape used in SVG models.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class Ellipse {

	private static final String ERROR_CHOOSE_ELLIPSE = "Cannot choose correct ellipse";

	private final double rx;
	private final double ry;
	private final double angle;
	private final Point center;

	/**
	 * @param rx     x-radius
	 * @param ry     x-radius
	 * @param angle  rotation angle
	 * @param center center
	 */
	public Ellipse(double rx, double ry, double angle, Point center) {
		this.rx = rx;
		this.ry = ry;
		this.angle = angle;
		this.center = center;
	}

	/**
	 * @param rx     x-radius
	 * @param ry     x-radius
	 * @param angle  rotation angle
	 */
	public Ellipse(double rx, double ry, double angle) {
		this(rx, ry, angle, new Point(0, 0));
	}

	/**
	 * @return the rx
	 */
	public double getRx() {
		return rx;
	}

	/**
	 * @return the ry
	 */
	public double getRy() {
		return ry;
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @return the center
	 */
	public Point getCenter() {
		return center;
	}

	@Override
	public String toString() {
		return String.format("%s - (%f, %f) %f", center, rx, ry, angle);
	}

	/**
	 * Transforms ellipse according given matrix.
	 *
	 * @param transform transform to be applied
	 * @return transformed ellipse
	 */
	public Ellipse transform(Transform transform) {
		Ellipse ellipse = this;
		TranslateLastDecomposer decomposer = new TranslateLastDecomposer();
		TransformDecomposition decomposition = decomposer.decompose(transform);
		Transform scale = decomposition.scale();
		if (!scale.toMatrix().isIdentity()) {
			ellipse = ellipse.scale(scale);
		}
		Transform skewX = decomposition.skewX();
		if (!skewX.toMatrix().isIdentity()) {
			ellipse = ellipse.skewX(scale);
		}
		Transform rotate = decomposition.rotate();
		if (!rotate.toMatrix().isIdentity()) {
			ellipse = ellipse.rotate(rotate);
		}
		return ellipse;
	}

	private Ellipse scale(Transform scale) {
		Point[] points = getPoints();
		return calculate(points, scale, new ScaleChooser());
	}

	private Ellipse skewX(Transform skewX) {
		Point[] points = getPoints();
		return calculate(points, skewX, new SkewXChooser());
	}

	private Ellipse rotate(Transform rotate) {
		Point p = new Point(1, 0).transform(rotate);
		double a = Math.toDegrees(Math.atan2(p.getY(), p.getX()));
		final int wholeRound = 360;
		return new Ellipse(rx, ry, (angle + a) % wholeRound, center);
	}

	private Point[] getPoints() {
		double phi = Math.toRadians(angle);
		double cos = Math.cos(phi);
		double sin = Math.sin(phi);
		Point p1 = new Point(rx * cos, rx * sin);
		double sqrt2 = 1 / Math.sqrt(2);
		Point p2 = new Point(sqrt2 * (rx * cos - ry * sin), sqrt2 * (rx * sin + ry * cos));
		Point p3 = new Point(-ry * sin, ry * cos);
		return new Point[] {p1, p2, p3};
	}

	private Ellipse calculate(Point[] points, Transform transform, Chooser chooser) {
		final int pointsCount = 3;
		Point[] p = new Point[pointsCount];
		for (int i = 0; i < pointsCount; i++) {
			p[i] = points[i].transform(transform);
		}
		Matrix m = Matrix.of(
				p[0].getX() * p[0].getX(), 2 * p[0].getX() * p[0].getY(), p[0].getY() * p[0].getY(),
				p[1].getX() * p[1].getX(), 2 * p[1].getX() * p[1].getY(), p[1].getY() * p[1].getY(),
				p[2].getX() * p[2].getX(), 2 * p[2].getX() * p[2].getY(), p[2].getY() * p[2].getY());
		if (m.isSingular()) {
			throw new SvgException("Ellipse does not exist for given points");
		}
		double detA = Matrix.of(
				1, 2 * p[0].getX() * p[0].getY(), p[0].getY() * p[0].getY(),
				1, 2 * p[1].getX() * p[1].getY(), p[1].getY() * p[1].getY(),
				1, 2 * p[2].getX() * p[2].getY(), p[2].getY() * p[2].getY()).determinant();
		double detB = Matrix.of(
				p[0].getX() * p[0].getX(), 1, p[0].getY() * p[0].getY(),
				p[1].getX() * p[1].getX(), 1, p[1].getY() * p[1].getY(),
				p[2].getX() * p[2].getX(), 1, p[2].getY() * p[2].getY()).determinant();
		double detC = Matrix.of(
				p[0].getX() * p[0].getX(), 2 * p[0].getX() * p[0].getY(), 1,
				p[1].getX() * p[1].getX(), 2 * p[1].getX() * p[1].getY(), 1,
				p[2].getX() * p[2].getX(), 2 * p[2].getX() * p[2].getY(), 1).determinant();
		double det = m.determinant();
		double a = detA / det;
		double b = detB / det;
		double c = detC / det;
		if (b == 0) {
			return new Ellipse(Math.sqrt(1 / a), Math.sqrt(1 / c), 0, center);
		}
		Matrix matrix = Matrix.of(a, b, b, c);
		double[] lambda = matrix.eigenValues();
		double[] k = {-(a - lambda[0]) / b, -(a - lambda[1]) / b};
		double x1 = 1 / Math.sqrt(a + 2 * b * k[0] + c * k[0] * k[0]);
		double y1 = k[0] * x1;
		double x2 = 1 / Math.sqrt(a + 2 * b * k[1] + c * k[1] * k[1]);
		double y2 = k[1] * x2;
		return chooser.choose(new Point[] {
			new Point(x1, y1), new Point(x2, y2), new Point(-x1, -y1), new Point(-x2, -y2),
		});
	}

	private interface Chooser {
		Ellipse choose(Point[] points);
	}

	private class ScaleChooser implements Chooser {
		@Override
		public Ellipse choose(Point[] points) {
			double a = Math.toRadians(angle);
			double signX = Math.signum(Math.cos(a));
			double signY = Math.signum(Math.sin(a));
			final int count = 4;
			for (int i = 0; i < count; i++) {
				Point p = points[i];
				double x = p.getX();
				double y = p.getY();
				if (x * signX > 0 && y * signY > 0) {
					double alpha = Math.toDegrees(Math.atan2(y, x));
					double r1 = Math.hypot(x, y);
					Point p2 = points[(i + 1) % count];
					double r2 = Math.hypot(p2.getX(), p2.getY());
					return new Ellipse(r1, r2, alpha, center);
				}
			}
			throw new SvgException(ERROR_CHOOSE_ELLIPSE);
		}
	}

	private class SkewXChooser implements Chooser {
		@Override
		public Ellipse choose(Point[] points) {
			// TODO Fix the implementation
			return new ScaleChooser().choose(points);
		}
	}
}
