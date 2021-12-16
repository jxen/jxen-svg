package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.Defs;
import com.github.jxen.svg.model.Desc;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.G;
import com.github.jxen.svg.model.Metadata;
import com.github.jxen.svg.model.Title;
import com.github.jxen.svg.style.Style;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class RedundantVisitor implements SvgVisitor {

  private final Set<Element<?>> toRemove = new HashSet<>();

  @Override
  public void visit(Defs defs) {
    if (defs.isEmpty()) {
      toRemove.add(defs);
    }
    process(defs);
  }

  @Override
  public void visit(Desc desc) {
    toRemove.add(desc);
  }

  @Override
  public void visit(G g) {
    if (g.getId() == null && Style.isEmpty(g.getStyle()) && g.getTransform() == null) {
      toRemove.add(g);
    }
    process(g);
  }

  @Override
  public void visit(Metadata metadata) {
    toRemove.add(metadata);
  }

  @Override
  public void visit(Title title) {
    toRemove.add(title);
  }

  @Override
  public void process(Element<?> element) {
    element.stream().forEach(e -> e.accept(this));
    List<Element<?>> list = new ArrayList<>();
    element.stream().filter(toRemove::contains).forEach(list::add);
    for (Element<?> e : list) {
      element.replace(e, e.stream().collect(Collectors.toList()));
    }
  }
}
