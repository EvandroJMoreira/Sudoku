package ui.custom.input;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Space;
import service.EventListener;
import service.EventEnum;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.PLAIN;

public class NumberText extends JTextField implements EventListener {
    private static final long serialVersionUID = 1L;
    private final Space space;

    public NumberText(final Space space) {
        this.space = space;
        setPreferredSize(new Dimension(50, 50));
        setFont(new Font("Arial", PLAIN, 20));
        setHorizontalAlignment(CENTER);
        setDocument(new NumberTextLimit());
        setEnabled(!space.isFixed());
        
        if (space.isFixed() && space.getActual() != null) {
            setText(space.getActual().toString());
        }
        
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSpace();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSpace();
            }

            private void updateSpace() {
                try {
                    int value = getText().isEmpty() ? null : Integer.parseInt(getText());
                    space.setActual(value);
                } catch (NumberFormatException e) {
                    space.setActual(null);
                }
            }
        });
    }

    @Override
    public void update(EventEnum eventType) {
        if (eventType == EventEnum.CLEAR_SPACE && isEnabled()) {
            setText("");
            space.setActual(null);
        }
    }
}