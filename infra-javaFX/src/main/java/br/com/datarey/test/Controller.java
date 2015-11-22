package br.com.datarey.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.datarey.controller.AbstractController;
import br.com.datarey.dataBind.DataBind;
import br.com.datarey.test.entity.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class Controller extends AbstractController {

    @Inject
    private TestWindow testWindow;

    @FXML
    private Button botao;

    @FXML
    @DataBind(mappedBy = "usuario.nome")
    private TextField input;

    @FXML
    @DataBind(mappedBy = "aa")
    private TextField input1;

    @FXML
    @DataBind(mappedBy = "usuario.lista", type = String.class, typeField = String.class)
    private TableView<String> dataGrid1;

    @FXML
    private TableColumn<String, String> nome1;

    @FXML
    @DataBind(mappedBy = "usuarios", type = Usuario.class, typeField = String.class)
    private TableView<Usuario> dataGrid2;

    @FXML
    private TableColumn<Usuario, String> nome2;

    @FXML
    @DataBind(mappedBy = "usuario.data")
    private DatePicker dataPick;

    @DataBind(mappedBy = "nome")
    @FXML
    private Label label;

    private StringProperty nome = new SimpleStringProperty();

    private String aa;

    private LocalDate data = LocalDate.now();

    private List<Usuario> usuarios = new ArrayList<Usuario>();

    private Usuario usuario;

    private CheckBox checkBox;
    private ChoiceBox<Usuario> choiceBox;
    private ColorPicker colorPicher;
    private ComboBox<Usuario> comboBox;
    private DatePicker datePicker;
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

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(usuario.getData().toString());
        alert.showAndWait();
        nome.set("aa");

        aa = "teste";
        testWindow.show();

    }

    @FXML
    protected void init() {
        addEnterNavigator(input);
        addEnterNavigator(input1);
        usuario = new Usuario();
        usuario.setNome("usuario 1");
        usuarios.add(usuario);

        usuario = new Usuario();
        usuario.setNome("usuario 2");
        usuarios.add(usuario);

        usuario = new Usuario();
        usuario.setNome("usuario 3");
        usuarios.add(usuario);

        usuario = new Usuario();
        usuario.setNome("usuario 4");
        usuarios.add(usuario);

        usuario = new Usuario();
        usuario.setNome("usuario 5");
        usuarios.add(usuario);

        usuario = new Usuario();
        usuario.setNome("usuario 6");
        usuarios.add(usuario);

        usuario = new Usuario();
        usuario.setLista(new ArrayList<String>());
        usuario.getLista().add("1");
        usuario.getLista().add("2");
        usuario.getLista().add("3");
        usuario.getLista().add("4");
        usuario.getLista().add("5");
        usuario.getLista().add("6");

        nome1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        nome2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));

        aa = "";
        /* adapter = new BeanPathAdapter<Usuario>(usuario); */

        /*
         * adapter.bindBidirectional("nome", input.textProperty());
         * adapter.bindContentBidirectional("lista", null, String.class,
         * dataGrid1.getItems(), String.class, null, null);
         * adapter.bindContentBidirectional("lista", null, String.class,
         * dataGrid2.getItems(), String.class, null, null);
         */

        /*
         * checkBox.selectedProperty();
         * 
         * choiceBox.getItems(); choiceBox.valueProperty();
         * 
         * colorPicher.valueProperty();
         * 
         * comboBox.valueProperty(); comboBox.getItems();
         * 
         * datePicker.valueProperty();
         * 
         * listView.getItems();
         * 
         * passwordField.textProperty();
         * 
         * progressBar.progressProperty();
         * 
         * radioButton.selectedProperty();
         * 
         * slider.valueProperty();
         * 
         * textArea.textProperty();
         * 
         * input.textProperty();
         * 
         * tableView.getItems();
         */

    }
}
