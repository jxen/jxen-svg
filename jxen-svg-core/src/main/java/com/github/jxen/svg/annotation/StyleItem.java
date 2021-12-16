package com.github.jxen.svg.annotation;

import com.github.jxen.svg.style.StyleItemType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code StyleItem} annotation is used to mark fields which can be treated as part of style.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StyleItem {

  /**
   * Provides name.
   *
   * @return style item name
   */
  String name();

  /**
   * Provides type.
   *
   * @return style item type
   */
  StyleItemType type();
}
