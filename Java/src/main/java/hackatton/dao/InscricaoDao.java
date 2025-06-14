package hackatton.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscricaoDao extends Dao {

    public List<String[]> listarAlunosPorEvento(Long idEvento) {
        List<String[]> lista = new ArrayList<>();
        String sql = """
    SELECT u.nome AS nomeAluno, u.email AS emailAluno,
    e.titulo AS nomeEvento, p.nome AS nomePalestrante
    FROM inscricao i
    JOIN users u ON u.id = i.user_id
    JOIN evento e ON e.id = i.evento_id
    LEFT JOIN palestrante p ON e.id_palestrante = p.id
    WHERE i.evento_id = ?
""";


        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, idEvento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nomeAluno = rs.getString("nomeAluno");
                String emailAluno = rs.getString("emailAluno");
                String nomeEvento = rs.getString("nomeEvento");
                String nomePalestrante = rs.getString("nomePalestrante");
                lista.add(new String[]{nomeAluno, emailAluno, nomeEvento,  nomePalestrante});
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos por evento: " + e.getMessage());
        }

        return lista;
    }
    }