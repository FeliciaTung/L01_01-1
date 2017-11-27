package ui.components;

import javax.swing.*;
import java.awt.*;

public class ScrollPanel extends JScrollPane {

    public static int WINDOW_WIDTH = 800;

    public ScrollPanel(JPanel contentPanel, int scrollPanelHeight) {
        super(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setPreferredSize(new Dimension(WINDOW_WIDTH, scrollPanelHeight));
        setBorder(BorderFactory.createEmptyBorder());
        getVerticalScrollBar().setOpaque(true);

    }


}
