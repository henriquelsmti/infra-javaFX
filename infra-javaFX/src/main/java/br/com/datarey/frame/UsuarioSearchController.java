package br.com.datarey.frame;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import br.com.datarey.frame.dialog.BaseSearchController;
import br.com.datarey.frame.dialog.ItemTipoPesquisa;
import br.com.datarey.frame.dialog.ItemTipoPesquisaConverter;
import br.com.datarey.model.Usuario;
import br.com.datarey.service.UsuarioService;
import br.com.datarey.service.type.Regra;

public class UsuarioSearchController extends BaseIdentificadorSearchController<Usuario, UsuarioService> {

    @Override
    protected void init() {
        super.init();
        iniciarColuas();
        getChoiceBox().getItems().add(new ItemTipoPesquisa("Codigo", "codigo", Regra.IGUAL));
        getChoiceBox().getItems().add(new ItemTipoPesquisa("Nome", "nome", Regra.CONTEM));
        getChoiceBox().getSelectionModel().select( getChoiceBox().getItems().get(0));
        getChoiceBox().setConverter(new ItemTipoPesquisaConverter());
    }
    
    @SuppressWarnings("unchecked")
    private void iniciarColuas(){
        TableColumn<Usuario, String> colunaCodigo = new TableColumn<>("Codigo");
        TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Usuario, String> colunaLogin = new TableColumn<>("Login");
        colunaNome.setPrefWidth(250);
        colunaLogin.setPrefWidth(250);
        colunaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo().toString()));
        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaLogin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));
        getTableView().getColumns().addAll(colunaCodigo, colunaNome, colunaLogin);
    }
}
