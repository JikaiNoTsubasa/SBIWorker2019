package fr.triedge.worker.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import fr.triedge.worker.utils.Utils;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;

public class FileTab extends Tab{

	private File file;
	private HTMLEditor editor;
	
	public void buildUI() {
		editor = new HTMLEditor();
        editor.setPrefHeight(245);
        this.setContent(editor);
        this.setText(file.getName());
        ImageView view = new ImageView(getClass().getResource("/icons8-file-16.png").toExternalForm());
        setGraphic(view);
        
        editor.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
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
				editor.setHtmlText(tmp.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public FileTab(File file) {
		super();
		this.file = file;
		buildUI();
		loadFileIntoEditor();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public HTMLEditor getEditor() {
		return editor;
	}

	public void setEditor(HTMLEditor editor) {
		this.editor = editor;
	}

	public void save() {
		if (file != null && file.exists()) {
			String txt = editor.getHtmlText();
			FileWriter w;
			try {
				w = new FileWriter(file);
				w.write(txt);
				w.flush();
				w.close();
				Utils.updateStatus("Saved to: "+file.getName());
				Utils.updateRecent(file.getAbsoluteFile());
				
			} catch (IOException e) {
				Utils.error("Write error", "Could not save file", e);
			}
		}
	}
}
