package com.github.jxen.svg.api;

import com.github.jxen.svg.annotation.Elements;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.parser.ElementParser;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code SvgReader} class is responsible for reading SVG data.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class SvgReader {

	private static final Logger LOG = LogManager.getLogger(SvgReader.class);

	private static final Set<String> IGNORED_NAMESPACES = Stream
			.of("http://creativecommons.org/ns#",
					"http://purl.org/dc/elements/1.1/",
					"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd",
					"http://web.resource.org/cc/",
					"http://www.inkscape.org/namespaces/inkscape",
					"http://www.w3.org/1999/02/22-rdf-syntax-ns#")
			.collect(Collectors.toSet());

	private static final Map<String, ElementParser> PARSERS = new HashMap<>();

	static {
		Stream.of(Element.class.getPackage().getAnnotation(Elements.class).value()).forEach(SvgReader::processClass);
		//new PackageScanner().scan("com.github.jxen.svg.model", SvgReader::processClass);
	}

	/**
	 * Reads SVG data from given input stream.
	 *
	 * @param input input stream
	 * @return root element model of SVG
	 */
	public Element<?> read(InputStream input) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		Deque<Element<?>> stack = new ArrayDeque<>();
		try {
			XMLEventReader reader = factory.createXMLEventReader(input);
			Element<?> root = null;
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				root = readEvent(stack, root, event);
			}
			return root;
		} catch (XMLStreamException e) {
			throw new SvgException(e);
		}
	}

	private Element<?> readEvent(Deque<Element<?>> stack, Element<?> root, XMLEvent event) {
		if (event.isStartElement()) {
			return readElement(event.asStartElement(), root, stack);
		}
		if (event.isEndElement()) {
			EndElement element = event.asEndElement();
			if (PARSERS.containsKey(element.getName().getLocalPart())) {
				stack.pop();
			}
		} else if (event.isStartDocument()) {
			LOG.debug(() -> "Start document");
		} else if (event.isEndDocument()) {
			LOG.debug(() -> "End document");
		} else if (event.isCharacters()) {
			logValue("Text", event.asCharacters().getData().trim());
		} else if (event.getEventType() == XMLStreamConstants.COMMENT) {
			logValue("Comment", event.toString().trim());
		} else if (event.getEventType() == XMLStreamConstants.DTD) {
			logValue("DTD", event.toString().trim());
		} else {
			LOG.warn(() -> "Unknown event: " + event);
		}
		return root;
	}

	private Element<?> readElement(StartElement element, Element<?> root, Deque<Element<?>> stack) {
		ElementParser parser = PARSERS.get(element.getName().getLocalPart());
		if (Objects.nonNull(parser)) {
			Element<?> e = parser.parse(element);
			if (Objects.isNull(root)) {
				stack.push(e);
				return e;
			}
			stack.getFirst().add(e);
			e.setParent(stack.getFirst());
			stack.push(e);
		} else {
			if (!IGNORED_NAMESPACES.contains(element.getName().getNamespaceURI())) {
				LOG.warn(() -> "Unknown element: " + element);
			}
		}
		return root;
	}

	private void logValue(String name, String text) {
		if (!text.isEmpty()) {
			LOG.debug(() -> name + ": " + text);
		}
	}

	private static void processClass(Class<?> c) {
		try {
			Tag tag = c.getAnnotation(Tag.class);
			if (tag != null && Element.class.isAssignableFrom(c)) {
				@SuppressWarnings("unchecked")
				Constructor<? extends Element<?>> constructor = (Constructor<? extends Element<?>>) c.getConstructor();
				PARSERS.put(tag.value(), new ElementParser(constructor));
			}
		} catch (NoSuchMethodException e) {
			throw new SvgException(e);
		}
	}
}
