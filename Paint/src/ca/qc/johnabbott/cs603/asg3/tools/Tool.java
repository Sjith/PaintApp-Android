package ca.qc.johnabbott.cs603.asg3.tools;


import ca.qc.johnabbott.cs603.asg3.ToolBox;
import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class Tool {

	protected ToolBox toolbox;
	protected ToolName name;
	protected boolean preview;
	
	public void setPreview(boolean preview) {
		this.preview = preview;
	}

	public Tool(ToolBox toolbox, ToolName name) {
		super();
		this.toolbox = toolbox;
		this.name = name;
		this.preview = false;
	}

	public ToolName getName() {
		return name;
	}
	
	public boolean hasPreview() {
		return preview;
	}
	
	public abstract void touchStart(MotionEvent event);
	public abstract void touchEnd(MotionEvent event);
	public abstract void touchMove(MotionEvent event);
	
	public abstract void drawPreview(Canvas canvas);
	public abstract void addToDrawing();
}
