package hackatton.dao;

import hackatton.model.Palestrante;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class PalestranteDao extends Dao implements DaoInterface {

    @Override
    public boolean salvar(Object entity) {
        try {
            var palestrante = (Palestrante) entity;
            String sqlInsert = "INSERT INTO palestrante(nome, minicurriculo, foto) VALUES (?, ?, ?)";
            var ps = getConnection().prepareStatement(sqlInsert);
            ps.setString(1, palestrante.getNome());
            ps.setString(2, palestrante.getMiniCurriculo());
            ps.setString(3, palestrante.getFoto());
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar palestrante: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Object entity) {
        try {
            var palestrante = (Palestrante) entity;
            String sqlUpdate = "UPDATE palestrante SET nome = ?, minicurriculo = ?, foto = ? WHERE id = ?";
            var ps = getConnection().prepareStatement(sqlUpdate);
            ps.setString(1, palestrante.getNome());
            ps.setString(2, palestrante.getMiniCurriculo());
            ps.setString(3, palestrante.getFoto());
            ps.setLong(4, palestrante.getId());
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar palestrante: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> palestrantes = new ArrayList<>();
        try {
            var rs = getConnection().prepareStatement("SELECT * FROM palestrante").executeQuery();
            while (rs.next()) {
                var palestrante = new Palestrante(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("minicurriculo"),
                        rs.getString("foto")
                );
                palestrantes.add(palestrante);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar palestrantes: " + e.getMessage());
        }
        return palestrantes;
    }

    @Override
    public Object buscarPorId(Long id) {
        try {
            String sql = "SELECT * FROM palestrante WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return new Palestrante(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("minicurriculo"),
                        rs.getString("foto")
                );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao buscar palestrante por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean deletar(Long id) {
        try {
            var ps = getConnection().prepareStatement("DELETE FROM palestrante WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao deletar palestrante: " + e.getMessage());
            return false;
        }
    }

    public List<Palestrante> listarPalestrantes() {
        List<Palestrante> palestrantes = new ArrayList<>();
        var dao = new PalestranteDao();

        dao.listar().forEach(obj -> palestrantes.add((Palestrante) obj));
        return palestrantes;
    }
}
