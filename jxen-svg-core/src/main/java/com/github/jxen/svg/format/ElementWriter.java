package com.github.jxen.svg.format;

import static com.github.jxen.svg.annotation.SvgNamespaces.SVG_NAMESPACE;
import static com.github.jxen.svg.annotation.SvgNamespaces.XLINK_NAMESPACE;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.Element;
import java.lang.reflect.Field;
import java.util.Objects;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * {@code ElementWriter} class is responsible to write single SVG information data.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public final class ElementWriter {

  /**
   * Writes SVG element information.
   *
   * @param element element to be written
   * @param writer  writer
   */
  public void write(Element<?> element, XMLStreamWriter writer) {
    try {
      writer.setDefaultNamespace(SVG_NAMESPACE);
      writer.writeStartDocument();
      newLine(writer);
      write(element, writer, true);
      writer.writeEndDocument();
    } catch (XMLStreamException e) {
      throw new SvgException(e);
    }
  }

  private void write(Element<?> element, XMLStreamWriter writer, boolean writeNamespace) {
    Tag tag = element.getClass().getAnnotation(Tag.class);
    if (tag == null) {
      throw new SvgException("Unsupported class: " + element.getClass());
    }
    try {
      if (element.isEmpty()) {
        writer.writeEmptyElement(tag.namespace(), tag.value());
        if (writeNamespace) {
          writeNamespaces(writer);
        }
        writeAttributes(element, writer);
        newLine(writer);
      } else {
        writer.writeStartElement(tag.namespace(), tag.value());
        if (writeNamespace) {
          writeNamespaces(writer);
        }
        writeAttributes(element, writer);
        newLine(writer);
        element.stream().forEach(e -> write(e, writer, false));
        writer.writeEndElement();
        newLine(writer);
      }
    } catch (XMLStreamException e) {
      throw new SvgException(e);
    }
  }

  private void writeNamespaces(XMLStreamWriter writer) throws XMLStreamException {
    writer.writeDefaultNamespace(SVG_NAMESPACE);
    writer.writeNamespace("xlink", XLINK_NAMESPACE);
  }

  private void writeAttributes(Element<?> element, XMLStreamWriter writer) {
    Class<?> classType = element.getClass();
    processClass(element, classType, writer);
  }

  private void processClass(Element<?> element, Class<?> classType, XMLStreamWriter writer) {
    Class<?> superType = classType.getSuperclass();
    if (superType != Object.class) {
      processClass(element, superType, writer);
    }
    for (Field field : classType.getDeclaredFields()) {
      Attr attr = field.getAnnotation(Attr.class);
      if (attr == null) {
        continue;
      }
      try {
        field.setAccessible(true);
        writeAttribute(attr, field.get(element), writer);
      } catch (IllegalAccessException e) {
        throw new SvgException(e);
      }
    }
  }

  private void writeAttribute(Attr attr, Object value, XMLStreamWriter writer) {
    if (Objects.isNull(value)) {
      return;
    }
    try {
      String formattedValue = attr.type().format(value);
      if (!formattedValue.isEmpty()) {
        writer.writeAttribute(attr.namespace(), attr.name(), formattedValue);
      }
    } catch (XMLStreamException e) {
      throw new SvgException(e);
    }
  }

  private void newLine(XMLStreamWriter writer) throws XMLStreamException {
    writer.writeCharacters("\n");
  }
}
