import com.cuerposcelestes.view.CuerposCelestes;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuerposCelestes.CuerposCelestesView frame = new CuerposCelestes.CuerposCelestesView();
            frame.setVisible(true);
        });
    }
}