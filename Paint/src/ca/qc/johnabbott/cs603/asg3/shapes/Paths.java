package ca.qc.johnabbott.cs603.asg3.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Paths extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Path path;
	public Paths(int strokeColor, int fillColor, int lineWidth,Path path) {
		super(strokeColor, fillColor, lineWidth);
		this.path = path;
	}

	@Override
	public void draw(Paint paint, Canvas canvas) {
		// reset any path effect
		paint.setPathEffect(null);
			
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(strokeColor);
		paint.setStrokeWidth(strokeWidth);
		paint.setStrokeCap(Paint.Cap.ROUND);
		canvas.drawPath(path, paint);
	}

}
