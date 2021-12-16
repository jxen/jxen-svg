package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;

/**
 * {@code G} class represents SVG g element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("g")
public final class G extends Element<G> {

  /**
   * Initializes element.
   */
  public G() {
    super(G.class);
  }

  @Override
  public void accept(SvgVisitor t) {
    t.visit(this);
  }
}
