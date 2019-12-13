package com.github.jxen.svg.optimizer;

import com.github.jxen.math.linear.Column;
import com.github.jxen.svg.model.Line;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;

class LineTransformApplier extends TransformApplier<Line> {

	@Override
	void transform(Line line, Transform transform) {
		Column v = transform.toMatrix().multiply(Column.of(line.getX1().getValue(), line.getY1().getValue(), 1));
		line.setX1(line.getX1().to(Point.from(v).getX()));
		line.setY1(line.getY1().to(Point.from(v).getY()));
		v = transform.toMatrix().multiply(Column.of(line.getX2().getValue(), line.getY2().getValue(), 1));
		line.setX2(line.getX2().to(Point.from(v).getX()));
		line.setY2(line.getY2().to(Point.from(v).getY()));
		line.setTransform(null);
	}
}
