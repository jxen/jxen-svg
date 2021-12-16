package com.github.jxen.svg.optimizer;

import com.github.jxen.math.linear.Column;
import com.github.jxen.svg.model.Circle;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import com.github.jxen.svg.transform.TransformDecomposition;
import com.github.jxen.svg.transform.TranslateFirstDecomposer;
import java.util.Arrays;
import java.util.List;

class CircleTransformApplier extends TransformApplier<Circle> {

  @Override
  void transform(Circle circle, Transform transform) {
    TranslateFirstDecomposer decomposer = new TranslateFirstDecomposer();
    TransformDecomposition decomposition = decomposer.decompose(transform);
    Transform scale = decomposition.scale();
    double factor = Math.abs(scale.toMatrix().get(0, 0));
    Transform toApply = Transform.scale(factor).multiply(decomposition.translate());
    List<Transform> rest = Arrays.asList(decomposition.rotate(), decomposition.skewX(),
        Transform.scale(scale.toMatrix().get(0, 0) / factor, scale.toMatrix().get(1, 1) / factor));
    Column v = toApply.toMatrix().multiply(Column.of(circle.getCx().getValue(), circle.getCy().getValue(), 1));
    circle.setCx(circle.getCx().to(Point.from(v).getX()));
    circle.setCy(circle.getCy().to(Point.from(v).getY()));
    circle.setR(circle.getR().multiply(factor));
    circle.setTransform(rest);
  }
}
