package tinf13b4.forum.databaseConfigurator;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

	private JPanel contentPane;

	private JTextField textHostname;
	private JTextField textPort;
	private JTextField textUsername;
	private JButton btnSave;
	private JButton btnOpen;
	private JPasswordField passwordField;
	private JTextField textDatabaseName;

	public String getHostname() {
		return textHostname.getText();
	}

	public void setHostname(String textHostname) {
		this.textHostname.setText(textHostname);
	}

	public int getPort() {
		return Integer.parseInt(textPort.getText());
	}

	public void setPort(String textPort) {
		this.textPort.setText(textPort);
	}

	public String getUsername() {
		return textUsername.getText();
	}

	public void setUsername(String textUsername) {
		this.textUsername.setText(textUsername);
	}

	public String getPassword() {
		char[] password = passwordField.getPassword();
		String passwordOutput = "";
		for (char character : password)
			passwordOutput += character;
		return passwordOutput;
	}

	public void setPassword(String passwordField) {
		this.passwordField.setText(passwordField);
	}

	public String getDatabaseName() {
		return textDatabaseName.getText();
	}

	public void setDatabaseName(String textDatabaseName) {
		this.textDatabaseName.setText(textDatabaseName);
	}

	public View() {
		super("Configurator");
		initForm();
	}

	/**
	 * Initialize Frame
	 */
	public void initForm() {
		setTitle("Database Configurator");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 317, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		final JLabel lblHostname = new JLabel("Hostname:");
		final JLabel lblPort = new JLabel("Port:");
		final JLabel lblUsername = new JLabel("Username:");
		final JLabel lblPassword = new JLabel("Password:");
		final JLabel lblDatabaseName = new JLabel("Databasename:");

		btnSave = new JButton("Save As");
		btnOpen = new JButton("Open");

		textHostname = new JTextField();
		textHostname.setColumns(10);

		textPort = new JTextField();
		textPort.setColumns(10);

		textUsername = new JTextField();
		textUsername.setColumns(10);

		textDatabaseName = new JTextField();
		textDatabaseName.setColumns(10);

		passwordField = new JPasswordField();

		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.TRAILING)
												.addComponent(lblPassword)
												.addComponent(lblUsername)
												.addComponent(lblDatabaseName)
												.addComponent(lblPort)
												.addComponent(lblHostname))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.LEADING,
														false)
												.addComponent(textHostname)
												.addComponent(textPort)
												.addComponent(textDatabaseName)
												.addComponent(textUsername)
												.addComponent(passwordField))
								.addContainerGap(97, Short.MAX_VALUE))
				.addGroup(
						Alignment.TRAILING,
						groupLayout.createSequentialGroup()
								.addContainerGap(165, Short.MAX_VALUE)
								.addComponent(btnOpen)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSave).addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								Alignment.LEADING,
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblHostname)
														.addComponent(
																textHostname,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblPort)
														.addComponent(
																textPort,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDatabaseName)
														.addComponent(
																textDatabaseName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblUsername)
														.addComponent(
																textUsername,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblPassword)
														.addComponent(
																passwordField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(81, Short.MAX_VALUE))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(194, Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(btnSave)
														.addComponent(btnOpen))));
		contentPane.setLayout(groupLayout);
	}

	public void btnSaveEventListener(ActionListener listener) {
		this.btnSave.addActionListener(listener);
	}

	public void btnOpenEventListener(ActionListener listener) {
		this.btnOpen.addActionListener(listener);
	}
}
