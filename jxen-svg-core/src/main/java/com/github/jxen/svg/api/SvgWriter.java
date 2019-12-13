package com.github.jxen.svg.api;

import com.github.jxen.svg.format.ElementWriter;
import com.github.jxen.svg.model.Element;
import java.io.OutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * {@code SvgReader} class is responsible for writing SVG data.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class SvgWriter {

	/**
	 * Writes given SVG element to given output stream.
	 *
	 * @param element element
	 * @param out     output stream
	 */
	public void write(Element<?> element, OutputStream out) {
		try {
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter writer = factory.createXMLStreamWriter(out);
			new ElementWriter().write(element, writer);
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			throw new SvgException(e);
		}
	}
}
