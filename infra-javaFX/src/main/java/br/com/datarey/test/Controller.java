package br.com.datarey.test;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import jfxtras.labs.scene.control.BeanPathAdapter;
import br.com.datarey.controller.BaseController;
import br.com.datarey.dataBind.Bindable;
import br.com.datarey.dataBind.DataBind;
import br.com.datarey.test.entity.Usuario;

@Bindable
public class Controller extends BaseController{

	@FXML
	private Button botao;
	
	@FXML
	@DataBind(mappedBy="usuario.nome")
	private TextField input;
	
	
	@FXML
	@DataBind(mappedBy="aa")
	private TextField input1;

	@FXML
	@DataBind(mappedBy="usuario.lista", type=String.class, typeField=String.class)
	private TableView<String> dataGrid1;
	
	@FXML
	private TableColumn<String, String> nome1;
	
	@FXML
	@DataBind(mappedBy="usuario.lista", type=String.class, typeField=String.class)
	private TableView<String> dataGrid2;
	
	
	@FXML
	private TableColumn<String, String> nome2;
	

	private String aa;
	
	private Usuario usuario;
	
	private CheckBox checkBox;
	private ChoiceBox<Usuario> choiceBox;
	private ColorPicker colorPicher;
	private ComboBox<Usuario> comboBox;
	private DatePicker datePicker;
	private HTMLEditor hTMLEditor;
	private ImageView imageView;
	private ListView<Usuario> listView;
	private PasswordField passwordField;
	private ProgressBar progressBar;
	private RadioButton radioButton;
	private Slider slider;
	private TextArea textArea;
	private ToggleButton toggleButton;
	private TableView<String> tableView;
	
	@FXML
	public void event() {
		
		dataGrid1.getItems().remove(dataGrid1.getSelectionModel().getSelectedItem());
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		
		
	    alert.setHeaderText(aa);
		alert.showAndWait();
		
		alert = new Alert(AlertType.CONFIRMATION);
		
		
		
		alert.setHeaderText(usuario.getNome());
		alert.showAndWait();
		
		aa = "teste";
		
	}

	@FXML
	protected void init() {
		usuario = new Usuario();
		usuario.setLista(new ArrayList<String>());
		usuario.getLista().add("1");
		usuario.getLista().add("2");
		usuario.getLista().add("3");
		usuario.getLista().add("4");
		usuario.getLista().add("5");
		usuario.getLista().add("6");
		
		
		nome1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
		nome2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
		
		aa = "";
		/*adapter = new BeanPathAdapter<Usuario>(usuario);*/
		
		
		
		/*adapter.bindBidirectional("nome", input.textProperty());
		adapter.bindContentBidirectional("lista", null, String.class, dataGrid1.getItems(), String.class,  null, null);
		adapter.bindContentBidirectional("lista", null, String.class, dataGrid2.getItems(), String.class,  null, null);
		*/
		
		/*checkBox.selectedProperty();
		
		choiceBox.getItems();
		choiceBox.valueProperty();
		
		colorPicher.valueProperty();
		
		comboBox.valueProperty();
		comboBox.getItems();
		
		datePicker.valueProperty();
		
		listView.getItems();
		
		passwordField.textProperty();
		
		progressBar.progressProperty();
		
		radioButton.selectedProperty();
		
		slider.valueProperty();
		
		textArea.textProperty();
		
		input.textProperty();
		
		tableView.getItems();*/
		
	}
}
