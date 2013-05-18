package ca.qc.johnabbott.cs603.asg3;

import ca.qc.johnabbott.cs603.asg3.shapes.Shape;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
	
	private Paint paint;
	private ToolBox toolBox;
	private Picture picture = new Picture();
	public Picture getPicture() {
		return picture;
	}

	public void addShapesToList(Shape shape) {
		picture.addShapesToList(shape);
	}
	
	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setAntiAlias(true);
		toolBox = new ToolBox(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		for ( Shape s : picture.getShapesList())
			s.draw(paint, canvas);
		if (toolBox.currentTool != null && toolBox.currentTool.hasPreview()) // Checks if the user is currently drawing a shape
			toolBox.currentTool.drawPreview(canvas);

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) { // Calls the methods based on what shape is selected, and what event was triggered.
		if (toolBox.currentTool != null)
		{
			switch(event.getActionMasked()) 
			{
				case MotionEvent.ACTION_DOWN:
					toolBox.currentTool.touchStart(event);
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					toolBox.currentTool.touchStart(event);
					break;	
				case MotionEvent.ACTION_UP:
					toolBox.currentTool.touchEnd(event);
					break;
				case MotionEvent.ACTION_POINTER_UP:
					toolBox.currentTool.touchEnd(event);
					break;	
				default:
					toolBox.currentTool.touchMove(event);
			}
			return true;
		}
		return false;
	}
	
	public void erase() {
		picture.erase();
	}

	public ToolBox getToolBox() {
		return toolBox;
	}
	
	
}
