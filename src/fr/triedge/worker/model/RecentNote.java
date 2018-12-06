package fr.triedge.worker.model;

import javax.xml.bind.annotation.XmlElement;

public class RecentNote {

	private String path;

	public String getPath() {
		return path;
	}

	@XmlElement()
	public void setPath(String path) {
		this.path = path;
	}
}
