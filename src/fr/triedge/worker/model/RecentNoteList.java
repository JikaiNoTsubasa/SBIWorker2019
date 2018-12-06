package fr.triedge.worker.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecentNoteList {

	private ArrayList<RecentNote> notes = new ArrayList<>();

	public ArrayList<RecentNote> getNotes() {
		return notes;
	}

	@XmlElement(name="notes")
	public void setNotes(ArrayList<RecentNote> notes) {
		this.notes = notes;
	}
}
