package downloader.ui;

import downloader.fc.Downloader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class Main extends JFrame {
	public Main(String title, String[] urls) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// set dimension of JFrame
		setSize(400, 300);

		// set layout
		StackLayout layout = new StackLayout();
		setLayout(layout);


		// launch downloader in threads and give it to Download ProgressBar
		for (String url : urls){
			// add url at the top of each Download
			JLabel label = new JLabel(url);
			add(label);
			Downloader downloader = new Downloader(url);
			add(new Download(downloader));
			downloader.execute();
			// add stop and pause button at the right of each Download
			JPanel panel = new JPanel();
			JButton stop = new JButton("Stop");
			stop.addActionListener(e -> downloader.cancel(true));
			JButton pause = new JButton("Pause");
			pause.addActionListener(e -> {
				downloader.pause();
				if (downloader.isPaused()) {
					pause.setText("Resume");
				} else {
					pause.setText("Pause");
				}
							}
			);
			panel.add(stop);
			panel.add(pause);
			add(panel, BorderLayout.EAST);
		}

		// add a side panel with an input field to add new download and a button to start it
		JPanel sidePanel = new JPanel();

		JTextField inputField = new JTextField();
		inputField.setPreferredSize(new Dimension(300, 20));
		JButton addButton = new JButton("Add");
		addButton.addActionListener(e -> {
			System.out.println(inputField.getText());
			Downloader downloader = new Downloader(inputField.getText());
			add(new Download(downloader));
			downloader.execute();
			inputField.setText("");
		});
		sidePanel.add(inputField);
		sidePanel.add(addButton);
		add(sidePanel);
	}

	public static void main(String[] args) {
		final String[] urls = args;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main("Download manager", urls ).setVisible(true);
			}
		});
	}

}
