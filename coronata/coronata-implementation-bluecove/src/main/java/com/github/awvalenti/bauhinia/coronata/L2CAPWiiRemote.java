package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;


class L2CAPWiiRemote implements CoronataWiiRemote {

	private final L2CAPConnection output;

	private byte litLedIndex = -1;
	private byte vibrationState = 0x00;

	public L2CAPWiiRemote(L2CAPConnection input, L2CAPConnection output, CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {
		this.output = output;
		new ButtonHandlerThread(input, output, buttonObserver, disconnectionObserver).start();
	}

	@Override
	public void turnLedOn(int ledIndex) {
		this.litLedIndex = (byte) (ledIndex % 4);
		realizeLedAndOrVibration();
	}

	@Override
	public void startVibration() {
		vibrationState = 0x01;
		realizeLedAndOrVibration();
	}

	@Override
	public void stopVibration() {
		vibrationState = 0x00;
		realizeLedAndOrVibration();
	}

	private void realizeLedAndOrVibration() {
		sendDataToWiiRemote((byte) 0x11, new byte[] { (byte) (vibrationState | (1 << (litLedIndex + 4))) });
	}

	private void sendDataToWiiRemote(byte commandCode, byte[] data) {
		byte[] dataWithExtraBytes = new byte[2 + data.length];
		dataWithExtraBytes[0] = 0x52;
		dataWithExtraBytes[1] = commandCode;
		System.arraycopy(data, 0, dataWithExtraBytes, 2, data.length);
		try {
			output.send(dataWithExtraBytes);
		} catch (IOException e) {
			// This should happen only if user tries to send data to
			// an already disconnected Wii Remote. The exception is ignored.
		}
	}

}
