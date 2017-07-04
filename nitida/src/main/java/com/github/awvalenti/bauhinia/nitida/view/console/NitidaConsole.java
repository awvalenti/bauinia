package com.github.awvalenti.bauhinia.nitida.view.console;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.Messages;

public class NitidaConsole implements CoronataFullObserver {

	private final ProjectProperties projectProperties;
	private final Messages messages;

	public NitidaConsole(ProjectProperties projectProperties, Messages messages) {
		this.projectProperties = projectProperties;
		this.messages = messages;
	}

	@Override
	public void coronataStarted() {
		System.out.println(messages.get("appTitle", projectProperties.getProjectVersion()));
	}

	@Override
	public void libraryLoaded() {
		System.out.println(messages.get("libraryLoaded"));
	}

	@Override
	public void searchStarted() {
		System.out.println(messages.get("searchStarted"));
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		System.out.println(messages.get("bluetoothDeviceFound", address, deviceClass));
	}

	@Override
	public void wiiRemoteIdentified() {
		System.out.println(messages.get("wiiRemoteIdentified"));
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		System.out.println(messages.get("deviceRejectedIdentification", address));
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		System.out.println(messages.get("deviceIdentifiedAsNotWiiRemote", address));
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		System.out.println(messages.get("wiiRemoteConnected"));
	}

	@Override
	public void searchFinished() {
		System.out.println(messages.get("searchFinished"));
	}

	@Override
	public void wiiRemoteDisconnected() {
		System.out.println(messages.get("wiiRemoteDisconnected"));
	}

	@Override
	public void errorOccurred(CoronataException e) {
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
