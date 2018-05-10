package controller;

import java.io.IOException;

import models.Page;
import views.MainWindow;

public class Controller {
	
	private MainWindow mainWindow;
	private Page page;
	
	public Controller() {
		page = new Page();
		mainWindow = new MainWindow();
		try {
			mainWindow.setImages(page.downloadImages(page.getImages()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}
