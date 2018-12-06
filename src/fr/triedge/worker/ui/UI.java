package fr.triedge.worker.ui;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UI {

	public static File openFile(Stage stage, String workspace) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(workspace));
		File file = chooser.showOpenDialog(stage);
		return file;
	}
	
}
