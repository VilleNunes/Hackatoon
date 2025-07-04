package hackatton.service;

import hackatton.dao.CursoDao;
import hackatton.model.Curso;
import java.util.ArrayList;
import java.util.List;

public class CursoService {

    public boolean salvar(Curso curso) {
        var dao = new CursoDao();
        if (curso.getId() == null) {
            return dao.salvar(curso);
        } else {
            return dao.atualizar(curso);
        }
    }

    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        var dao = new CursoDao();

        dao.listar().forEach(obj -> cursos.add((Curso) obj));
        return cursos;
    }

    public boolean deletar(Long id) {
        var dao = new CursoDao();
        return dao.deletar(id);
    }

    public String listarTexto() {
        var dao = new CursoDao();

        String result = "";
        for (Object curso : dao.listar()) {
            result += "\n" + curso;
        }

        return result;
    }
    public boolean atualizarBD(Curso curso) {
        var dao = new CursoDao();
        return dao.atualizar(curso);
    }

}
