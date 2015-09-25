package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.github.awvalenti.bauhinia.forficata.ForficataException;
import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow implements NitidaOutput {

	private final JFrame frame;
	private final StatePanel statePanel;
	private final ConnectButton connectButton;
	private final JEditorPane logText;

	public NitidaWindow(ProjectProperties projectProperties, ConnectButton connectButton) {
		this.connectButton = connectButton;
		this.statePanel = new StatePanel();

		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(statePanel, BorderLayout.NORTH);

		logText = new JEditorPane();
		logText.setEditable(false);

		JPanel logPanel = new JPanel(new BorderLayout());
		logPanel.setBorder(BorderFactory.createTitledBorder("Log"));
		logPanel.add(logText);
		frame.add(logPanel, BorderLayout.CENTER);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(connectButton);
		frame.add(actions, BorderLayout.SOUTH);
	}

	@Override
	public void run() {
		frame.setVisible(true);
	}

	@Override
	public void searchStarted() {
		statePanel.setSearchingState();
		connectButton.setEnabled(false);
	}

	@Override
	public void identifyingBluetoothDevice(String deviceAddress, String deviceClass) {
		appendToLog(String.format("Device found: %s - %s (TODO: add a state label)",
				deviceAddress, deviceClass));
	}

	@Override
	public void wiimoteFound() {
		appendToLog("Wiimote found. Connecting... (TODO: add a state label)");
	}

	@Override
	public void robotActivated() {
		appendToLog("Connected. Robot activated!");
		statePanel.setActiveState();
		connectButton.setEnabled(false);
	}

	@Override
	public void unableToFindWiimote() {
		appendToLog("Unable to find Wiimote");
		statePanel.setIdleState();
		connectButton.setEnabled(true);
	}

	@Override
	public void errorOccurred(ForficataException e) {
		appendToLog(String.format("An unexpected exception occurred: %s", e));
	}

	private void appendToLog(String content) {
		Document doc = logText.getDocument();
		try {
			doc.insertString(doc.getLength(), content, null);
			doc.insertString(doc.getLength(), "\n", null);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

}
