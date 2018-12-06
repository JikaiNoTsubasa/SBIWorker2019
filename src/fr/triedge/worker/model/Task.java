package fr.triedge.worker.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Task {

	private String name;
	private String content;
	private String desc;
	private double progress;
	private int id;
	
	public Task() {
	}
	
	public Task(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}
	public String getDesc() {
		return desc;
	}
	@XmlElement
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	public double getProgress() {
		return progress;
	}

	@XmlElement
	public void setProgress(double progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", content=" + content + ", desc=" + desc + ", progress=" + progress + ", id="
				+ id + "]";
	}
	
	
}
