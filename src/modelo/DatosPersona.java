package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatosPersona {

	private ObservableList<Persona> contactos;
	
	public DatosPersona() {
		contactos = FXCollections.observableArrayList();
		this.contactos.add(new Persona("Alfredo", "Manjon", 18));
	}
	
	public ObservableList<Persona> getContactos() {
		return contactos;
	}
	
}
