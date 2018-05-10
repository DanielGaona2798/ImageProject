package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	private Table table;
	
	public MainWindow() {
		setTitle("Affinity 1.0");
		setLayout(new BorderLayout());
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		table = new Table();
		add(table, BorderLayout.CENTER);
		
		
		setVisible(true);
	}
	
	public void setImages(ArrayList<String> lsit) {
		table.setImages(lsit);
	} 
}
