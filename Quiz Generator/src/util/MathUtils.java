package util;

public class MathUtils {
	
	private MathUtils() {
	}

	public static int min(int value, int min) {
		return value < min ? min : value;
	}
	
	public static int max(int value, int max) {
		return value > max ? max : value;
	}
	
	public static int triangleDistance(int x0, int x1, int y0, int y1) {
		return (int)  Math.sqrt(Math.abs(((x0 - x1) * (x0 - x1)) + ((y0 - y1) * (y0 - y1))));
	}
	
	public static boolean inTheRange( int value, int min, int max ) {
		if((value >= min) && (value <= max)) return true;
		return false;
	}

	public static int clamp(int value, int min, int max) {
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}
	

}
