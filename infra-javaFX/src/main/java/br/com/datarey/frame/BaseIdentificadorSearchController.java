package br.com.datarey.frame;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import br.com.datarey.frame.dialog.BaseSearchController;
import br.com.datarey.frame.dialog.ItemTipoPesquisa;
import br.com.datarey.frame.dialog.ItemTipoPesquisaConverter;
import br.com.datarey.model.Identificador;
import br.com.datarey.service.IdentificadorService;

public abstract class BaseIdentificadorSearchController<E extends Identificador, S extends IdentificadorService<E>>
        extends BaseSearchController<E, S> {

    @Override
    protected void init() {
        super.init();
        iniciarColuas();
        getChoiceBox().getItems().add(new ItemTipoPesquisa("Codigo", "codigo", Predicate.EQUAL));
        getChoiceBox().getItems().add(new ItemTipoPesquisa("Nome", "nome", Predicate.LIKE));
        getChoiceBox().getSelectionModel().select(getChoiceBox().getItems().get(0));
        getChoiceBox().setConverter(new ItemTipoPesquisaConverter());
    }

    @SuppressWarnings("unchecked")
    private void iniciarColuas() {
        TableColumn<E, String> colunaCodigo = new TableColumn<>("Codigo");
        TableColumn<E, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setPrefWidth(250);
        colunaCodigo
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo().toString()));
        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        getTableView().getColumns().addAll(colunaCodigo, colunaNome);
    }
}
