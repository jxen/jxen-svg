package com.github.jxen.svg.optimizer;

import com.github.jxen.math.linear.Column;
import com.github.jxen.svg.model.Rect;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import com.github.jxen.svg.transform.TransformDecomposition;
import com.github.jxen.svg.transform.TranslateFirstDecomposer;
import java.util.Arrays;

class RectTransformApplier extends TransformApplier<Rect> {

  @Override
  void transform(Rect rect, Transform transform) {
    TranslateFirstDecomposer decomposer = new TranslateFirstDecomposer();
    TransformDecomposition decomposition = decomposer.decompose(transform);
    Transform toApply = decomposition.scale().multiply(decomposition.translate());
    Column v = toApply.toMatrix().multiply(Column.of(rect.getX().getValue(), rect.getY().getValue(), 1));
    rect.setX(rect.getX().to(Point.from(v).getX()));
    rect.setY(rect.getY().to(Point.from(v).getY()));
    v = toApply.toMatrix().multiply(Column.of(rect.getWidth().getValue(), rect.getHeight().getValue(), 0));
    rect.setWidth(rect.getWidth().to(Point.from(v).getX()));
    if (rect.getWidth().getValue() < 0) {
      rect.setX(rect.getX().plus(rect.getWidth()));
      rect.setWidth(rect.getWidth().negate());
    }
    rect.setHeight(rect.getHeight().to(Point.from(v).getY()));
    if (rect.getHeight().getValue() < 0) {
      rect.setY(rect.getY().plus(rect.getHeight()));
      rect.setHeight(rect.getHeight().negate());
    }
    if (rect.getRx() != null && rect.getRy() != null) {
      v = toApply.toMatrix().multiply(Column.of(rect.getRx().getValue(), rect.getRy().getValue(), 0));
      rect.setRx(rect.getRx().to(Math.abs(Point.from(v).getX())));
      rect.setRy(rect.getRy().to(Math.abs(Point.from(v).getY())));
    }
    rect.setTransform(Arrays.asList(decomposition.rotate(), decomposition.skewX()));
  }
}
