package com.github.jxen.svg.annotation;

import com.github.jxen.svg.model.type.ValueType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Attr} annotation is used to mark fields for which values is read from XML attributes.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attr {

  /**
   * Provides attribute name.
   *
   * @return attribute name
   */
  String name();

  /**
   * Provides attribute namespace.
   *
   * @return attribute namespace
   */
  String namespace() default SvgNamespaces.SVG_NAMESPACE;

  /**
   * Provides attribute type.
   *
   * @return attribute type
   */
  ValueType type();
}
