package com.github.jxen.svg.model.path;

import com.github.jxen.svg.model.geometry.Point;
import java.util.Iterator;
import java.util.List;

/**
 * {@code PathSegType} class represents segment type of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum PathSegType {

	/**
	 * "A" segment.
	 */
	A_ABS("A", 7) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			return ArcSeg.create(A_ABS, values, ref);
		}
	},

	/**
	 * "a" segment.
	 */
	A_REL("a", 7) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			return ArcSeg.create(A_REL, values, ref);
		}
	},

	/**
	 * "C" segment.
	 */
	C_ABS("C", 6) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Iterator<Double> it = values.iterator();
			Point point1 = new Point(it.next(), it.next());
			Point point2 = new Point(it.next(), it.next());
			Point point = new Point(it.next(), it.next());
			return new CubicBezierSeg(C_ABS, point1, point2, point, point);
		}
	},

	/**
	 * "c" segment.
	 */
	C_REL("c", 6) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Iterator<Double> it = values.iterator();
			Point point1 = new Point(it.next(), it.next());
			Point point2 = new Point(it.next(), it.next());
			Point point = new Point(it.next(), it.next());
			return new CubicBezierSeg(C_REL, point1, point2, point, point.toAbs(ref));
		}
	},

	/**
	 * "H" segment.
	 */
	H_ABS("H", 1) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), ref.getY());
			return new LineToSeg(H_ABS, point, point);
		}
	},

	/**
	 * "h" segment.
	 */
	H_REL("h", 1) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), 0);
			return new LineToSeg(H_REL, point, point.toAbs(ref));
		}
	},

	/**
	 * "L" segment.
	 */
	L_ABS("L", 2) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), values.get(1));
			return new LineToSeg(L_ABS, point, point);
		}
	},

	/**
	 * "l" segment.
	 */
	L_REL("l", 2) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), values.get(1));
			return new LineToSeg(L_REL, point, point.toAbs(ref));
		}
	},

	/**
	 * "M" segment.
	 */
	M_ABS("M", 2, L_ABS.symbol) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), values.get(1));
			return new MoveToSeg(M_ABS, point, point);
		}
	},

	/**
	 * "m" segment.
	 */
	M_REL("m", 2, L_REL.symbol) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), values.get(1));
			return new MoveToSeg(M_REL, point, point.toAbs(ref));
		}
	},

	/**
	 * "Q" segment.
	 */
	Q_ABS("Q", 4) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Iterator<Double> it = values.iterator();
			Point point1 = new Point(it.next(), it.next());
			Point point = new Point(it.next(), it.next());
			return new QuadraticBezierSeg(Q_ABS, point1, point, point);
		}
	},

	/**
	 * "q" segment.
	 */
	Q_REL("q", 4) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Iterator<Double> it = values.iterator();
			Point point1 = new Point(it.next(), it.next());
			Point point = new Point(it.next(), it.next());
			return new QuadraticBezierSeg(Q_REL, point1, point, point.toAbs(ref));
		}
	},

	/**
	 * "S" segment.
	 */
	S_ABS("S", 4) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Iterator<Double> it = values.iterator();
			Point point2 = new Point(it.next(), it.next());
			Point point = new Point(it.next(), it.next());
			return new CubicBezierSeg(S_ABS, ref, point2, point, point);
		}
	},

	/**
	 * "s" segment.
	 */
	S_REL("s", 4) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Iterator<Double> it = values.iterator();
			Point point2 = new Point(it.next(), it.next());
			Point point = new Point(it.next(), it.next());
			return new CubicBezierSeg(S_REL, ref, point2, point, point.toAbs(ref));
		}
	},

	/**
	 * "T" segment.
	 */
	T_ABS("T", 2) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), values.get(1));
			return new QuadraticBezierSeg(T_ABS, ref, point, point);
		}
	},

	/**
	 * "t" segment.
	 */
	T_REL("t", 2) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(values.get(0), values.get(1));
			return new QuadraticBezierSeg(T_REL, ref, point, point.toAbs(ref));
		}
	},

	/**
	 * "V" segment.
	 */
	V_ABS("V", 1) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(ref.getX(), values.get(0));
			return new LineToSeg(V_ABS, point, point);
		}
	},

	/**
	 * "v" segment.
	 */
	V_REL("v", 1) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			Point point = new Point(0, values.get(0));
			return new LineToSeg(V_REL, point, point.toAbs(ref));
		}
	},

	/**
	 * "Z" segment.
	 */
	Z_ABS("Z", 0) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			return new ClosePathSeg(Z_ABS, ref);
		}
	},

	/**
	 * "z" segment.
	 */
	Z_REL("z", 0) {
		@Override
		public PathSeg create(List<Double> values, Point ref) {
			return new ClosePathSeg(Z_REL, ref);
		}
	};

	private final String symbol;
	private final int count;
	private final String next;
	private final boolean relative;

	PathSegType(String symbol, int count, String next) {
		this.symbol = symbol;
		this.count = count;
		this.next = next;
		relative = Character.isLowerCase(symbol.charAt(0));
	}

	PathSegType(String symbol, int count) {
		this(symbol, count, symbol);
	}

	/**
	 * Creates path segment using given data.
	 *
	 * @param values parsed values
	 * @param ref    reference point
	 * @return created {@code PathSeg}
	 */
	public abstract PathSeg create(List<Double> values, Point ref);

	/**
	 * @return {@code true} if it is relative path segment
	 */
	public final boolean isRelative() {
		return relative;
	}

	/**
	 * @return the next
	 */
	public PathSegType next() {
		return of(next);
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param seg  segment
	 * @param type type
	 * @return string representation
	 */
	public String toString(PathSeg seg, PathSegType type) {
		return String.format("%s%s", getSymbol(type), seg);
	}

	/**
	 * Looks for type by given symbol.
	 *
	 * @param symbol command symbol
	 * @return type found
	 */
	public static PathSegType of(String symbol) {
		for (PathSegType type : values()) {
			if (type.symbol.equals(symbol)) {
				return type;
			}
		}
		return null;
	}

	String getSymbol(PathSegType previousType) {
		final String empty = "";
		if (this == previousType) {
			return empty;
		}
		return previousType != null && symbol.equals(previousType.next) ? empty : symbol;
	}
}
