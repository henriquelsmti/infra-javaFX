package br.com.datarey.controller.type;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Collection;

import javafx.beans.property.Property;
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
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.BeanPathAdapter;
import br.com.datarey.dataBind.DataBind;
import br.com.datarey.util.UtilDataBind;

@SuppressWarnings({"rawtypes", "unchecked"})
public enum ComponentsType {
	
	CHECK_BOX(CheckBox.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			CheckBox checkBox;
			try {
				field.setAccessible(true);
				checkBox = (CheckBox) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, checkBox.selectedProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			CheckBox checkBox;
			try {
				field.setAccessible(true);
				checkBox = (CheckBox) field.get(value);
				if(property instanceof Property){
					checkBox.selectedProperty().bindBidirectional((Property)property);;
				}else{
					checkBox.selectedProperty().set((Boolean)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			CheckBox checkBox;
			Object valueProperty;
			try {
				field.setAccessible(true);
				checkBox = (CheckBox) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, checkBox.selectedProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}
	},
	CHOICE_BOX(ChoiceBox.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			ChoiceBox choiceBox;
			try {
				field.setAccessible(true);
				choiceBox = (ChoiceBox) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindContentBidirectional(name, null, dataBind.typeField(), choiceBox.getItems(), dataBind.type(),  null, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			ChoiceBox checkBox;
			try {
				field.setAccessible(true);
				checkBox = (ChoiceBox) field.get(value);
				checkBox.getItems().clear();
				checkBox.getItems().setAll((Collection)property);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
		}
	},
	COLOR_PICKER(ColorPicker.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			ColorPicker input;
			try {
				field.setAccessible(true);
				input = (ColorPicker) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.valueProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			ColorPicker input;
			try {
				field.setAccessible(true);
				input = (ColorPicker) field.get(value);
				if(property instanceof Property){
					input.valueProperty().bindBidirectional((Property)property);;
				}else{
					input.valueProperty().set((Color)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			ColorPicker input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (ColorPicker) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.valueProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	COMBO_BOX(ComboBox.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			ComboBox comboBox;
			try {
				field.setAccessible(true);
				comboBox = (ComboBox) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindContentBidirectional(name, null, dataBind.typeField(), comboBox.getItems(), dataBind.type(),  null, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			ComboBox comboBox;
			try {
				field.setAccessible(true);
				comboBox = (ComboBox) field.get(value);
				comboBox.getItems().clear();
				comboBox.getItems().setAll((Collection)property);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
		}
	},
	DATE_PICKER(DatePicker.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			DatePicker input;
			try {
				field.setAccessible(true);
				input = (DatePicker) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.valueProperty(), LocalDate.class);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			DatePicker input;
			try {
				field.setAccessible(true);
				input = (DatePicker) field.get(value);
				if(property instanceof Property){
					input.valueProperty().bindBidirectional((Property)property);;
				}else{
					input.valueProperty().set((LocalDate)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			DatePicker input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (DatePicker) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.valueProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	
	LIST_VIEW(ListView.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			ListView listView;
			try {
				field.setAccessible(true);
				listView = (ListView) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindContentBidirectional(name, null, dataBind.typeField(), listView.getItems(), dataBind.type(),  null, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			ListView listView;
			try {
				field.setAccessible(true);
				listView = (ListView) field.get(value);
				listView.getItems().clear();
				listView.getItems().setAll((Collection)property);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
		}
	},
	PASSWORD_FIELD(PasswordField.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			PasswordField input;
			try {
				field.setAccessible(true);
				input = (PasswordField) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.textProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			PasswordField input;
			try {
				field.setAccessible(true);
				input = (PasswordField) field.get(value);
				if(property instanceof Property){
					input.textProperty().bindBidirectional((Property)property);;
				}else{
					input.textProperty().set((String)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			PasswordField input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (PasswordField) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.textProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	PROGRESS_BAR(ProgressBar.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			ProgressBar input;
			try {
				field.setAccessible(true);
				input = (ProgressBar) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.progressProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			ProgressBar input;
			try {
				field.setAccessible(true);
				input = (ProgressBar) field.get(value);
				if(property instanceof Property){
					input.progressProperty().bindBidirectional((Property)property);;
				}else{
					input.progressProperty().set((Double)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			ProgressBar input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (ProgressBar) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.progressProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	RADIO_BUTTON(RadioButton.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			RadioButton input;
			try {
				field.setAccessible(true);
				input = (RadioButton) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.selectedProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			RadioButton input;
			try {
				field.setAccessible(true);
				input = (RadioButton) field.get(value);
				if(property instanceof Property){
					input.selectedProperty().bindBidirectional((Property)property);;
				}else{
					input.selectedProperty().set((Boolean)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			RadioButton input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (RadioButton) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.selectedProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	SLIDER(Slider.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			Slider input;
			try {
				field.setAccessible(true);
				input = (Slider) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.valueProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			Slider input;
			try {
				field.setAccessible(true);
				input = (Slider) field.get(value);
				if(property instanceof Property){
					input.valueProperty().bindBidirectional((Property)property);;
				}else{
					input.valueProperty().set((Double)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			Slider input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (Slider) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.valueProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	TEXT_AREA(TextArea.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			TextArea input;
			try {
				field.setAccessible(true);
				input = (TextArea) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.textProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			TextArea input;
			try {
				field.setAccessible(true);
				input = (TextArea) field.get(value);
				if(property instanceof Property){
					input.textProperty().bindBidirectional((Property)property);;
				}else{
					input.textProperty().set((String)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			TextArea input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (TextArea) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.textProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	
	TEXT_FIELD(TextField.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			TextField input;
			try {
				field.setAccessible(true);
				input = (TextField) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindBidirectional(name, input.textProperty());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			TextField input;
			try {
				field.setAccessible(true);
				input = (TextField) field.get(value);
				if(property instanceof Property){
					input.textProperty().bindBidirectional((Property)property);;
				}else{
					input.textProperty().set((String)property);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
			TextField input;
			Object valueProperty;
			try {
				field.setAccessible(true);
				input = (TextField) field.get(value);
				valueProperty = property.get(value);
				if(!(valueProperty instanceof Property)){
					property.set(value, input.textProperty().get());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	},
	TABLE_VIEW(TableView.class) {
		@Override
		public void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value) {
			TableView tableView;
			try {
				field.setAccessible(true);
				tableView = (TableView) field.get(value);
				String name = dataBind.mappedBy().replace(UtilDataBind.getFieldsBeanNameFormated(field) + ".", "");
				adapter.bindContentBidirectional(name, null, dataBind.typeField(), tableView.getItems(), dataBind.type(),  null, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void binder(Field field, Object property, Object value) {
			TableView tableView;
			try {
				field.setAccessible(true);
				tableView = (TableView) field.get(value);
				tableView.getItems().clear();
				tableView.getItems().setAll((Collection)property);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}

		@Override
		public void voewToControler(Field field, Field property, Object value) {
		}

		
	};

	private Class<?> clazz;
	
	private ComponentsType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return this.clazz;
	}
	public abstract void binder(Field field, BeanPathAdapter adapter, DataBind dataBind, Object value);
	public abstract void binder(Field field, Object property, Object value);
	public abstract void voewToControler(Field field, Field property, Object value);
	
}
