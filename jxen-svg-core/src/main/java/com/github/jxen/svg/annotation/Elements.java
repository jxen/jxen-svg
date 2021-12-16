package com.github.jxen.svg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Elements} annotation is used to mark package and specify all supported classes representing SVG elements.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Elements {

  /**
   * Provides value.
   *
   * @return tags of supported elements
   */
  Class<?>[] value();
}
