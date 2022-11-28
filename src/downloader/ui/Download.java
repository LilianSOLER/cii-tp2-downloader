package downloader.ui;

import downloader.fc.Downloader;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Download extends JProgressBar {
	public Download(Downloader downloader) {
		super(0, 100);
		setStringPainted(true);
		downloader.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("progress")) {
					setValue((Integer) evt.getNewValue());
				}
			}
		});
	}
}
