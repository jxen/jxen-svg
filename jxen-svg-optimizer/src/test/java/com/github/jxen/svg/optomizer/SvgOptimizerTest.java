package com.github.jxen.svg.optomizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.github.jxen.svg.api.SvgReader;
import com.github.jxen.svg.api.SvgWriter;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.optimizer.SvgOptimizer;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class SvgOptimizerTest {

	private final SvgReader reader = new SvgReader();
	private final SvgWriter writer = new SvgWriter();
	private final SvgOptimizer optimizer = new SvgOptimizer();

	@TestFactory
	DynamicTest[] testOptimize() {
		return new DynamicTest[] {
				testFile("test1", "expected1"),
				testFile("test2", "expected2"),
		};
	}

	private DynamicTest testFile(String in, String out) {
		return dynamicTest(in, () -> {
			Element<?> element = reader.read(getInput(in));
			optimizer.optimize(element);
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			writer.write(element, o);
			String actual = o.toString().trim();
			String expected = new BufferedReader(new InputStreamReader(getInput(out)))
					.lines()
					.collect(Collectors.joining("\n"));
			assertEquals(expected, actual);
		});
	}

	private InputStream getInput(String name) {
		return SvgOptimizerTest.class.getResourceAsStream(String.format("/%s.svg", name));
	}
}
