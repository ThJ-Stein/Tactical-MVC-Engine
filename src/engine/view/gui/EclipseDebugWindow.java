package engine.view.gui;

import engine.view.Canvas;

public class EclipseDebugWindow extends ViewFrame {
	
	private Canvas canvas = new Canvas();
	
	{
		getContentPane().add(canvas);
	}

	public EclipseDebugWindow(String windowTitle) {
		super(windowTitle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void println(String debugOutput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Canvas getCanvas() {
		return canvas;
	}

}
