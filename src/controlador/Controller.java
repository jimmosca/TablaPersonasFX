package controlador;



import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.DatosPersona;
import modelo.Persona;

public class Controller {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellido;
	@FXML
	private TextField txtEdad;

	@FXML
	private Button btnAnnadir;
	@FXML
	private Button btnBorrar;

	@FXML
	private TableView<Persona> table;
	@FXML
	private TableColumn<Persona, String> colNombre;
	@FXML
	private TableColumn<Persona, String> colApellido;
	@FXML
	private TableColumn<Persona, Integer> colEdad;

	private BooleanBinding boolBtnAnnadir;

	static protected DatosPersona misDatos;

	@FXML
	public void initialize() {
		misDatos = new DatosPersona();

		colNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("Nombre"));
		colApellido.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellido"));
		colEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("Edad"));
		refrescarTabla();

		boolBtnAnnadir = new BooleanBinding() {
			{
				super.bind(txtNombre.textProperty(), txtApellido.textProperty(), txtEdad.textProperty());
			}

			@Override
			protected boolean computeValue() {
				return (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()
						|| txtEdad.getText().isEmpty());
			}
		};
		btnAnnadir.disableProperty().bind(boolBtnAnnadir);
		btnBorrar.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
	}

	private void refrescarTabla() {
		table.setItems(misDatos.getContactos());

	}

	public void addPersona() {
		misDatos.getContactos()
				.add(new Persona(txtNombre.getText(), txtApellido.getText(), Integer.parseInt(txtEdad.getText())));
	}

	public void dropPersona() {
		TableViewSelectionModel<Persona> modeloSeleccion = table.getSelectionModel();

		misDatos.getContactos().remove(modeloSeleccion.getSelectedIndex());
		modeloSeleccion.clearSelection();

	}
	
	
}
