package fr.triedge.worker.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import fr.triedge.worker.utils.Utils;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Note extends BorderPane{

	private File file;
	private TextArea textArea;
	private ToolBar toolbar;
	private Button btnSave;

	public Note(File file) {
		super();
		this.file = file;
		buildUI();
		loadFileIntoEditor();
		
		textArea.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
        	if (e.isControlDown() && e.getCode() == KeyCode.S) {
        		save();
        	}
        });
	}
	
	public void buildUI() {
		String fontName = Utils.config.getProperty("fx.area.font","Quicksand-Regular.ttf");
		int fontSize = Integer.parseInt(Utils.config.getProperty("fx.area.font.size","15"));
		setTextArea(new TextArea());
		getTextArea().setFont(Font.loadFont("file:resources/"+fontName, fontSize));
		setToolbar(new ToolBar());
		setBtnSave(new Button());
		getBtnSave().setGraphic(new ImageView(getClass().getResource("/icons8-save-16.png").toExternalForm()));
		getBtnSave().setOnAction(e -> save());
		getToolbar().getItems().add(btnSave);
		
		setTop(toolbar);
		setCenter(textArea);
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
				textArea.setText(tmp.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save() {
		if (file != null && file.exists()) {
			String txt = textArea.getText();
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

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public void setToolbar(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}
}
