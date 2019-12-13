package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;

abstract class AbstractTranslateDecomposer implements TransformDecomposer {

	@Override
	public TransformDecomposition decompose(Transform transform) {
		Matrix m = transform.toMatrix();
		double alpha = m.isZero(0, 0) ? Math.PI / 2 : Math.atan(m.get(1, 0) / m.get(0, 0));
		double det = m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0);
		double hx = (m.get(0, 0) * m.get(0, 1) + m.get(1, 0) * m.get(1, 1)) / det;
		double sx = m.isZero(0, 0) ? m.get(1, 0) / Math.sin(alpha) : m.get(0, 0) / Math.cos(alpha);
		double sy = sx * det / (m.get(0, 0) * m.get(0, 0) + m.get(1, 0) * m.get(1, 0));
		Transform scale = Transform.scale(sx, sy);
		Transform skewX = Transform.skewX(Math.toDegrees(Math.atan(hx)));
		Transform rotate = Transform.rotate(Math.toDegrees(alpha));
		return addTranslate(m, scale, skewX, rotate);
	}

	abstract TransformDecomposition addTranslate(Matrix matrix, Transform scale, Transform skewX, Transform rotate);
}
