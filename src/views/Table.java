package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class Table extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable tableMan;
	private JScrollPane scroll;

	public Table() {
		setLayout(new BorderLayout());

		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] {"Image" , "Status"});

		tableMan = new JTable(model);
		tableMan.setRowHeight(25);
		tableMan.setFont(new Font("Arial", Font.BOLD, 15));

		JTableHeader header = tableMan.getTableHeader();
		header.setBackground(Color.BLUE);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.BOLD, 19));
		
		scroll = new JScrollPane(tableMan);
		add(scroll, BorderLayout.CENTER);
	}

	public void setImages(ArrayList<String> lsit) {
		for (String string : lsit) {
			model.addRow(new Object[] {string , "lista"});
		}
	}


}