package hackatton.dao;

import hackatton.model.Curso;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CursoDao extends Dao implements DaoInterface {


    @Override
    public boolean salvar(Object entity) {
        try {
            var curso = (Curso) entity;
            String sqlInsert = "INSERT INTO curso(nome) VALUES (?)";
            var ps = getConnection().prepareStatement(sqlInsert);
            ps.setString(1, curso.getNome());
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar curso: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Object entity) {
        try {
            var curso = (Curso) entity;
            String sqlUpdate = "UPDATE curso SET nome = ? WHERE id = ?";
            var ps = getConnection().prepareStatement(sqlUpdate);
            ps.setString(1, curso.getNome());
            ps.setLong(2, curso.getId());
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> cursos = new ArrayList<>();
        try {
            var rs = getConnection().prepareStatement("SELECT * FROM curso").executeQuery();
            while (rs.next()) {
                var curso = new Curso(
                        rs.getLong("id"),
                        rs.getString("nome")
                );
                cursos.add(curso);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        }
        return cursos;
    }

    @Override
    public Object buscarPorId(Long id) {
        try {
            String sql = "SELECT * FROM curso WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return new Curso(rs.getLong("id"), rs.getString("nome"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao buscar curso por ID: " + e.getMessage());
        }
        return null;
    }


    public boolean deletar(Long id) {
        try {
            var ps = getConnection().prepareStatement("DELETE FROM curso WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao deletar curso: " + e.getMessage());
            return false;
        }
    }
}
