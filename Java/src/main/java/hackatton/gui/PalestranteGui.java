package hackatton.gui;

import hackatton.model.Palestrante;
import hackatton.service.PalestranteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class PalestranteGui extends JFrame {
    private JTextField nomeField;
    private JTextArea miniCurriculoArea;
    private JLabel imagemLabel;
    private File imagemSelecionada;
    private JTable tabela;
    private PalestranteService service = new PalestranteService();

    public PalestranteGui() {
        setTitle("Cadastro de Palestrantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        nomeField = new JTextField();
        miniCurriculoArea = new JTextArea(3, 20);
        imagemLabel = new JLabel("Nenhuma imagem selecionada");

        JButton selecionarImagemBtn = new JButton("Selecionar Imagem");
        selecionarImagemBtn.addActionListener(this::selecionarImagem);

        JButton salvarBtn = new JButton("Salvar");
        salvarBtn.addActionListener(this::salvarPalestrante);

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Mini Currículo:"));
        formPanel.add(new JScrollPane(miniCurriculoArea));
        formPanel.add(new JLabel("Imagem:"));
        formPanel.add(imagemLabel);
        formPanel.add(selecionarImagemBtn);
        formPanel.add(salvarBtn);

        panel.add(formPanel, BorderLayout.NORTH);

        tabela = new JTable();
        atualizarTabela();
        panel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void selecionarImagem(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagemSelecionada = fileChooser.getSelectedFile();
            imagemLabel.setText(imagemSelecionada.getName());
        }
    }

    private void salvarPalestrante(ActionEvent e) {
        String nome = nomeField.getText();
        String miniCurriculo = miniCurriculoArea.getText();
        String nomeImagem = imagemSelecionada != null ? imagemSelecionada.getName() : "";

        // Salvar a imagem na pasta Imagens
        if (imagemSelecionada != null) {
            File destino = new File("Imagens/" + imagemSelecionada.getName());
            imagemSelecionada.renameTo(destino);
        }

        Palestrante palestrante = new Palestrante();
        palestrante.setNome(nome);
        palestrante.setMinicurriculo(miniCurriculo);
        palestrante.setFoto(nomeImagem);

        if (service.salvar(palestrante)) {
            JOptionPane.showMessageDialog(this, "Palestrante salvo com sucesso!");
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar palestrante.");
        }
    }

    private void atualizarTabela() {
        List<Palestrante> palestrantes = service.listar();
        String[] colunas = {"ID", "Nome", "Mini Currículo", "Imagem"};
        Object[][] dados = new Object[palestrantes.size()][4];

        for (int i = 0; i < palestrantes.size(); i++) {
            Palestrante p = palestrantes.get(i);
            dados[i][0] = p.getId();
            dados[i][1] = p.getNome();
            dados[i][2] = p.getMiniCurriculo();
            dados[i][3] = p.getFoto();
        }

        tabela.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PalestranteGui::new);
    }
}
