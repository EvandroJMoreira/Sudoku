package ui.custom.panel;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ui.custom.input.NumberText;

import java.awt.Dimension;
import java.util.List;

import static java.awt.Color.black;

public class SudokuSector extends JPanel {

	private static final long serialVersionUID = 1L;

	public SudokuSector(final List<NumberText> textFields){
        var dimension = new Dimension(170, 170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(black, 2, true));
        this.setVisible(true);
        textFields.forEach(this::add);
    }

}