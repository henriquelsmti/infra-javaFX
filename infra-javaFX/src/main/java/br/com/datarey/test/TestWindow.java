package br.com.datarey.test;

import br.com.datarey.frame.base.BaseWindow;

public class TestWindow extends BaseWindow {

    public TestWindow() {
        super(TestWindow.class.getResource("testWindow.fxml"));
        setHeight(300);
        setWidth(400);
        setTitle("Test");
    }

}
