package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class RectangleBaseTool extends Tool {
	protected float x1, x2, y1, y2;
	public RectangleBaseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void touchStart(MotionEvent event) {
		if (preview)
		{
			this.x1 = event.getX(); // Store the start positions
			this.y1 = event.getY();
			this.x2 = event.getX();
			this.y2 = event.getY();	
			toolbox.getDrawingView().invalidate();
		}
	}

	@Override
	public void touchEnd(MotionEvent event) {
		if (preview)
		{
			toolbox.currentTool.setPreview(false); // Set the preview to false because the user is done drawing
			addToDrawing();
			toolbox.getDrawingView().invalidate();
		}

	}

	@Override
	public void touchMove(MotionEvent event) {
		if (preview)
		{
			this.x2 = event.getX(); // update the position of where to draw the shape to with the mouse cordinates
			this.y2 = event.getY();
			toolbox.getDrawingView().invalidate();
		}

	}
	public abstract void drawPreview(Canvas canvas);
	public abstract void addToDrawing();

}
