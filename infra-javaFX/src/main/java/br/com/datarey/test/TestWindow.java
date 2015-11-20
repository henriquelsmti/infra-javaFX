package br.com.datarey.test;

import br.com.datarey.frame.BaseWindow;

public class TestWindow extends BaseWindow {

	public TestWindow() {
		super(TestWindow.class.getResource("testWindow.fxml").toString().replace("file:/", ""));
		setHeight(300);
		setWidth(400);
		setTitle("Test");
	}

}
