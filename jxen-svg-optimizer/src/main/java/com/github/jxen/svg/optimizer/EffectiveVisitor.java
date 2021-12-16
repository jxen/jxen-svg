package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.Defs;
import com.github.jxen.svg.model.Desc;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.Metadata;
import com.github.jxen.svg.model.Title;
import com.github.jxen.svg.style.Style;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class EffectiveVisitor implements SvgVisitor {

  @Override
  public void visit(Defs defs) {
    processDefault(defs);
  }

  @Override
  public void visit(Desc desc) {
    processDefault(desc);
  }

  @Override
  public void visit(Metadata metadata) {
    processDefault(metadata);
  }

  @Override
  public void visit(Title title) {
    processDefault(title);
  }

  private void processDefault(Element<?> element) {
    element.setEffective(Style.getDefault());
    process(element);
    element.setEffective(Style.getDefault());
  }

  @Override
  public void process(Element<?> element) {
    Style effective = element.getEffective();
    Style style = element.getStyle();
    if (Objects.nonNull(style)) {
      Style.names().forEach(n -> {
        Object item = style.get(n);
        if (Objects.nonNull(item) && !item.equals(effective.get(n))) {
          effective.set(n, item);
        }
      });
    }
    element.stream().forEach(c -> {
      c.setEffective(effective.copy());
      c.accept(this);
    });
    Style.names().forEach(n -> processStyleItem(n, element));
  }

  private void processStyleItem(String name, Element<?> element) {
    Map<Object, Integer> map = element.stream()
        .map(e -> e.getEffective().get(name)).collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
    if (map.isEmpty()) {
      return;
    }
    int count = 0;
    Object max = null;
    for (Map.Entry<Object, Integer> e : map.entrySet()) {
      if (e.getValue() > count) {
        count = e.getValue();
        max = e.getKey();
      }
    }
    if (max == null) {
      return;
    }
    Style style = element.getEffective();
    if (2 * count > map.size()) {
      style.set(name, max);
    }
  }
}
