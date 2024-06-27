package gui.component.input;

import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.CustomStyle;

//흰색 배경의 둥근 모서리를 가진 JTextField이며, 매개변수로 JTextField와 동일한 값을 받는다.
public class RoundInput extends JPanel{
	protected JTextField input;
	
	public RoundInput(int n){
		//setFocusable(false);
		setBorder(new CustomStyle.DrawRoundSquare(8, CustomStyle.borderColor, CustomStyle.white));
		
		input = new JTextField(n);
		add(input);
		input.setBorder(null);
		input.setFont(CustomStyle.setCutomFont(16, 'n'));
		
	}
	
	public RoundInput() {
		this(10);
	}
}
