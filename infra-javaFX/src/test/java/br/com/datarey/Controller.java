package br.com.datarey;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import jfxtras.labs.scene.control.BeanPathAdapter;
import br.com.datarey.entity.Usuario;

public class Controller {

	@FXML
	private Button botao;
	@FXML
	private TextField input;

	@FXML
	private TableView<String> dataGrid1;
	
	@FXML
	private TableColumn<String, String> nome1;
	
	@FXML
	private TableView<String> dataGrid2;
	
	@FXML
	private TableColumn<String, String> nome2;
	
	
	private BeanPathAdapter<Usuario> adapter;

	@FXML
	private TextField input1;

	private Usuario usuario;

	@FXML
	public void event() {
		dataGrid1.getItems().remove(dataGrid1.getSelectionModel().getSelectedItem());
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		
		alert.setHeaderText(adapter.getBean().getLista().size() + "");
		alert.showAndWait();
	}

	@FXML
	private void initialize() {
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
		
		
		
		
		
		
		
		adapter = new BeanPathAdapter<Usuario>(usuario);
		
		
		
		adapter.bindBidirectional("nome", input.textProperty());
		adapter.bindBidirectional("nome", input1.textProperty());
		adapter.bindContentBidirectional("lista", null, String.class, dataGrid1.getItems(), String.class,  null, null);
		adapter.bindContentBidirectional("lista", null, String.class, dataGrid2.getItems(), String.class,  null, null);
		
		
	}
}
