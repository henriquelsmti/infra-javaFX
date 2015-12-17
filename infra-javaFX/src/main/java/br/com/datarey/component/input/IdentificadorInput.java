package br.com.datarey.component.input;

import javax.inject.Inject;

import br.com.datarey.frame.BaseIdentificadorSearchPopUp;
import br.com.datarey.model.Identificador;
import br.com.datarey.service.IdentificadorService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public abstract class IdentificadorInput<E extends Identificador, S extends IdentificadorService<E>, P extends BaseIdentificadorSearchPopUp<E, ?>> extends HBox
        implements CustomInput<E> {

    @Inject
    private S service;

    @Inject
    private P search;
    
    private final ObjectProperty<E> valueProperty = new SimpleObjectProperty<E>();

    private TextField textField;
    private LongInput longInput;
    private Button button;

    public IdentificadorInput() {
        super();
        textField = new TextField();
        longInput = new LongInput();
        button = new Button();
        button.setPrefWidth(30);
        double fracao = ((this.getWidth() - 30) / 100);
        longInput.setPrefWidth(fracao * 40);
        textField.setPrefWidth(fracao * 60);
        textField.setEditable(false);
        
        valueProperty.addListener((listener) ->{
            longInput.getValueProperty().set(valueProperty.get().getCodigo());
            textField.setText(valueProperty.get().getNome());
        });

        longInput.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (longInput.getValueProperty().get() == null || longInput.getValueProperty().get() == 0) {
                    valueProperty.set(search.showAndWait(this.getScene().getWindow()));
                } else {
                    valueProperty.set(service.obterPorCodigo(longInput.getValueProperty().get()));
                    if(valueProperty.isNull().get()){
                        valueProperty.set(search.showAndWait(this.getScene().getWindow()));
                    }
                }
            }
        });
        
        button.setOnAction((event) -> {
            valueProperty.set(search.showAndWait(this.getScene().getWindow()));
        });

    }

    public ObjectProperty<E> getValueProperty() {
        return this.valueProperty;
    }

}
