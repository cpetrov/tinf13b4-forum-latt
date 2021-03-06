
package tinf13b4.forum.databaseConfigurator;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.bind.DatatypeConverter;

public class CreateConfig {

	private static View mView;
	private static ViewModel mViewModel;
	private static BufferedReader br;

	public CreateConfig() {
		mView = new View();
		mView.pack();
		mView.setVisible(true);
		addListener();
		mViewModel = new ViewModel();
	}

	public static void main(String[] args) {
		new CreateConfig();
	}

	private static void writeFile() throws IOException {
		File file = new File(mViewModel.getPath().toString());
		if (file.exists())
			file.delete();
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		String[] data = {
				mViewModel.getHostname(),
				Integer.toString(mViewModel.getPort()),
				mViewModel.getDatabasename(),
				mViewModel.getUsername(),
				mViewModel.getPassword()
		};
		for (int i = 0; i < data.length; i++) {
			data[i] = encrypt(data[i]);
		}
		for (String line : data)
			bw.write(line + "\n");
		bw.close();
	}

	private static void testFilepath() throws IOException {
		File file = new File(mViewModel.getPath().toString());
		if (file.exists()) {
			br = new BufferedReader(new FileReader(file));
			int i = 0;
			while (br.ready()) {
				String text = null;
				switch (i) {
					case 0 :
						text = br.readLine();
						mViewModel.setHostname(text);
						mView.setHostname(text);
						break;
					case 1 :
						mViewModel.setPort(Integer.parseInt(text));
						mView.setPort(text);
						break;
					case 2 :
						mViewModel.setDatabasename(br.readLine());
						mView.setDatabaseName(text);
						break;
					case 3 :
						mViewModel.setUsername(br.readLine());
						mView.setUsername(text);
						break;
					case 4 :
						mViewModel.setPassword(br.readLine());
						mView.setPassword(text);
						break;
				}
				i++;
			}
		}
	}

	private static void addListener() {
		mView.btnSaveEventListener(new SaveEventListener());
		mView.btnOpenEventListener(new OpenEventListener());
	}

	public static String encrypt(String s) {
		return DatatypeConverter.printBase64Binary(s.getBytes());
	}

	public static String decrypt(String s) {
		return new String(DatatypeConverter.parseBase64Binary(s));
	}

	private static class SaveEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog((Component) e.getSource());
				mViewModel.setPath(chooser.getSelectedFile().toPath());
				mViewModel.setHostname(mView.getHostname());
				mViewModel.setPort(mView.getPort());
				mViewModel.setUsername(mView.getUsername());
				mViewModel.setPassword(mView.getPassword());
				mViewModel.setDatabasename(mView.getDatabaseName());
				writeFile();
			} catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("File can't be written!");
			}
		}
	}
	private static class OpenEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog((Component) e.getSource());
			mViewModel.setPath(chooser.getSelectedFile().toPath());
			try {
				testFilepath();
			} catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Can't find File!");
			}
		}
	}
}
