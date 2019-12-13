package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.format.FormatHelper;
import java.util.List;

class DefaultHelper implements TransformHelper {

	@Override
	public Transform create(List<Double> values) {
		return Transform.of(values);
	}

	@Override
	public boolean test(Matrix matrix) {
		return true;
	}

	@Override
	public String toString(Transform transform) {
		FormatHelper helper = format();
		StringBuilder builder = new StringBuilder();
		Matrix m = transform.toMatrix();
		helper.add(m.get(0, 0), builder);
		helper.comma(builder);
		helper.add(m.get(1, 0), builder);
		helper.comma(builder);
		helper.add(m.get(0, 1), builder);
		helper.comma(builder);
		helper.add(m.get(1, 1), builder);
		helper.comma(builder);
		helper.add(m.get(0, 2), builder);
		helper.comma(builder);
		helper.add(m.get(1, 2), builder);
		return builder.toString();
	}
}
