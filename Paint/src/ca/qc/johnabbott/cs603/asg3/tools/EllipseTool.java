package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Ellipse;
import android.graphics.Canvas;
import android.graphics.RectF;


public class EllipseTool extends RectangleBaseTool {

	public EllipseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		canvas.drawOval(new RectF(x1-x2, y1-y2, x1+x2, y1+y2), toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() { // Adds an ellipse to the array list of shapes
		toolbox.getDrawingView().addShapesToList(new Ellipse(x1, y1, x2, y2, toolbox.getFillColor(), toolbox.getStrokeWidth(), toolbox.getStrokeColor()));
	}

}
