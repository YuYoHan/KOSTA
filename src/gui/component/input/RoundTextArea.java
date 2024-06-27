package gui.component.input;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.CustomStyle;

import java.awt.*;

//흰색 배경의 둥근 모서리를 가진 JTextArea이며, 매개변수로 JTextArea와 동일한 값을 받는다.
public class RoundTextArea extends JPanel{
	protected JTextArea textarea;
	
	public RoundTextArea(){
		this(2, 10);
	}
	//public Roun
	public RoundTextArea(int row, int cols){
		setLayout(new BorderLayout());
		setBorder(new CustomStyle.DrawRoundSquare(8, CustomStyle.borderColor, CustomStyle.white));
		textarea = new JTextArea(row, cols);
		add(textarea);
		textarea.setBorder(null);
		textarea.setFont(CustomStyle.setCutomFont(16, 'n'));
	}
}
