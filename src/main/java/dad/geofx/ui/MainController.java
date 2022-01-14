package dad.geofx.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.google.gson.stream.MalformedJsonException;

import dad.geofx.api.ipapi.IpapiMessage;
import dad.geofx.api.ipapi.IpapiService;
import dad.geofx.api.ipify.IpifyService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	// LOGICA

	IpifyService ipifyService = new IpifyService();

	IpapiService ipapiService = new IpapiService();

	// MODELO

	ObjectProperty<IpapiMessage> ipapi = new SimpleObjectProperty<>();
	StringProperty address = new SimpleStringProperty();
	StringProperty latitud = new SimpleStringProperty();
	StringProperty longitude = new SimpleStringProperty();
	StringProperty location = new SimpleStringProperty();
	StringProperty city = new SimpleStringProperty();
	StringProperty zip = new SimpleStringProperty();
	StringProperty lenguage = new SimpleStringProperty();
	StringProperty time = new SimpleStringProperty();
	StringProperty calling = new SimpleStringProperty();
	StringProperty  currency= new SimpleStringProperty();
	StringProperty isp = new SimpleStringProperty();
	StringProperty type = new SimpleStringProperty();
	StringProperty asn = new SimpleStringProperty();
	StringProperty host = new SimpleStringProperty();
	ObjectProperty<Image> flag = new SimpleObjectProperty<>();
	BooleanProperty proxy = new SimpleBooleanProperty();
	BooleanProperty tor = new SimpleBooleanProperty();
	BooleanProperty crawler = new SimpleBooleanProperty();
	StringProperty security = new SimpleStringProperty();
	StringProperty threats = new SimpleStringProperty();
	StringProperty threatTypes = new SimpleStringProperty();
	

	// VISTA

	@FXML
	private BorderPane root;

	@FXML
	private Label asnLabel;

	@FXML
	private Label callingLabel;

	@FXML
	private Button checkButton;

	@FXML
	private Label cityLabel;

	@FXML
	private CheckBox crawlerCheck;

	@FXML
	private Label currencyLabel;

	@FXML
	private ImageView flagImage;

	@FXML
	private Label hostLabel;

	@FXML
	private Label ipLabel;

	@FXML
	private TextField ipText;

	@FXML
	private Label ispLabel;

	@FXML
	private Label latitudeLabel;

	@FXML
	private Label lenguageLabel;

	@FXML
	private Label locationLabel;

	@FXML
	private Label longitudeLabel;

	@FXML
	private CheckBox proxyCheck;

	@FXML
	private Label securityLabel;

	@FXML
	private Label threatLevelLabel;

	@FXML
	private Label threatLabel;

	@FXML
	private Label timeLabel;

	@FXML
	private CheckBox torCheck;

	@FXML
	private Label typeLabel;

	@FXML
	private Label zipLabel;

	public BorderPane getView() {
		return root;
	}

	public MainController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// binding
		address.bindBidirectional(ipText.textProperty());
		address.bindBidirectional(ipLabel.textProperty());
		latitud.bindBidirectional(latitudeLabel.textProperty());
		longitude.bindBidirectional(longitudeLabel.textProperty());
		this.location.bindBidirectional(locationLabel.textProperty());
		flag.bindBidirectional(flagImage.imageProperty());
		city.bindBidirectional(cityLabel.textProperty());
		zip.bindBidirectional(zipLabel.textProperty());
		lenguage.bindBidirectional(lenguageLabel.textProperty());
		time.bindBidirectional(timeLabel.textProperty());
		calling.bindBidirectional(callingLabel.textProperty());
		currency.bindBidirectional(currencyLabel.textProperty());
		isp.bindBidirectional(ispLabel.textProperty());
		type.bindBidirectional(typeLabel.textProperty());
		asn.bindBidirectional(asnLabel.textProperty());
		host.bindBidirectional(hostLabel.textProperty());
		proxy.bindBidirectional(proxyCheck.selectedProperty());
		tor.bindBidirectional(torCheck.selectedProperty());
		crawler.bindBidirectional(crawlerCheck.selectedProperty());
		security.bindBidirectional(securityLabel.textProperty());
		threats.bindBidirectional(threatLevelLabel.textProperty());
		threatTypes.bindBidirectional(threatLabel.textProperty());


		// init

		Task<String> ipTask = new Task<String>() {

			@Override
			protected String call() throws Exception {
				Thread.sleep(500L);
				return ipifyService.getIP();
			}
		};

		ipTask.setOnSucceeded(event -> {
			address.set(ipTask.getValue());
			setInfo(address.get());
		});
		ipTask.setOnFailed(event -> {
			event.getSource().getException().printStackTrace();
		});

		new Thread(ipTask).start();
	}

	@FXML
	private void onCheckButton(ActionEvent e) {

		setInfo(address.get());

	}

	private void setInfo(String ip) {

		Task<IpapiMessage> ipInfo = new Task<IpapiMessage>() {

			@Override
			protected IpapiMessage call() throws Exception {
				Thread.sleep(500L);

				return ipapiService.message(ip);
			}
		};

		ipInfo.setOnSucceeded(event -> {

			try {

				IpapiMessage ipapi = ipInfo.get();
								
				latitud.set(ipapi.getLatitude().toString());
				longitude.set(ipapi.getLongitude().toString());
				location.set(
						ipapi.getCountryName().concat(" (").concat(ipapi.getCountryCode().concat(")")));
				flag.set(new Image("/64x42/" + ipapi.getCountryCode() + ".png"));
				city.set(ipapi.getCity().concat(" (").concat(ipapi.getRegionName()).concat(")"));
				zip.set(ipapi.getZip());
				lenguage.set(ipapi.getLocation().getLanguages().get(0).getName().concat(" (").concat(ipapi.getCountryCode().concat(")")));
				time.set(ipapi.getTimeZone().getCode());
				calling.set(ipapi.getLocation().getCallingCode());
				currency.set(ipapi.getCurrency().getName().concat(" (")
						.concat(ipapi.getCurrency().getSymbol().concat(")")));
				address.set(ipapi.getIp());
				isp.set(ipapi.getConnection().getIsp());
				typeLabel.setText(ipapi.getType());
				asnLabel.setText(ipapi.getConnection().getAsn().toString());
				hostLabel.setText(ipapi.getHostname());
				proxy.set(ipapi.getSecurity().getIsProxy());
				tor.set(ipapi.getSecurity().getIsTor());
				crawler.set(ipapi.getSecurity().getIsCrawler());
				threats.set(ipapi.getSecurity().getThreatLevel());
				
				if (ipapi.getSecurity().getThreatLevel().equals("low")) {
					security.set("Esta IP es segura. No se han encontrado amenazas.");
					threatTypes.set("No se han detectado amenazas para esta dirección IP.");
				}
				else {
					security.set("Se han encontrado varias amenazas. Esta IP no es segura.");
					threatTypes.set(ipapi.getSecurity().getThreatTypes().toString());
				}
				

			} catch (Exception e) {

				Alert alertError = new Alert(AlertType.ERROR);
				alertError.setTitle("GeoFx");
				alertError.setHeaderText("ERROR");
				alertError.setContentText("Ha introducido una IP inválida o ha surgido un problema: "+e.getMessage());
				alertError.showAndWait();
			}

		});
		ipInfo.setOnFailed(event -> {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("GeoFx");
			alertError.setHeaderText("ERROR");
			alertError.setContentText("Ha introducido una IP inválida. ");
			alertError.showAndWait();
		});

		new Thread(ipInfo).start();
	}

}
