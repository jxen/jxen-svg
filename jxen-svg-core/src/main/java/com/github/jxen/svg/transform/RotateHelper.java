package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.format.FormatHelper;
import java.util.List;

class RotateHelper implements TransformHelper {

	@Override
	public Transform create(List<Double> values) {
		if (values.size() == 1) {
			return Transform.rotate(values.get(0));
		}
		return Transform.rotate(values.get(0), values.get(1), values.get(2));
	}

	@Override
	public boolean test(Matrix matrix) {
		if (matrix.isZero(1, 0) && matrix.isZero(0, 1)) {
			return false;
		}
		return matrix.minor(2, 2).isOrthogonal();
	}

	@Override
	public String toString(Transform transform) {
		Matrix m = transform.toMatrix();
		double angle = Math.toDegrees(Math.asin(m.get(1, 0)));
		FormatHelper helper = format();
		StringBuilder builder = new StringBuilder();
		helper.add(angle, builder);
		if (!m.isZero(0, 2) || !m.isZero(1, 2)) {
			double k = m.get(1, 0) / (1 - m.get(0, 0));
			double cx = (m.get(0, 2) - m.get(1, 2) * k) / 2;
			double cy = (m.get(1, 2) + m.get(0, 2) * k) / 2;
			helper.comma(builder);
			helper.add(cx, builder);
			helper.comma(builder);
			helper.add(cy, builder);
		}
		return builder.toString();
	}
}
