package fr.triedge.worker.ui;

import fr.triedge.worker.model.Task;
import fr.triedge.worker.utils.Utils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
	private TextField fieldName, fieldId, fieldDesc;
	private HTMLEditor fieldContent;
	private Slider fieldProgress;
	
	public TaskWizard(String title, Task task, Stage parent) {
		setStage(new Stage());
		setTask(task);
		getStage().setTitle(title);
		setWizardPane(new BorderPane());
		setScene(new Scene(getWizardPane(), 900, 500));
		getStage().setScene(getScene());
		getStage().initOwner(parent);
		getStage().initModality(Modality.WINDOW_MODAL);
		wizardPane.getStylesheets().add(Utils.config.getProperty("fx.theme", "/dark-theme.css"));
		getStage().getIcons().add(new Image(getClass().getResourceAsStream(Utils.config.getProperty("fx.icon", "/icon_bright.png"))));
		GridPane grid = new GridPane();
		
		Label labID = new Label("ID");
		Label labName = new Label("Name");
		Label labContent = new Label("Content");
		Label labDesc = new Label("Description");
		Label labProgress = new Label("Progress");
		
		setFieldId(new TextField());
		setFieldName(new TextField());
		setFieldContent(new HTMLEditor());
		setFieldDesc(new TextField());
		setFieldProgress(new Slider(0, 100, 0));
		//getFieldContent().setMaxWidth(400);
		//getFieldContent().setMaxHeight(400);
		
		if (task != null) {
			getFieldId().setText(task.getId()+"");
			getFieldName().setText(task.getName());
			getFieldContent().setHtmlText(task.getContent());
			getFieldDesc().setText(task.getDesc());
			getFieldProgress().setValue(task.getProgress());
		}
		
		grid.add(labID, 0, 0);
		grid.add(labName, 0, 1);
		grid.add(labProgress, 0, 2);
		grid.add(labContent, 0, 3);
		grid.add(labDesc, 0, 4);
		grid.add(getFieldId(), 1, 0);
		grid.add(getFieldName(), 1, 1);
		grid.add(getFieldProgress(), 1, 2);
		grid.add(getFieldContent(), 1, 3);
		grid.add(getFieldDesc(), 1, 4);
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
		getTask().setDesc(getFieldDesc().getText());
		getTask().setProgress(Utils.trimDouble(getFieldProgress().getValue()));
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

	public TextField getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(TextField fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public Slider getFieldProgress() {
		return fieldProgress;
	}

	public void setFieldProgress(Slider fieldProgress) {
		this.fieldProgress = fieldProgress;
	}
}
