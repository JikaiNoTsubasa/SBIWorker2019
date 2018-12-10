package fr.triedge.worker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import fr.triedge.worker.model.Model;
import fr.triedge.worker.model.RecentNote;
import fr.triedge.worker.model.RecentNoteList;
import fr.triedge.worker.model.Task;
import fr.triedge.worker.model.TaskList;
import fr.triedge.worker.ui.MainWindow;
import fr.triedge.worker.ui.Note;
import fr.triedge.worker.ui.TaskManager;
import fr.triedge.worker.ui.TaskWizard;
import fr.triedge.worker.ui.UI;
import fr.triedge.worker.utils.Utils;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller extends Application{
	
	public static String STORAGE						= "storage";
	public static String RECENT_NOTES					= "conf/recent_notes.xml";
	public static final String CONF						= "conf/config.properties";
	
	private MainWindow mainWindow;
	private Model model;
	
	//FIXME - Add web browser and auto open urls from notes (right click)
	//FIXME - Task manager
	
	/**
	 * Initial loads
	 */
	public void init() {
		// Loads config
		Utils.config = new Properties();
		try {
			Utils.config.load(new FileInputStream(new File(CONF)));
			STORAGE = Utils.config.getProperty("fx.storage", "storage");
			RECENT_NOTES = Utils.config.getProperty("fx.recent.notes", "conf/recent_notes.xm");
		} catch (IOException e) {
			Utils.error("ERROR", "Cannot load config file", e);
		}
		
		// Setup model
		setModel(new Model());
		
		// Load tasks
		File file = new File("storage/tasks.xml");
		try {
			TaskList list = Utils.loadTaskXml(file);
			model.setTaskList(list);
		} catch (JAXBException e) {
			Utils.error("ERROR", "Cannot load tasks file", e);
		}
	}
	
	public void openTab(String title ,Node node) {
		Tab tab = new Tab(title, node);
		getMainWindow().getTabPane().getTabs().add(tab);
		getMainWindow().getTabPane().getSelectionModel().select(tab);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		init();
		
		// Create main window
		String title = Utils.config.getProperty("fx.title", "FX");
		double width = Double.parseDouble(Utils.config.getProperty("fx.width", "600"));
		double height = Double.parseDouble(Utils.config.getProperty("fx.height", "500"));
		String theme = Utils.config.getProperty("fx.theme", "/dark-theme.css");
		String icon = Utils.config.getProperty("fx.icon", "/icons8-tentacles-64.png");
		setMainWindow(new MainWindow(stage, title, width, height, theme, icon));
		
		// Create window actions
		getMainWindow().getItemExit().setOnAction(e -> actionExit(e));
		getMainWindow().getItemOpenNote().setOnAction(e -> actionOpenNote(e));
		getMainWindow().getItemNewNote().setOnAction(e -> actionNewNote(e));
		getMainWindow().getItemStorageLocation().setOnAction(e -> actionChangeStorage(e));
		getMainWindow().getItemOpenTaskManager().setOnAction(e -> actionOpenTaskManager());
		
		// Load recent
		File fileRecentNotes = new File(RECENT_NOTES);
		if (fileRecentNotes.exists()) {
			RecentNoteList list = Utils.loadXml(RecentNoteList.class, fileRecentNotes);
			for (RecentNote note : list.getNotes()) {
				Note nt = new Note(new File(note.getPath()));
				registerRecentNote(nt);
			}
		}
		
		// Open window
		getMainWindow().show();
	}
	
	private void actionOpenTaskManager() {
		TaskManager man = new TaskManager(this);
		openTab("Task Manager", man);
	}

	private void actionChangeStorage(ActionEvent e) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(STORAGE));
		File file = chooser.showDialog(getMainWindow().getStage());
		if (file != null)
			STORAGE = file.getAbsolutePath();
	}
	
	private void actionNewNote(ActionEvent e) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String name = Utils.dialogText("File Name", "Enter file name");
		name = name.replaceAll(" ", "_");
		if (name == null)
			return;
		File file = new File(STORAGE+File.separator+format.format(new Date())+"_"+ name);
		FileWriter w;
		try {
			w = new FileWriter(file);
			w.write("");
			w.flush();
			w.close();
		} catch (IOException e1) {
			Utils.error("Error file", "Cannot create file", e1);
		}
		
		Note note = new Note(file);
		openTab(file.getName(), note);
		
		// Register to recent
		registerRecentNote(note);
	}
	
	private void registerRecentNote(Note note) {
		ObservableList<MenuItem> items = getMainWindow().getMenuRecentNote().getItems();
		Iterator<MenuItem> it = items.iterator();
		while (it.hasNext()) {
			MenuItem item = it.next();
			if (item.getText().equalsIgnoreCase(note.getFile().getAbsolutePath())) {
				it.remove();
			}
			
		}
		
		MenuItem itemRecent = new MenuItem(note.getFile().getAbsolutePath());
		itemRecent.setOnAction(e -> {
			openTab(note.getFile().getName(), note);
		});
		getMainWindow().getMenuRecentNote().getItems().add(0,itemRecent);
	}

	private void actionOpenNote(ActionEvent e) {
		File file = UI.openFile(getMainWindow().getStage(), STORAGE);
		if (file == null)
			return;
		Note note = new Note(file);
		openTab(file.getName(), note);
		
		// Register to recent
		registerRecentNote(note);
	}

	private void actionExit(ActionEvent e) {
		Utils.config.setProperty("fx.width", String.valueOf(getMainWindow().getStage().getWidth()));
		Utils.config.setProperty("fx.height", String.valueOf(getMainWindow().getStage().getHeight()));
		Utils.config.setProperty("fx.storage", STORAGE);
		Utils.saveConfig();
		
		storeRecents();
		
		System.exit(0);
	}

	private void storeRecents() {
		// Store recent notes
		ObservableList<MenuItem> noteList = getMainWindow().getMenuRecentNote().getItems();
		RecentNoteList storageList = new RecentNoteList();
		for (MenuItem item : noteList) {
			RecentNote note = new RecentNote();
			note.setPath(item.getText());
			storageList.getNotes().add(note);
		}
		try {
			Utils.storeXml(storageList, new File(RECENT_NOTES));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		actionExit(null);
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void actionNewTask(ActionEvent e) {
		TaskWizard wiz = new TaskWizard("New Task", null, getMainWindow().getStage());
		wiz.show();
		Task task = wiz.getTask();
		if (task != null)
			getModel().getTaskList().getTasks().add(task);
	}
	
	public void actionEditTask(ActionEvent e, Task task) {
		TaskWizard wiz = new TaskWizard("New Task", task, getMainWindow().getStage());
		wiz.show();
	}
}
