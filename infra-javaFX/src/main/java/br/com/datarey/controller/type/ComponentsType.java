package br.com.datarey.controller.type;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public enum ComponentsType {
	
	CHECK_BOX(CheckBox.class),
	CHOICE_BOX(ChoiceBox.class),
	COLOR_PICKER(ColorPicker.class),
	COMBO_BOX(ComboBox.class),
	DATE_PICKER(DatePicker.class),
	IMAGE_VIEW(ImageView.class),
	LIST_VIEW(ListView.class),
	PASSWORD_FIELD(PasswordField.class),
	PROGRESS_BAR(ProgressBar.class),
	RADIO_BUTTON(RadioButton.class),
	SLIDER(Slider.class),
	TEXT_AREA(TextArea.class),
	TABLE_VIEW(TableView.class);

	private Class<?> clazz;
	
	private ComponentsType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return this.clazz;
	}
	
}
