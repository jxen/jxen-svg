package com.github.jxen.svg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Tag} annotation is used to mark classes representing SVG elements.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag {

  /**
   * Provides value.
   *
   * @return element name
   */
  String value();

  /**
   * Provides namespace.
   *
   * @return element namespace
   */
  String namespace() default SvgNamespaces.SVG_NAMESPACE;
}
