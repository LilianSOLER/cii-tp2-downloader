package downloader.ui;

import downloader.fc.Downloader;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Download extends JProgressBar {
	public Download(Downloader downloader) {
		// show progress
		super(0, 100);
		setStringPainted(true);

		// change color when done
		downloader.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("progress")) {
					setValue((Integer) evt.getNewValue());
				} else if (evt.getPropertyName().equals("state")) {
					if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
						setForeground(Color.RED);
						setString(downloader.getFilename());
					}
				}
			}
		});
	}
}
