package com.github.jxen.svg.optimizer;

import com.github.jxen.math.linear.Column;
import com.github.jxen.svg.model.Ellipse;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import com.github.jxen.svg.transform.TransformDecomposition;
import com.github.jxen.svg.transform.TranslateFirstDecomposer;
import java.util.Arrays;
import java.util.List;

class EllipseTransformApplier extends TransformApplier<Ellipse> {

  @Override
  void transform(Ellipse ellipse, Transform transform) {
    TranslateFirstDecomposer decomposer = new TranslateFirstDecomposer();
    TransformDecomposition decomposition = decomposer.decompose(transform);
    Transform toApply = decomposition.translate();
    List<Transform> rest = Arrays.asList(decomposition.rotate(), decomposition.skewX(), decomposition.scale());
    Column v = toApply.toMatrix().multiply(Column.of(ellipse.getCx().getValue(), ellipse.getCy().getValue(), 1));
    ellipse.setCx(ellipse.getCx().to(Point.from(v).getX()));
    ellipse.setCy(ellipse.getCy().to(Point.from(v).getY()));
    ellipse.setTransform(rest);
  }
}
