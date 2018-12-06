package fr.triedge.worker.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import fr.triedge.worker.utils.Utils;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;

public class Editor extends HTMLEditor{

	private File file;
	
	public Editor(File file) {
		super();
		this.file = file;
		loadFileIntoEditor();
		
		addEventFilter(KeyEvent.KEY_PRESSED, e -> {
        	if (e.isControlDown() && e.getCode() == KeyCode.S) {
        		save();
        	}
        });
	}
	
	public void loadFileIntoEditor() {
		if (file != null && file.exists()) {
			StringBuilder tmp = new StringBuilder();
			try {
				Scanner scan = new Scanner(file);
				while (scan.hasNextLine()) {
					tmp.append(scan.nextLine());
					tmp.append("\r\n");
				}
				scan.close();
				setHtmlText(tmp.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save() {
		if (file != null && file.exists()) {
			String txt = getHtmlText();
			FileWriter w;
			try {
				w = new FileWriter(file);
				w.write(txt);
				w.flush();
				w.close();
				Utils.updateStatus("Saved to: "+file.getName());
				
			} catch (IOException e) {
				Utils.error("Write error", "Could not save file", e);
			}
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
