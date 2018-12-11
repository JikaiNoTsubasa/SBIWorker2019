package fr.triedge.worker.ui;

import fr.triedge.worker.controller.Controller;
import fr.triedge.worker.model.Task;
import fr.triedge.worker.model.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class TaskManager extends BorderPane{

	private TableView<Task> tableTask;
	private ToolBar toolbar;
	private Button btnNew, btnOpen, btnDelete, btnEdit;
	private Controller controller;
	
	public TaskManager(Controller controller) {
		setController(controller);
		buildUI();
	}

	private void buildUI() {
		setToolbar(new ToolBar());
		setBtnNew(new Button());
		setBtnOpen(new Button());
		setBtnDelete(new Button());
		setBtnEdit(new Button());
		getBtnNew().setGraphic(new ImageView(getClass().getResource("/icons8-plus-math-16.png").toExternalForm()));
		getBtnOpen().setGraphic(new ImageView(getClass().getResource("/icons8-opened-folder-16.png").toExternalForm()));
		getBtnEdit().setGraphic(new ImageView(getClass().getResource("/icons8-pencil-16.png").toExternalForm()));
		getBtnDelete().setGraphic(new ImageView(getClass().getResource("/icons8-close-window-16_red.png").toExternalForm()));
		getBtnNew().setTooltip(new Tooltip("New Task"));
		getBtnEdit().setTooltip(new Tooltip("Edit Task"));
		getBtnOpen().setTooltip(new Tooltip("Open Task"));
		getBtnDelete().setTooltip(new Tooltip("Delete Task"));
		getBtnNew().setOnAction(e -> {
			getController().actionNewTask(e);
			populateTable(getController().getModel().getTaskList());
			getTableTask().refresh();
		});
		getBtnEdit().setOnAction(e -> {
			getController().actionEditTask(e, getTableTask().getSelectionModel().getSelectedItem());
			getTableTask().refresh();
		});
		getToolbar().getItems().add(getBtnNew());
		getToolbar().getItems().add(getBtnEdit());
		getToolbar().getItems().add(getBtnOpen());
		getToolbar().getItems().add(getBtnDelete());
		
		buildTable();
		
		setTop(toolbar);
		setCenter(tableTask);
	}

	public TableView<Task> getTableTask() {
		return tableTask;
	}

	public void setTableTask(TableView<Task> tableTask) {
		this.tableTask = tableTask;
	}
	
	@SuppressWarnings("unchecked")
	private void buildTable() {
		tableTask = new TableView<>();
		
		// Set collumns
		TableColumn<Task, Integer> colId = new TableColumn<Task, Integer>("ID");
		TableColumn<Task, Double> colProgress = new TableColumn<Task, Double>("Progress");
		TableColumn<Task, String> colTitle = new TableColumn<Task, String>("Name");
		TableColumn<Task, String> colDesc = new TableColumn<Task, String>("Description");
		
		// Set callbacks
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colProgress.setCellValueFactory(new PropertyValueFactory<>("progress"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
		colTitle.prefWidthProperty().bind(tableTask.widthProperty().multiply(0.7));
		colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		
		tableTask.getColumns().addAll(colId, colProgress, colTitle, colDesc);
		populateTable(getController().getModel().getTaskList());
	}
	
	public void populateTable(TaskList list1) {
		ObservableList<Task> list = FXCollections.observableArrayList(list1.getTasks());
		tableTask.setItems(list);
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public void setToolbar(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public Button getBtnOpen() {
		return btnOpen;
	}

	public void setBtnOpen(Button btnOpen) {
		this.btnOpen = btnOpen;
	}

	public Button getBtnNew() {
		return btnNew;
	}

	public void setBtnNew(Button btnNew) {
		this.btnNew = btnNew;
	}

	public Button getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(Button btnEdit) {
		this.btnEdit = btnEdit;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}
