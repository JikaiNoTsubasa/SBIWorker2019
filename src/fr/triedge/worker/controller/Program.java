package fr.triedge.worker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import fr.triedge.worker.utils.Fifo;
import fr.triedge.worker.utils.Utils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Program extends Application{
	
	public static String STORAGE						= "storage";
	public static String RECENT							= "recent";
	public static final String CONF						= "conf/config.properties";
	
	private Stage stage;
	private Scene scene;
	private MenuItem itemExit, itemOpen, itemSave, itemNew, itemStorageLocation, itemOpenTaskManager;
	private Menu menuOpenRecent;
	private TabPane tabbedPane;
	public static Fifo<String> recentItems;
	
	public void init() {
		Utils.config = new Properties();
		try {
			Utils.config.load(new FileInputStream(new File(CONF)));
		} catch (IOException e) {
			Utils.error("ERROR", "Cannot load config file", e);
		}
	}
	
	public void buildUI() {
		// Stage
		stage.setTitle(Utils.config.getProperty("fx.title", "FX"));
        stage.setWidth(Double.parseDouble(Utils.config.getProperty("fx.width", "600")));
        stage.setHeight(Double.parseDouble(Utils.config.getProperty("fx.height", "500")));
        STORAGE = Utils.config.getProperty("fx.storage", "storage");
        RECENT = Utils.config.getProperty("fx.recent", "recent");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons8-tentacles-64.png")));
        //stage.initStyle(StageStyle.UNDECORATED);
        
        // Root
        BorderPane root = new BorderPane();
        tabbedPane = new TabPane();
        
        // Menubar
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        itemOpen = new MenuItem("Open file...");
        itemSave = new MenuItem("Save file");
        itemExit = new MenuItem("Exit");
        itemNew = new MenuItem("New file");
        Menu menuTaskManager = new Menu("Task Manager");
        itemOpenTaskManager = new MenuItem("Open");
        itemStorageLocation = new MenuItem("Change Storage Location");
        
        menuOpenRecent = new Menu("Open recent...");
        
        menuFile.getItems().add(itemNew);
        menuFile.getItems().add(itemOpen);
        menuFile.getItems().add(itemSave);
        menuFile.getItems().add(menuOpenRecent);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(itemStorageLocation);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(itemExit);
        menuTaskManager.getItems().add(itemOpenTaskManager);
        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuTaskManager);
        
        loadRecentFiles();
        
        root.setTop(menuBar);
        root.setCenter(tabbedPane);
        root.setBottom(Utils.STATUS);
        
        scene = new Scene(root);
        root.getStylesheets().add("/dark-theme.css");
        stage.setScene(scene);
        stage.show();
	}
	
	public void loadRecentFiles() {
		// Load recent items
        recentItems = new Fifo<>(5);
        try {
			Scanner scan = new Scanner(new File(RECENT));
			while (scan.hasNextLine()) {
				recentItems.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			Utils.error("ERROR", "Cannot read recent file", e);
		}
        
        //menuOpenRecent = new Menu("Open recent...");
        recentItems.getQueue().forEach(el ->{
        	MenuItem item = new MenuItem(el);
        	item.setOnAction(e -> {
        		actionOpenFile(el);
        	});
        	menuOpenRecent.getItems().add(item);
        });
	}

	private void actionOpenFile(String el) {
		File file = new File(el);
		FileTab ftab = new FileTab(file);
		tabbedPane.getTabs().add(ftab);
	}

	public void buildActions() {
		itemExit.setOnAction((e) ->{
			actionExit(e);
		});
		itemOpen.setOnAction((e) -> {
			actionOpenFile(e);
		});
		itemSave.setOnAction((e) -> {
			actionSaveFile(e);
		});
		itemNew.setOnAction((e) -> {
			actionNewFile(e);
		});
		itemStorageLocation.setOnAction((e) -> {
			actionChangeStorage(e);
		});
		itemOpenTaskManager.setOnAction((e) -> {
			actionOpenTaskManager(e);
		});
	}


	private void actionOpenTaskManager(ActionEvent e) {
		TaskManager m = new TaskManager();
		m.buildUI();
	}

	private void actionChangeStorage(ActionEvent e) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(STORAGE));
		File file = chooser.showDialog(stage);
		if (file != null)
			STORAGE = file.getAbsolutePath();
	}

	private void actionNewFile(ActionEvent e) {
		//FileChooser chooser = new FileChooser();
		//chooser.setInitialDirectory(new File(STORAGE));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//chooser.setInitialFileName(format.format(new Date())+"_");
		//File file = chooser.showSaveDialog(stage);
		File file = new File(STORAGE+File.separator+format.format(new Date())+"_"+ Utils.dialogText("File Name", "Enter file name"));
		FileWriter w;
		try {
			w = new FileWriter(file);
			w.write("");
			w.flush();
			w.close();
		} catch (IOException e1) {
			Utils.error("Error file", "Cannot create file", e1);
		}
		FileTab ftab = new FileTab(file);
		tabbedPane.getTabs().add(ftab);
	}

	private void actionSaveFile(ActionEvent e) {
		// Get active pane
		FileTab ftab = (FileTab)tabbedPane.getSelectionModel().getSelectedItem();
		if (ftab != null)
			ftab.save();
	}

	private void actionOpenFile(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(STORAGE));
		File file = chooser.showOpenDialog(stage);
		if (file == null)
			return;
		FileTab ftab = new FileTab(file);
		tabbedPane.getTabs().add(ftab);
	}

	private void actionExit(ActionEvent e) {
		Utils.config.setProperty("fx.width", String.valueOf(stage.getWidth()));
		Utils.config.setProperty("fx.height", String.valueOf(stage.getHeight()));
		Utils.config.setProperty("fx.storage", STORAGE);
		Utils.saveConfig();
		try {
			FileWriter w = new FileWriter(new File(RECENT));
			recentItems.getQueue().forEach(el -> {
				try {
					w.write(el+"\r\n");
					w.flush();
				} catch (IOException e1) {
				}
			});
			w.close();
		} catch (IOException e1) {
			Utils.error("ERROR", "Cannot write in RECENT", e1);
		}
		System.exit(0);
	}
	
	@Override
	public void stop() {
		actionExit(null);
	}

	@Override
    public void start(Stage stage) {
        this.stage = stage;
        init();
        buildUI();
        buildActions();
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}
