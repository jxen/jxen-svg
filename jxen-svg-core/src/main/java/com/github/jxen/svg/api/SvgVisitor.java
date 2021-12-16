package com.github.jxen.svg.api;

import com.github.jxen.svg.model.Circle;
import com.github.jxen.svg.model.Defs;
import com.github.jxen.svg.model.Desc;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.Ellipse;
import com.github.jxen.svg.model.G;
import com.github.jxen.svg.model.Line;
import com.github.jxen.svg.model.Metadata;
import com.github.jxen.svg.model.Path;
import com.github.jxen.svg.model.Polygon;
import com.github.jxen.svg.model.Rect;
import com.github.jxen.svg.model.Svg;
import com.github.jxen.svg.model.Title;
import com.github.jxen.svg.model.Use;

/**
 * {@code SvgVisitor} interface is general contract for implementing Visitor design pattern
 * that can be applied to the SVG elements.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public interface SvgVisitor {

  /**
   * Visits the element.
   *
   * @param circle circle
   */
  default void visit(Circle circle) {
    process(circle);
  }

  /**
   * Visits the element.
   *
   * @param defs defs
   */
  default void visit(Defs defs) {
    process(defs);
  }

  /**
   * Visits the element.
   *
   * @param desc desc
   */
  default void visit(Desc desc) {
    process(desc);
  }

  /**
   * Visits the element.
   *
   * @param ellipse ellipse
   */
  default void visit(Ellipse ellipse) {
    process(ellipse);
  }

  /**
   * Visits the element.
   *
   * @param g g
   */
  default void visit(G g) {
    process(g);
  }

  /**
   * Visits the element.
   *
   * @param line line
   */
  default void visit(Line line) {
    process(line);
  }

  /**
   * Visits the element.
   *
   * @param metadata metadata
   */
  default void visit(Metadata metadata) {
    process(metadata);
  }

  /**
   * Visits the element.
   *
   * @param path path
   */
  default void visit(Path path) {
    process(path);
  }

  /**
   * Visits the element.
   *
   * @param polygon polygon
   */
  default void visit(Polygon polygon) {
    process(polygon);
  }

  /**
   * Visits the element.
   *
   * @param rect rect
   */
  default void visit(Rect rect) {
    process(rect);
  }

  /**
   * Visits the element.
   *
   * @param svg svg
   */
  default void visit(Svg svg) {
    process(svg);
  }

  /**
   * Visits the element.
   *
   * @param title title
   */
  default void visit(Title title) {
    process(title);
  }

  /**
   * Visits the element.
   *
   * @param use use
   */
  default void visit(Use use) {
    process(use);
  }

  /**
   * Default processing of element.
   *
   * @param element element to be processed
   */
  default void process(Element<?> element) {
    element.stream().forEach(e -> e.accept(this));
  }
}
