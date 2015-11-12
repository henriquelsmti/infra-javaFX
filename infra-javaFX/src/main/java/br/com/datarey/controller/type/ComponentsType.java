package br.com.datarey.controller.type;

import java.lang.reflect.Field;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import jfxtras.labs.scene.control.BeanPathAdapter;
import br.com.datarey.dataBind.DataBind;
import br.com.datarey.util.UtilDataBind;

@SuppressWarnings("rawtypes")
public enum ComponentsType {
	
	CHECK_BOX(CheckBox.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	CHOICE_BOX(ChoiceBox.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	COLOR_PICKER(ColorPicker.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	COMBO_BOX(ComboBox.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	DATE_PICKER(DatePicker.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	IMAGE_VIEW(ImageView.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	LIST_VIEW(ListView.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	PASSWORD_FIELD(PasswordField.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	PROGRESS_BAR(ProgressBar.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	RADIO_BUTTON(RadioButton.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	SLIDER(Slider.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	TEXT_AREA(TextArea.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	},
	
	TEXT_FIELD(TextField.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			TextField input;
			try {
				input = (TextField) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.textProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}
	},
	TABLE_VIEW(TableView.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			// TODO Auto-generated method stub
			
		}
	};

	private Class<?> clazz;
	
	private ComponentsType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return this.clazz;
	}
	public abstract void binder(Field field, @SuppressWarnings("rawtypes") BeanPathAdapter adapter, DataBind dataBind, Object value);
	
}
