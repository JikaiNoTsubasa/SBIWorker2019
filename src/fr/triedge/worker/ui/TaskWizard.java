package fr.triedge.worker.ui;

import fr.triedge.worker.model.Task;
import fr.triedge.worker.utils.Utils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskWizard {

	private Stage stage;
	private Scene scene;
	private BorderPane wizardPane;
	private Task task;
	private TextField fieldName, fieldId;
	private HTMLEditor fieldContent;
	
	public TaskWizard(String title, Task task, Stage parent) {
		setStage(new Stage());
		setTask(task);
		getStage().setTitle(title);
		setWizardPane(new BorderPane());
		setScene(new Scene(getWizardPane(), 500, 800));
		getStage().setScene(getScene());
		getStage().initOwner(parent);
		getStage().initModality(Modality.WINDOW_MODAL);
		wizardPane.getStylesheets().add(Utils.config.getProperty("fx.theme", "/dark-theme.css"));
		getStage().getIcons().add(new Image(getClass().getResourceAsStream(Utils.config.getProperty("fx.icon", "/icons8-tentacles-64.png"))));
		GridPane grid = new GridPane();
		
		Label labID = new Label("ID");
		Label labName = new Label("Name");
		Label labContent = new Label("Content");
		
		setFieldId(new TextField());
		setFieldName(new TextField());
		setFieldContent(new HTMLEditor());
		getFieldContent().setMaxWidth(400);
		getFieldContent().setMaxHeight(400);
		
		if (task != null) {
			getFieldId().setText(task.getId()+"");
			getFieldName().setText(task.getName());
			getFieldContent().setHtmlText(task.getDesc());
		}
		
		grid.add(labID, 0, 0);
		grid.add(labName, 0, 1);
		grid.add(labContent, 0, 2);
		grid.add(getFieldId(), 1, 0);
		grid.add(getFieldName(), 1, 1);
		grid.add(getFieldContent(), 1, 2);
		grid.setVgap(10);
		grid.setHgap(10);
		
		// Buttons
		HBox box = new HBox();
		Button btnOk = new Button("OK");
		btnOk.setOnAction(e -> actionOK());
		Button btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e -> actionCancel());
		box.getChildren().addAll(btnCancel, btnOk);
		wizardPane.setCenter(grid);
		wizardPane.setBottom(box);
	}
	
	private void actionCancel() {
		stage.close();
	}

	private void actionOK() {
		if (getTask() == null)
			setTask(new Task());
		getTask().setId(Integer.parseInt(getFieldId().getText()));
		getTask().setName(getFieldName().getText());
		getTask().setContent(getFieldContent().getHtmlText());
		stage.close();
	}

	public void show() {
		getStage().showAndWait();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public BorderPane getWizardPane() {
		return wizardPane;
	}

	public void setWizardPane(BorderPane wizardPane) {
		this.wizardPane = wizardPane;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public TextField getFieldId() {
		return fieldId;
	}

	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}

	public TextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(TextField fieldName) {
		this.fieldName = fieldName;
	}

	public HTMLEditor getFieldContent() {
		return fieldContent;
	}

	public void setFieldContent(HTMLEditor fieldDesc) {
		this.fieldContent = fieldDesc;
	}
}
