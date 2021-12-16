package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.type.ValueType;
import com.github.jxen.svg.style.Style;
import com.github.jxen.svg.transform.Transform;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Desc} class represents abstract SVG element.
 *
 * @author Denis Murashev
 *
 * @param <T> concrete element type
 *
 * @since SVG Core 0.1
 */
public abstract class Element<T extends Element<T>> implements Consumer<SvgVisitor> {

  private static final Logger LOG = LogManager.getLogger(Element.class);

  private final Constructor<T> constructor;

  @Attr(name = "id", type = ValueType.STRING)
  private String id;

  @Attr(name = "style", type = ValueType.STYLE)
  private Style style;

  @Attr(name = "transform", type = ValueType.TRANSFORM)
  private List<Transform> transform;

  private Element<?> parent;
  private final List<Element<?>> elements = new ArrayList<>();

  private Style effective;

  /**
   * Initializes element.
   *
   * @param type class type
   */
  protected Element(Class<T> type) {
    try {
      this.constructor = type.getConstructor();
    } catch (NoSuchMethodException e) {
      throw new SvgException(e);
    }
  }

  /**
   * Provides the value.
   *
   * @return the id
   */
  public final String getId() {
    return id;
  }

  /**
   * Sets the value.
   *
   * @param id the id to set
   */
  public final void setId(String id) {
    this.id = id;
  }

  /**
   * Provides the value.
   *
   * @return the style
   */
  public final Style getStyle() {
    return style;
  }

  /**
   * Sets the value.
   *
   * @param style the style to set
   */
  public final void setStyle(Style style) {
    this.style = style;
  }

  /**
   * Provides the value.
   *
   * @return the transform
   */
  public final List<Transform> getTransform() {
    return transform;
  }

  /**
   * Sets the value.
   *
   * @param transform the transform to set
   */
  public final void setTransform(List<Transform> transform) {
    this.transform = transform;
  }

  /**
   * Provides the value.
   *
   * @return the parent
   */
  public final Element<?> getParent() {
    return parent;
  }

  /**
   * Sets the value.
   *
   * @param parent the parent to set
   */
  public final void setParent(Element<?> parent) {
    this.parent = parent;
  }

  /**
   * Checks if there is no children.
   *
   * @return {@code true} if there is no sub elements
   */
  public final boolean isEmpty() {
    return elements.isEmpty();
  }

  /**
   * Provides stream of elements.
   *
   * @return the stream of elements
   */
  public final Stream<Element<?>> stream() {
    return elements.stream();
  }

  /**
   * Adds element.
   *
   * @param element the element to add
   */
  public final void add(Element<?> element) {
    elements.add(element);
  }

  /**
   * Replaces given child with given children.
   *
   * @param child    child to be replaced
   * @param children children to be replacement
   */
  public final void replace(Element<?> child, List<Element<?>> children) {
    int index = elements.indexOf(child);
    if (index != -1) {
      elements.remove(index);
      elements.addAll(index, children);
    }
  }

  /**
   * Provides the value.
   *
   * @return the effective
   */
  public final Style getEffective() {
    return effective;
  }

  /**
   * Sets the value.
   *
   * @param effective the effective to set
   */
  public final void setEffective(Style effective) {
    this.effective = effective;
  }

  /**
   * Provides copy.
   *
   * @return copy of the element
   */
  public final T copy() {
    try {
      T copy = constructor.newInstance();
      Class<?> type = constructor.getDeclaringClass();
      while (type != Object.class) {
        for (Field field : type.getDeclaredFields()) {
          if (field.getAnnotation(Attr.class) != null) {
            copyField(copy, field);
          }
        }
        type = type.getSuperclass();
      }
      for (Element<?> e : elements) {
        copy.add(e.copy());
      }
      return copy;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new SvgException(e);
    }
  }

  private void copyField(T copy, Field field) {
    AccessController.doPrivileged((PrivilegedAction<?>) () -> {
      try {
        field.setAccessible(true);
        Object value = field.get(this);
        field.set(copy, value);
      } catch (IllegalAccessException e) {
        LOG.error("Error copying field value", e);
      }
      return null;
    });
  }
}
