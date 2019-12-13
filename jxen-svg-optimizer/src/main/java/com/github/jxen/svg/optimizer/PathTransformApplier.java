package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.model.Path;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.model.path.PathSeg;
import com.github.jxen.svg.transform.Transform;
import java.util.ArrayList;
import java.util.List;

class PathTransformApplier extends TransformApplier<Path> {

	@Override
	void transform(Path path, Transform transform) {
		Point o = new Point(0, 0);
		Point n = new Point(0, 0);
		List<PathSeg> transformed = new ArrayList<>();
		for (PathSeg s : path.getD()) {
			PathSeg seg = s.transform(transform, o, n);
			transformed.add(seg);
			o = s.getEndPoint();
			n = seg.getEndPoint();
		}
		path.setD(transformed);
		path.setTransform(null);
	}
}
