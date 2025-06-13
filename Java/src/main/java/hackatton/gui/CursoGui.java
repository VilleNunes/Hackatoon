package hackatton.gui;

import hackatton.model.Curso;
import hackatton.service.CursoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CursoGui extends JFrame implements GuiUtil {

    private final CursoService cursoService;
    private Long cursoSelecionadoId = null;

    private JLabel jlNome;
    private JTextField tfNome;

    private JButton btSalvar;
    private JButton btListar;
    private JButton btExcluir;
    private JButton btEditar;

    private JTable tabela;

    public CursoGui(CursoService cursoService) {
        this.cursoService = cursoService;
        setTitle("Cadastro de Cursos");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getContentPane().add(montarPainelEntrada(), BorderLayout.NORTH);
        getContentPane().add(montarPainelSaida(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel montarPainelEntrada() {
        var jPanel = new JPanel(new GridBagLayout());

        jlNome = new JLabel("Nome:");
        tfNome = new JTextField(20);

        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(this::salvarCurso);

        btListar = new JButton("Listar Cursos");
        btListar.addActionListener(this::listarCursos);

        btExcluir = new JButton("Excluir");
        btExcluir.addActionListener(this::deletarCurso);

        btEditar = new JButton("Editar");
        btEditar.addActionListener(this::editarCurso);

        jPanel.add(jlNome, montarGrid(0, 0));
        jPanel.add(tfNome, montarGrid(1, 0));
        jPanel.add(btSalvar, montarGrid(0, 1));
        jPanel.add(btListar, montarGrid(1, 1));
        jPanel.add(btExcluir, montarGrid(2, 1));
        jPanel.add(btEditar, montarGrid(3, 1));

        return jPanel;
    }

    private JPanel montarPainelSaida() {
        var jPanel = new JPanel(new BorderLayout());

        tabela = new JTable();
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);

        tabela.setModel(carregarCursos());
        tabela.getSelectionModel().addListSelectionListener(this::selecionarCurso);

        var scrollPanel = new JScrollPane(tabela);
        jPanel.add(scrollPanel, BorderLayout.CENTER);

        return jPanel;
    }

    private void selecionarCurso(ListSelectionEvent e) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            cursoSelecionadoId = (Long) tabela.getValueAt(selectedRow, 0);
            tfNome.setText((String) tabela.getValueAt(selectedRow, 1));
        }
    }



    private void salvarCurso(ActionEvent e) {
        if (tfNome.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios", "Campos vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        var curso = new Curso(cursoSelecionadoId, tfNome.getText());
        boolean sucesso = cursoService.salvar(curso);

        if (sucesso) {
            limparCampos();
            JOptionPane.showMessageDialog(this, "Curso salvo com sucesso!");
            tabela.setModel(carregarCursos());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar curso.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarCurso(ActionEvent e) {
        if (cursoSelecionadoId != null) {
            boolean sucesso = cursoService.deletar(cursoSelecionadoId);
            if (sucesso) {
                limparCampos();
                JOptionPane.showMessageDialog(this, "Curso excluído com sucesso!");
                tabela.setModel(carregarCursos());
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir curso.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um curso na tabela para excluir.");
        }
    }
    private void editarCurso(ActionEvent e) {
        if (tfNome.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios", "Campos vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }



        if (cursoSelecionadoId == null) {
            JOptionPane.showMessageDialog(this, "Selecione um curso na tabela para editar.");
            return;
        }

        Curso curso = new Curso(cursoSelecionadoId, tfNome.getText());

        boolean sucesso = cursoService.atualizarBD(curso);

        if (sucesso) {
            limparCampos();
            JOptionPane.showMessageDialog(this, "Curso editado com sucesso!");
            tabela.setModel(carregarCursos());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao editar curso.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void listarCursos(ActionEvent e) {
        JOptionPane.showMessageDialog(this, cursoService.listarTexto());
    }

    private DefaultTableModel carregarCursos() {
        var model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");

        cursoService.listar().forEach(curso -> {
            model.addRow(new Object[]{
                    curso.getId(),
                    curso.getNome()
            });
        });

        return model;
    }



        private void limparCampos() {
        cursoSelecionadoId = null;
        tfNome.setText("");
    }
}
