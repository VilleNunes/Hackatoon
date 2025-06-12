package hackatton;


import hackatton.gui.EventoGui;
import hackatton.gui.TelaPrincipal;
import hackatton.service.EventoService;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(Main::executar);
    }

    private static void executar() {
        var gui = new TelaPrincipal();
        gui.setVisible(true);
    }
}