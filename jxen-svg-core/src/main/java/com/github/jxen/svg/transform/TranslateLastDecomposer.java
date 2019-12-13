package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import java.util.Arrays;

/**
 * {@code TranslateLastDecomposer} class is responsible for affine transformation decomposition.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class TranslateLastDecomposer extends AbstractTranslateDecomposer {

	/*
	@Override
	public TranslateLastDecomposition decompose(Transform transform) {
		Matrix m = transform.toMatrix();
		double alpha = m.isZero(0, 0) ? Math.PI / 2 : Math.atan(m.get(1, 0) / m.get(0, 0));
		double det = m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0);
		double hx = (m.get(0, 0) * m.get(0, 1) + m.get(1, 0) * m.get(1, 1)) / det;
		double sx = m.isZero(0, 0) ? m.get(1, 0) / Math.sin(alpha) : m.get(0, 0) / Math.cos(alpha);
		double sy = sx * det / (m.get(0, 0) * m.get(0, 0) + m.get(1, 0) * m.get(1, 0));
		Transform scale = Transform.scale(sx, sy);
		Transform skewX = Transform.skewX(Math.toDegrees(Math.atan(hx)));
		Transform rotate = Transform.rotate(Math.toDegrees(alpha));
		double tx = m.get(0, 2);
		double ty = m.get(1, 2);
		Transform translate = Transform.translate(tx, ty);
		return new TranslateLastDecomposition(Arrays.asList(scale, skewX, rotate, translate));
	}
	*/

	@Override
	TransformDecomposition addTranslate(Matrix matrix, Transform scale, Transform skewX, Transform rotate) {
		double tx = matrix.get(0, 2);
		double ty = matrix.get(1, 2);
		Transform translate = Transform.translate(tx, ty);
		return new TranslateLastDecomposition(Arrays.asList(scale, skewX, rotate, translate));
	}
}
