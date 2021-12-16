package com.github.jxen.svg.parser;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.type.ValueType;
import com.github.jxen.svg.style.Style;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ElementParser} class is general parser of SVG element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class ElementParser {

  private static final Logger LOG = LogManager.getLogger(ElementParser.class);

  private static final Set<String> IGNORED_NAMESPACES = Stream
      .of("http://www.inkscape.org/namespaces/inkscape", "http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd")
      .collect(Collectors.toSet());

  private final Constructor<? extends Element<?>> constructor;

  private final Map<String, AttributeHandler> handlers = new HashMap<>();

  /**
   * Initializes with given values.
   *
   * @param constructor object constructor
   */
  public ElementParser(Constructor<? extends Element<?>> constructor) {
    this.constructor = constructor;
    processAttributes(constructor.getDeclaringClass());
  }

  private void processAttributes(Class<?> classType) {
    if (classType != Object.class) {
      processAttributes(classType.getSuperclass());
    }
    for (Field field : classType.getDeclaredFields()) {
      Attr attr = field.getAnnotation(Attr.class);
      if (attr == null) {
        continue;
      }
      handlers.put(attr.name(), (o, a) -> set(o, field, a, attr.type()));
    }
  }

  private void set(Object object, Field field, Attribute attribute, ValueType type) {
    Object value = type.parse(attribute.getValue());
    // TODO Implement value range checking
    try {
      field.setAccessible(true);
      field.set(object, value);
    } catch (IllegalAccessException e) {
      throw new SvgException(e);
    }
  }

  /**
   * Parses given XML element.
   *
   * @param startElement {@link StartElement} to be parsed
   * @return parsed object
   */
  public final Element<?> parse(StartElement startElement) {
    try {
      Element<?> element = constructor.newInstance();
      @SuppressWarnings("unchecked")
      Iterator<Attribute> attributes = startElement.getAttributes();
      while (attributes.hasNext()) {
        Attribute attribute = attributes.next();
        AttributeHandler handler = handlers.get(attribute.getName().getLocalPart());
        if (Objects.nonNull(handler)) {
          handler.handle(element, attribute);
        } else {
          String name = attribute.getName().getLocalPart();
          if (Style.isNameSupported(name)) {
            Style style = Optional.ofNullable(element.getStyle()).orElse(Style.getDefault());
            style.parseItem(name, attribute.getValue());
            element.setStyle(style);
          } else if (!IGNORED_NAMESPACES.contains(attribute.getName().getNamespaceURI())) {
            LOG.warn(() -> "Unknown attribute: " + attribute);
          }
        }
      }
      return element;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new SvgException(e);
    }
  }

  private interface AttributeHandler {
    void handle(Object object, Attribute attribute);
  }
}
