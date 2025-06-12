package hackatton.dao;

import java.util.List;

public interface DaoInterface {

    boolean salvar(Object entity);

    boolean atualizar(Object entity);

    List<Object> listar();

    Object buscarPorId(Long id);

    boolean deletarEvento(Long id);
}
