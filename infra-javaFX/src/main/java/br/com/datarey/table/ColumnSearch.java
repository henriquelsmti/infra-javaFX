package br.com.datarey.table;

import br.com.datarey.component.input.CustomInput;
import br.com.datarey.exception.BaseException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ColumnSearch<T> {
    private String title;
    private String field;
    private Callback<TableColumn.CellDataFeatures<T, String>, String> cellData;
    private Node graphic;
    private final ObjectProperty<Object>  valueSearch = new SimpleObjectProperty<>();
    private boolean visible;
    private double  prefWidth = 100;

    public  Callback<TableColumn.CellDataFeatures<T, String>, String> getCellData() {
        return cellData;
    }

    public void setCellData(Callback<TableColumn.CellDataFeatures<T, String>, String> cellData) {
        this.cellData = cellData;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Node getGraphic() {
        return graphic;
    }

    @SuppressWarnings("unchecked")
    public void setGraphic(Node graphic) {
        if(graphic instanceof CustomInput){
            CustomInput customInput = (CustomInput) graphic;
            valueSearch.bindBidirectional(customInput.getValueProperty());
        }

        this.graphic = graphic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getValueSearch() {
        return valueSearch.get();
    }

    public ObjectProperty<Object> valueSearchProperty() {
        return valueSearch;
    }

    public void setValueSearch(Object valueSearch) {
        this.valueSearch.set(valueSearch);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getPrefWidth() {
        return prefWidth;
    }

    public void setPrefWidth(double prefWidth) {
        this.prefWidth = prefWidth;
    }
}
