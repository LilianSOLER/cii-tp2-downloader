package downloader.ui;

import downloader.fc.Downloader;

import javax.swing.*;

public class Main extends JFrame {
	public Main(String title, String[] urls) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// set dimension of JFrame
		setSize(400, 300);

		// set layout of JFrame
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		// launch downloader in threads and give it to Download ProgressBar
		for (String url : urls){
			Downloader downloader = new Downloader(url);
			add(new Download(downloader));
			downloader.execute();
		}

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
