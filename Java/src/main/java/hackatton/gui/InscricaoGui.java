package hackatton.gui;

import hackatton.model.Evento;
import hackatton.service.InscricaoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class InscricaoGui extends JFrame {

    private final InscricaoService inscricaoService = new InscricaoService();
    private JComboBox<Evento> comboEventos;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public InscricaoGui() {
        setTitle("Alunos Inscritos por Evento");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        montarTela();
        carregarEventosNoCombo();
        carregarTabelaComEventoSelecionado();
        setVisible(true);
    }

    private void montarTela() {
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;

        add(new JLabel("Selecione o Evento:"), c);

        comboEventos = new JComboBox<>();
        comboEventos.setPreferredSize(new Dimension(400, 25));
        c.gridx = 1;
        add(comboEventos, c);

        comboEventos.addActionListener(this::eventoSelecionado);

        modeloTabela = new DefaultTableModel(new String[]{
                "Nome do Aluno", "Email", "Evento", "Palestrante"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabela);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scroll, c);
    }

    private void carregarEventosNoCombo() {
        List<Evento> eventos = inscricaoService.listarEventos();
        comboEventos.removeAllItems();
        for (Evento e : eventos) {
            comboEventos.addItem(e);
        }
    }

    private void carregarTabelaComEventoSelecionado() {
        Evento eventoSelecionado = (Evento) comboEventos.getSelectedItem();
        modeloTabela.setRowCount(0);
        if (eventoSelecionado != null) {
            List<String[]> alunos = inscricaoService.listarAlunosPorEvento(eventoSelecionado.getId());
            for (String[] linha : alunos) {
                modeloTabela.addRow(linha);
            }
        }
    }

    private void eventoSelecionado(ActionEvent e) {
        carregarTabelaComEventoSelecionado();
    }
}