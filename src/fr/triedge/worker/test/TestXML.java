package fr.triedge.worker.test;

import java.io.File;

import javax.xml.bind.JAXBException;

import fr.triedge.worker.model.RecentNote;
import fr.triedge.worker.model.RecentNoteList;
import fr.triedge.worker.utils.Utils;

public class TestXML {

	public static void main(String[] args) {
		/*
		Task t = new Task("Task1");
		t.setContent("Content");
		t.setDesc("Short desc");
		Task t2 = new Task("Task12");
		t2.setContent("Content2");
		t2.setDesc("Short desc2");
		
		TaskList list = new TaskList();
		list.getTasks().add(t);
		list.getTasks().add(t2);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TaskList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(list, new File("storage/tasks.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		RecentNote note = new RecentNote();
		note.setPath("\\\\msfshome\\st002841$\\MyDocuments\\Activities\\2018_Activities\\2018-11-26_Morning_Checks");
		RecentNoteList list = new RecentNoteList();
		list.getNotes().add(note);
		try {
			Utils.storeXml(list, new File("storage/recent.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
