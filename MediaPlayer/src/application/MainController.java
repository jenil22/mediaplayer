package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController implements Initializable{
	@FXML private MediaView Mvmedia;
	public MediaPlayer mp;
	private Media me;
	@FXML private Slider slider;
	@FXML private Slider sl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		// TODO Auto-generated method stub
	
	}
	public void play(ActionEvent event) throws Exception{
		mp.play();
		
	}
	public void pause(ActionEvent event) throws Exception
	{
		mp.pause();;
	}
	public void stop(ActionEvent event) throws Exception
	{
		mp.stop();
	}
	public void reload(ActionEvent event) throws Exception
	{
		mp.seek(mp.getStartTime());
		mp.play();;
	}
	public void open(ActionEvent event) throws Exception
	{
		FileChooser fc =new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("mp4","*.mp4"));
		fc.setInitialDirectory(new File("D:/jenil"));
		File file=fc.showOpenDialog(null);
		String path=file.getAbsolutePath();
		if(file != null) {
		me = new Media(new File(path.toString()).toURI().toString());
		mp = new MediaPlayer(me);
		Mvmedia.setMediaPlayer(mp);
		mp.setAutoPlay(true);
		DoubleProperty width= Mvmedia.fitWidthProperty();
		DoubleProperty heigth= Mvmedia.fitHeightProperty();
		width.bind(Bindings.selectDouble(Mvmedia.sceneProperty(), "width"));
		heigth.bind(Bindings.selectDouble(Mvmedia.sceneProperty(), "heigth"));
		slider.setValue(mp.getVolume() * 100);
		slider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				mp.setVolume(slider.getValue() / 100);
			}
			
		});
		}
		else
		{
			Platform.exit();
		}
	}
	public void close(ActionEvent event) throws Exception
	{
		Platform.exit();
		System.exit(0);
	}
	
	
}