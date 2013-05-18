package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Rectangle;
import android.graphics.Canvas;

public class RectangleTool extends RectangleBaseTool {

	public RectangleTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawPreview(Canvas canvas) {
			canvas.drawRect(x1, y1, x2, y2, toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		toolbox.getDrawingView().addShapesToList(new Rectangle(x1, y1, x2, y2, toolbox.getFillColor(), toolbox.getStrokeWidth(), toolbox.getStrokeColor()));

	}

}
