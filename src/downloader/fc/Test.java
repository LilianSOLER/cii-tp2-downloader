/* downloader.fc.Test
 * (c) blanch@imag.fr 2021–2023                                            */

package downloader.fc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import downloader.fc.Downloader;

public class Test {
	public static void main(String argv[]) {
		for(String url: argv) {
			Downloader downloader;
			try {
				downloader = new Downloader(url);
			}
			catch(RuntimeException e) {
				System.err.format("skipping %s %s\n", url, e);
				continue;
			}
			System.out.format("Downloading %s:", downloader);
			
			downloader.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					System.out.print(".");
					System.out.flush();
				}
			});
			
			String filename;
			try {
				filename = downloader.download();
			}
			catch(InterruptedException e) {
				System.err.println("failed!");
				continue;
			}
			System.out.format("into %s\n", filename);
		}
	}
}
