package com.github.jxen.svg.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class QuadraticBezierSegTest {

  @Test
  void testAbs() {
    PathSeg seg = PathSegType.Q_ABS.create(Arrays.asList(1.0, 1.0, 2.0, 1.0), new Point(2, 1));
    Transform transform = Transform.of(0.5f, 0, 0, 0.5f, 1, 1);
    PathSeg transformed = seg.transform(transform, new Point(0, 1), new Point(2, 2));
    assertEquals(PathSegType.Q_ABS, transformed.getType());
    assertEquals("1.5,1.5 2,1.5", transformed.toString());
  }

  @Test
  void testRel() {
    PathSeg seg = PathSegType.T_REL.create(Arrays.asList(2.0, 1.0), new Point(2, 1));
    Transform transform = Transform.of(0.5f, 0, 0, 0.5f, 1, 1);
    PathSeg transformed = seg.transform(transform, new Point(0, 1), new Point(2, 2));
    assertEquals(PathSegType.T_REL, transformed.getType());
    assertEquals("0,0", transformed.toString());
  }

  @Test
  void testToAbsCase1() {
    PathSeg seg = PathSegType.Q_ABS.create(Arrays.asList(1.0, 1.0, 2.0, 1.0), new Point(2, 1));
    assertSame(seg, seg.toAbs(new Point(0, 0)));
  }

  @Test
  void testToAbsCase2() {
    PathSeg seg = PathSegType.Q_REL.create(Arrays.asList(1.0, 1.0, 2.0, 1.0), new Point(2, 1));
    assertEquals("1,1 2,1", seg.toAbs(new Point(0, 0)).toString());
  }

  @Test
  void testToAbsCase3() {
    PathSeg seg = PathSegType.T_ABS.create(Arrays.asList(2.0, 1.0), new Point(2, 1));
    assertSame(seg, seg.toAbs(new Point(0, 0)));
  }

  @Test
  void testToAbsCase4() {
    PathSeg seg = PathSegType.T_REL.create(Arrays.asList(2.0, 1.0), new Point(2, 1));
    assertEquals("2,1", seg.toAbs(new Point(0, 0)).toString());
  }

  @Test
  void testToRelCase1() {
    PathSeg seg = PathSegType.Q_REL.create(Arrays.asList(1.0, 1.0, 2.0, 1.0), new Point(2, 1));
    assertSame(seg, seg.toRel(new Point(0, 0)));
  }

  @Test
  void testToRelCase2() {
    PathSeg seg = PathSegType.Q_ABS.create(Arrays.asList(1.0, 1.0, 2.0, 1.0), new Point(2, 1));
    assertEquals("1,1 2,1", seg.toRel(new Point(0, 0)).toString());
  }

  @Test
  void testToRelCase3() {
    PathSeg seg = PathSegType.T_REL.create(Arrays.asList(2.0, 1.0), new Point(2, 1));
    assertSame(seg, seg.toRel(new Point(0, 0)));
  }

  @Test
  void testToRelCase4() {
    PathSeg seg = PathSegType.T_ABS.create(Arrays.asList(2.0, 1.0), new Point(2, 1));
    assertEquals("2,1", seg.toRel(new Point(0, 0)).toString());
  }
}
