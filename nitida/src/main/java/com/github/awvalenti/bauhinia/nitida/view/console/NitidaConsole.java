package com.github.awvalenti.bauhinia.nitida.view.console;

import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaConsole implements NitidaOutput {

	private final ProjectProperties projectProperties;

	public NitidaConsole(ProjectProperties projectProperties) {
		this.projectProperties = projectProperties;
	}

	@Override
	public void run() {
		System.out.printf("nitida %s\n\n", projectProperties.getProjectVersion());
	}

	@Override
	public void searchStarted() {
		System.out.println("Searching for Wiimote...");
	}

	@Override
	public void identifyingBluetoothDevice(String deviceAddress, String deviceClass) {
		System.out.printf("Found a Bluetooth device at %s: %s\n", deviceAddress, deviceClass);
	}

	@Override
	public void wiimoteFound() {
		System.out.println("Found Wiimote. Connecting...");
	}

	@Override
	public void robotActivated() {
		System.out.println("Connected to Wiimote. Robot activated!");
	}

	@Override
	public void unableToFindWiimote() {
		System.out.println("Unable to find a Wiimote");
	}

	@Override
	public void errorOccurred(ForficataException e) {
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
