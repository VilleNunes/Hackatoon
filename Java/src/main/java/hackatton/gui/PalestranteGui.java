package hackatton.gui;

import hackatton.model.Palestrante;
import hackatton.service.PalestranteService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PalestranteGui extends JFrame implements GuiUtil {

    private final PalestranteService palestranteService;
    private Long palestranteSelecionadoId = null;
    private File imagemSelecionada = null;

    private JTextField tfNome;
    private JTextArea taMiniCurriculo;
    private JLabel jlImagemPath;

    private JButton btSelecionarImagem;
    private JButton btSalvar;
    private JButton btListar;
    private JButton btExcluir;
    private JButton btEditar;
    private JButton btLimpar;

    private JTable tabela;

    public PalestranteGui() {
        this.palestranteService = new PalestranteService();
        setTitle("Cadastro de Palestrantes");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getContentPane().add(montarPainelEntrada(), BorderLayout.NORTH);
        getContentPane().add(montarPainelSaida(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel montarPainelEntrada() {
        var jPanel = new JPanel(new GridBagLayout());


        jPanel.add(new JLabel("Nome:"), montarGrid(0, 0, 3, 1));
        tfNome = new JTextField(25);
        jPanel.add(tfNome, montarGrid(1, 0, 3, 1));


        jPanel.add(new JLabel("Mini Currículo:"), montarGrid(0, 1, 3, 1));
        taMiniCurriculo = new JTextArea(5, 30);
        taMiniCurriculo.setLineWrap(true);
        taMiniCurriculo.setWrapStyleWord(true);
        JScrollPane spMiniCurriculo = new JScrollPane(taMiniCurriculo);
        jPanel.add(spMiniCurriculo, montarGrid(1, 1, 3, 1));


        jPanel.add(new JLabel("Imagem:"), montarGrid(0, 2, 3, 1));
        jlImagemPath = new JLabel("Nenhuma imagem selecionada");
        jPanel.add(jlImagemPath, montarGrid(1, 2, 2, 1));

        btSelecionarImagem = new JButton("Selecionar Imagem");
        btSelecionarImagem.addActionListener(this::selecionarImagem);
        jPanel.add(btSelecionarImagem, montarGrid(3, 2, 3, 1));


        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(this::salvarPalestrante);
        jPanel.add(btSalvar, montarGrid(0, 3, 3, 1));

        btListar = new JButton("Listar Palestrantes");
        btListar.addActionListener(this::listarPalestrantes);
        jPanel.add(btListar, montarGrid(1, 3, 3, 1));

        btExcluir = new JButton("Excluir");
        btExcluir.addActionListener(this::deletarPalestrante);
        jPanel.add(btExcluir, montarGrid(2, 3, 3, 1));

        btEditar = new JButton("Editar");
        btEditar.addActionListener(this::editarPalestrante);
        jPanel.add(btEditar, montarGrid(3, 3, 3, 1));

        btLimpar = new JButton("Limpar Campos");
        btLimpar.addActionListener(e -> limparCampos());
        jPanel.add(btLimpar, montarGrid(4, 3, 3, 1));

        return jPanel;
    }

    private JPanel montarPainelSaida() {
        var jPanel = new JPanel(new BorderLayout());

        tabela = new JTable();
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);

        tabela.setModel(carregarPalestrantes());
        tabela.getSelectionModel().addListSelectionListener(this::selecionarPalestrante);

        var scrollPanel = new JScrollPane(tabela);
        jPanel.add(scrollPanel, BorderLayout.CENTER);

        return jPanel;
    }

    private void selecionarImagem(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar Imagem do Palestrante");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagemSelecionada = fileChooser.getSelectedFile();
            jlImagemPath.setText(imagemSelecionada.getName());
        }
    }

    private void selecionarPalestrante(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
            int selectedRow = tabela.getSelectedRow();
            palestranteSelecionadoId = (Long) tabela.getValueAt(selectedRow, 0);
            tfNome.setText((String) tabela.getValueAt(selectedRow, 1));
            taMiniCurriculo.setText((String) tabela.getValueAt(selectedRow, 2));
            String nomeFoto = (String) tabela.getValueAt(selectedRow, 3);
            jlImagemPath.setText(nomeFoto != null && !nomeFoto.isEmpty() ? nomeFoto : "Nenhuma imagem selecionada");
            imagemSelecionada = nomeFoto != null && !nomeFoto.isEmpty() ? new File("Imagens/" + nomeFoto) : null;
        }
    }

    private void salvarPalestrante(ActionEvent e) {
        String nome = tfNome.getText().trim();
        String miniCurriculo = taMiniCurriculo.getText().trim();
        String nomeImagem = "";

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do palestrante não pode ser vazio.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (imagemSelecionada != null) {
            try {
                Path dirImagens = Path.of("Imagens");
                if (!Files.exists(dirImagens)) {
                    Files.createDirectories(dirImagens);
                }
                Path destino = dirImagens.resolve(imagemSelecionada.getName());
                Files.copy(imagemSelecionada.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
                nomeImagem = "imagens/" + imagemSelecionada.getName();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a imagem: " + ex.getMessage(), "Erro de Imagem", JOptionPane.ERROR_MESSAGE);
            }
        }

        Palestrante palestrante = new Palestrante(
                palestranteSelecionadoId,
                nome,
                miniCurriculo,
                nomeImagem
        );

        boolean sucesso = palestranteService.salvar(palestrante);

        if (sucesso) {
            limparCampos();
            JOptionPane.showMessageDialog(this, "Palestrante salvo com sucesso!");
            tabela.setModel(carregarPalestrantes());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar palestrante.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarPalestrante(ActionEvent e) {
        if (palestranteSelecionadoId != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este palestrante?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (imagemSelecionada != null && imagemSelecionada.exists()) {
                    try {
                        Files.deleteIfExists(imagemSelecionada.toPath());
                    } catch (IOException ex) {
                        System.err.println("Erro ao deletar arquivo de imagem: " + ex.getMessage());
                    }
                }

                boolean sucesso = palestranteService.deletar(palestranteSelecionadoId);
                if (sucesso) {
                    limparCampos();
                    JOptionPane.showMessageDialog(this, "Palestrante excluído com sucesso!");
                    tabela.setModel(carregarPalestrantes());
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir palestrante.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um palestrante na tabela para excluir.");
        }
    }

    private void editarPalestrante(ActionEvent e) {
        if (palestranteSelecionadoId == null) {
            JOptionPane.showMessageDialog(this, "Selecione um palestrante na tabela para editar.");
            return;
        }

        String nome = tfNome.getText().trim();
        String miniCurriculo = taMiniCurriculo.getText().trim();
        String nomeImagem = "";

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do palestrante não pode ser vazio.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (imagemSelecionada != null && imagemSelecionada.exists()) {
            try {
                Path dirImagens = Path.of("Imagens");
                if (!Files.exists(dirImagens)) {
                    Files.createDirectories(dirImagens);
                }
                Path destino = dirImagens.resolve(imagemSelecionada.getName());
                Files.copy(imagemSelecionada.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
                nomeImagem = imagemSelecionada.getName();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a nova imagem: " + ex.getMessage(), "Erro de Imagem", JOptionPane.ERROR_MESSAGE);
                nomeImagem = (String) tabela.getValueAt(tabela.getSelectedRow(), 3); // Mantém a antiga
            }
        } else if (jlImagemPath.getText().equals("Nenhuma imagem selecionada") || jlImagemPath.getText().isEmpty()) {
            nomeImagem = "";
        } else {
            nomeImagem = jlImagemPath.getText();
        }


        Palestrante palestrante = new Palestrante(palestranteSelecionadoId, nome, miniCurriculo, nomeImagem);

        boolean sucesso = palestranteService.atualizarBD(palestrante);

        if (sucesso) {
            limparCampos();
            JOptionPane.showMessageDialog(this, "Palestrante editado com sucesso!");
            tabela.setModel(carregarPalestrantes());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao editar palestrante.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarPalestrantes(ActionEvent e) {
        JOptionPane.showMessageDialog(this, palestranteService.listarTexto());
    }

    private DefaultTableModel carregarPalestrantes() {
        var model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Mini Currículo");
        model.addColumn("Foto");

        palestranteService.listar().forEach(p -> {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getMiniCurriculo(),
                    p.getFoto()
            });
        });

        return model;
    }

    private void limparCampos() {
        palestranteSelecionadoId = null;
        tfNome.setText("");
        taMiniCurriculo.setText("");
        jlImagemPath.setText("Nenhuma imagem selecionada");
        imagemSelecionada = null;
        tabela.clearSelection();
    }

}