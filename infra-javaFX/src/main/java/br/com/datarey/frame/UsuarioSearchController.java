package br.com.datarey.frame;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import br.com.datarey.frame.dialog.ItemTipoPesquisa;
import br.com.datarey.model.Usuario;
import br.com.datarey.service.UsuarioService;

public class UsuarioSearchController extends BaseIdentificadorSearchController<Usuario, UsuarioService> {

    @Override
    protected void init() {
        super.init();
        iniciarColuas();
        getChoiceBox().getItems().add(new ItemTipoPesquisa("Codigo", "codigo", Predicate.LIKE));
    }
    
    @SuppressWarnings("unchecked")
    private void iniciarColuas(){
        TableColumn<Usuario, String> colunaLogin = new TableColumn<>("Login");
        colunaLogin.setPrefWidth(250);
        colunaLogin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));
        getTableView().getColumns().addAll(colunaLogin);
    }
}
