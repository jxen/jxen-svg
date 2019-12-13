package com.github.jxen.svg.api;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.jxen.svg.model.G;
import com.github.jxen.svg.model.Path;
import com.github.jxen.svg.model.Svg;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.model.path.PathSegType;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.ViewBox;
import com.github.jxen.svg.style.Style;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

class SvgWriterTest {

	@Test
	void testWrite() {
		Svg svg = new Svg();
		svg.setWidth(new Length(100));
		svg.setHeight(new Length(100));
		svg.setVersion("1.0");
		svg.setViewBox(new ViewBox(0, 0, 100, 100));
		G g = new G();
		svg.add(g);
		g.setId("g1");
		Path path = new Path();
		g.add(path);
		path.setD(asList(PathSegType.M_ABS.create(asList(0.0, 0.0), new Point(0,0)),
				PathSegType.L_ABS.create(asList(10.0, 10.0), new Point(0,0))));
		path.setStyle(Style.getDefault());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new SvgWriter().write(svg, out);
		assertNotEquals(0, out.size());
	}
}
