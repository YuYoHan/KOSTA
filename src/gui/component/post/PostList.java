package gui.component.post;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.PostDAO;
import dto.PostResponseDTO;
import gui.component.global.CustomStyle;
import gui.component.global.CustomStyle.DrowSquare;
import gui.component.buttons.DefaultButton;
import gui.component.buttons.Pagenation;
import gui.component.buttons.PrimaryButton;
import gui.component.global.Header;
import gui.component.input.RoundInput;

import static dao.PostDAO.selectAll;

public class PostList extends JFrame{
	private JTable table;
	private Vector<Vector<String>> rowData = new Vector<>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	PostList(){
		//S:header
		Header header = new Header(this);
		header.getButtonLogout().setVisible(false);
		header.getButtonSignUp().setVisible(false);
		header.getButtonPostList().setIsCurrent(true);
		add(header, BorderLayout.NORTH);
		//E:header
		
		//S:body
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		body.setBorder(new EmptyBorder(0, 96, 60, 96));
		body.setBackground(CustomStyle.white);
		add(body);
		//E:body
		
		//S: Total label
		JPanel tableTop = new JPanel();
		tableTop.setLayout(new BorderLayout());
		tableTop.setBackground(CustomStyle.white);
		body.add(tableTop, BorderLayout.NORTH);
		
		
		JPanel labelWrap = new JPanel();
		labelWrap.setLayout(new FlowLayout());
		labelWrap.setBackground(CustomStyle.white);
		tableTop.add(labelWrap, BorderLayout.WEST);
		
		JLabel defaultLabel1 = new JLabel("Total ");
		labelWrap.add(defaultLabel1);
		defaultLabel1.setFont(CustomStyle.setCutomFont(16, 'n'));
		
		JLabel totalNum = new JLabel("0");
		labelWrap.add(totalNum);
		totalNum.setFont(CustomStyle.setCutomFont(16, 'b'));
		totalNum.setForeground(CustomStyle.mainColor);
		//E: Total label
		
		//S: tableContentBox 
		JScrollPane tableContentBox = new JScrollPane();
		tableContentBox.setBackground(CustomStyle.white);
		DrowSquare tableBoxBorder = new DrowSquare();
		tableContentBox.setBorder(tableBoxBorder);
		tableContentBox.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableContentBox.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		body.add(tableContentBox, BorderLayout.CENTER);
		
		JTable table;
		Vector<String> columnName = new Vector<String>();
		columnName.add("No");
		columnName.add("제목");
		columnName.add("작성자");
		columnName.add("작성일");
		List<PostResponseDTO> posts = selectAll(); // selectAll() 메서드 호출
		Vector<Vector<String>> rowData = new Vector<>();
		for (PostResponseDTO post : posts) {
			Vector<String> row = new Vector<>();
			row.add(String.valueOf(post.getPostId()));
			row.add(post.getPostTitle());
			row.add(String.valueOf(post.getNickname()));
			row.add(dateFormat.format(post.getPostRegTime()));
			rowData.add(row);
		}
		table = new JTable(rowData, columnName);
		table.setBorder(new LineBorder(CustomStyle.borderColor, 1));
		
		tableContentBox.setViewportView(table);
		table.setMinimumSize(super.getPreferredSize());
		//E: tableContentBox
		//S: tableBottomArea
		JPanel tableBottomArea = new JPanel();
		body.add(tableBottomArea, BorderLayout.SOUTH);
		tableBottomArea.setLayout(new GridLayout(2,1));
		
		
		JPanel tableNavArea = new JPanel();
		tableNavArea.setLayout(new BorderLayout());
		
		tableBottomArea.add(tableNavArea);
		
		
		Pagenation tablePagenation = new Pagenation();
		tableNavArea.add(tablePagenation, BorderLayout.CENTER);
		
		
		JPanel buttonsWrap = new JPanel();
		buttonsWrap.setLayout(new FlowLayout());
		tableNavArea.add(buttonsWrap, BorderLayout.EAST);
		buttonsWrap.setBackground(CustomStyle.white);
		DefaultButton ButtonWrite = new DefaultButton("글쓰기");
		buttonsWrap.add(ButtonWrite);
		
		
		JPanel tableSearchArea = new JPanel();
		tableBottomArea.add(tableSearchArea);
		tableSearchArea.setLayout(new FlowLayout());
		tableSearchArea.setBackground(CustomStyle.white);
		RoundInput searchInput = new RoundInput(20);
		tableSearchArea.add(searchInput);
		PrimaryButton buttonSearch = new PrimaryButton("검색");
		tableSearchArea.add(buttonSearch);
		//E: tableBottomArea

		//S: Search Table
		buttonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				rowData.clear();
				String searchTxt = searchInput.input.getText();
				ArrayList<PostResponseDTO> searchResult = PostDAO.searchPostByTitleNickname(searchTxt);
				//System.out.println(searchResult);
				searchResult.forEach(
						dto ->{
							Vector<String> row = new Vector<>();
							row.add(dto.getPostId()+"");
							row.add(dto.getPostTitle());
							row.add(dto.getNickname());
							row.add(dateFormat.format(dto.getPostRegTime()));
							rowData.add(row);
						}
				);
				table.updateUI();
				searchInput.input.setText("");
			}
		});
		//E: Search Table

		//S: JFrame Setting
		setSize(1440, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//E: JFrame Setting
	}
	public static void main(String[] args) {
		new PostList();
	}
}