package br.com.datarey.component.input;

import java.lang.reflect.ParameterizedType;

import javafx.geometry.Pos;
import org.apache.log4j.Logger;

import br.com.datarey.context.Context;
import br.com.datarey.frame.BaseIdentificadorSearchPopUp;
import br.com.datarey.model.Identificador;
import br.com.datarey.service.IdentificadorService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public abstract class IdentificadorInput<E extends Identificador, S extends IdentificadorService<E>, P extends BaseIdentificadorSearchPopUp<E, ?>> extends HBox
        implements CustomInput<E> {

    private S service;

    private P search;
    
    private final ObjectProperty<E> valueProperty = new SimpleObjectProperty<E>();

    private TextField textField;
    private LongInput longInput;
    private Button button;

    public IdentificadorInput() {
        super();
        this.setPrefWidth(200);
        this.setAlignment(Pos.CENTER);
        textField = new TextField();
        longInput = new LongInput();
        button = new Button("");
        button.setPrefWidth(30);
        double fracao = ((this.getPrefWidth() - 30) / 100);
        longInput.setPrefWidth(fracao * 40);
        textField.setPrefWidth(fracao * 60);
        textField.setEditable(false);
        
        
        
        valueProperty.addListener((listener) ->{
            longInput.getValueProperty().set(valueProperty.get().getCodigo());
            textField.setText(valueProperty.get().getNome());
        });

        this.widthProperty().addListener((listener) ->{
            double frac = ((this.getPrefWidth() - 30) / 100);
            longInput.setPrefWidth(frac * 40);
            textField.setPrefWidth(frac * 60);
        });
        
        init();
        
        this.getChildren().addAll(longInput, textField, button);

    }
    
    @SuppressWarnings("unchecked")
    private void init(){
        try{
            Image image = new Image(getClass().getResourceAsStream("../../img/icon-search.png"), 17, 17, true, true);
            button.setGraphic(new ImageView(image));
            Class<S> serviceClass = (Class<S>) ((ParameterizedType) 
                    getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            
            Class<P> searchClass = (Class<P>) ((ParameterizedType) 
                    getClass().getGenericSuperclass()).getActualTypeArguments()[2];
            
            service = Context.getBean(serviceClass);
            search = Context.getBean(searchClass);
            
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
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ObjectProperty<E> getValueProperty() {
        return this.valueProperty;
    }

}
