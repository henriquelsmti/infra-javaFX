package br.com.datarey.frame;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.datarey.frame.dialog.BaseSearchPopUp;
import br.com.datarey.model.Usuario;
import br.com.datarey.service.BaseService;
import br.com.datarey.service.UsuarioService;

public class UsuarioSearchPopUp extends BaseSearchPopUp<Usuario> {

    @Inject
    private UsuarioService usurioService;
    
    @Inject
    private UsuarioSearchController usuarioSearchController;
    
    public UsuarioSearchPopUp() {
        super("Pesquisar usuario");
    }
    
    @PostConstruct
    private void postConstruct(){
        setBaseController(usuarioSearchController);
    }
    
    @Override
    protected BaseService<Usuario> getBaseService() {
        return usurioService;
    }

}
