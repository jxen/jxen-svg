package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.path.PathSeg;
import com.github.jxen.svg.model.type.ValueType;
import java.util.List;

/**
 * {@code Path} class represents SVG path element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("path")
public final class Path extends Element<Path> {

  @Attr(name = "d", type = ValueType.PATH)
  private List<PathSeg> d;

  /**
   * Initializes element.
   */
  public Path() {
    super(Path.class);
  }

  /**
   * Provides the value.
   *
   * @return the d
   */
  public List<PathSeg> getD() {
    return d;
  }

  /**
   * Sets the value.
   *
   * @param d the d to set
   */
  public void setD(List<PathSeg> d) {
    this.d = d;
  }

  @Override
  public void accept(SvgVisitor t) {
    t.visit(this);
  }
}
