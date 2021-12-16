package com.github.jxen.svg.parser;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.model.path.PathSeg;
import com.github.jxen.svg.model.path.PathSegType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code PathParser} class is parser of {@link PathSeg} objects.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public final class PathParser implements Parser<List<PathSeg>> {

  @Override
  public List<PathSeg> parse(String value) {
    Analyzer analyzer = new Analyzer();
    new PathSplitter(value, analyzer).split();
    return analyzer.getResult();
  }

  private static final class PathSplitter extends Splitter {

    private PathSplitter(String value, Consumer<String> consumer) {
      super(value, consumer);
    }

    @Override
    public boolean isCommand(char ch) {
      return "AaCcHhLlMmQqSsTtVvZz".indexOf(ch) >= 0;
    }
  }

  private static class Analyzer implements Consumer<String> {

    private final List<PathSeg> segments = new ArrayList<>();
    private List<Double> values = new ArrayList<>();
    private PathSegType type;
    private Point ref = new Point(0, 0);
    private boolean inProgress;

    @Override
    public void accept(String item) {
      if (inProgress) {
        values.add(Double.parseDouble(item));
      } else {
        PathSegType t = PathSegType.of(item);
        if (Objects.nonNull(t)) {
          type = t;
        } else {
          if (type == null) {
            throw new SvgException("No command specified in the path");
          }
          type = type.next();
          values = new ArrayList<>();
          values.add(Double.parseDouble(item));
        }
        inProgress = true;
      }
      if (values.size() == type.getCount()) {
        inProgress = false;
        PathSeg seg = type.create(values, ref);
        segments.add(seg);
        ref = seg.getEndPoint();
        values = new ArrayList<>();
      }
    }

    private List<PathSeg> getResult() {
      return segments;
    }
  }
}
