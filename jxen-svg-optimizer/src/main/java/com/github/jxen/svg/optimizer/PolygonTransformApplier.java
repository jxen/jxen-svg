package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.model.Polygon;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import java.util.ArrayList;
import java.util.List;

class PolygonTransformApplier extends TransformApplier<Polygon> {

  @Override
  void transform(Polygon polygon, Transform transform) {
    List<Point> transformed = new ArrayList<>();
    for (Point p : polygon.getPoints()) {
      transformed.add(p.transform(transform));
    }
    polygon.setPoints(transformed);
    polygon.setTransform(null);
  }
}
