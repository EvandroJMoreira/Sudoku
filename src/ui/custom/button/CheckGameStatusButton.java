package ui.custom.button;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CheckGameStatusButton extends JButton {


	private static final long serialVersionUID = 1L;

	public CheckGameStatusButton(final ActionListener actionListener){
       	
    	this.setText("Verificar jogo");
        this.addActionListener(actionListener);
    }

}