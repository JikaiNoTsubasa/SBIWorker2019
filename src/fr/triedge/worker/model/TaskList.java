package fr.triedge.worker.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tasklist")
public class TaskList {

	
	private ArrayList<Task> tasks = new ArrayList<>();

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	@XmlElement(name="task")
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
}
