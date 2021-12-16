package com.github.jxen.svg.parser;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.type.ViewBox;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@code ViewBoxParser} class is parser of {@link ViewBox} objects.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class ViewBoxParser implements Parser<ViewBox> {

  @Override
  public ViewBox parse(String value) {
    Analyzer analyzer = new Analyzer();
    new Splitter(value, analyzer).split();
    final int size = 4;
    if (analyzer.values.size() != size) {
      throw new SvgException("Unparsable ViewBox: " + value);
    }
    Iterator<Double> it = analyzer.values.iterator();
    double x = it.next();
    double y = it.next();
    double width = it.next();
    double height = it.next();
    return new ViewBox(x, y, width, height);
  }

  private static class Analyzer implements Consumer<String> {

    private final List<Double> values = new ArrayList<>();

    @Override
    public void accept(String item) {
      values.add(Double.parseDouble(item));
    }
  }
}
