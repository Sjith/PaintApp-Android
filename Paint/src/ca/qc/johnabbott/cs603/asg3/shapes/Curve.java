package ca.qc.johnabbott.cs603.asg3.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Curve extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Path path;
	public Curve(int strokeColor, int fillColor, int lineWidth, Path path) {
		super(strokeColor, fillColor, lineWidth);
		this.path = path;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Paint paint, Canvas canvas) {
		
		paint.setPathEffect(null);
		
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(strokeColor);
		paint.setStrokeWidth(strokeWidth);
		paint.setStrokeCap(Paint.Cap.ROUND);
		canvas.drawPath(path, paint);

	}

}
