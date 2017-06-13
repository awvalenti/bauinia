package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

class CoronataConfig implements ReadableCoronataConfig {

	private Boolean synchronous;
	private Integer wiiRemotesExpected;
	
	private final ObserversAggregation observers = new ObserversAggregation();
	private final EventsMediator mediator = new EventsMediator(observers);

	@Override
	public boolean isSynchronous() {
		return synchronous;
	}

	public void setSynchronous(boolean synchronous) {
		this.synchronous = synchronous;
	}

	@Override
	public int getWiiRemotesExpected() {
		return wiiRemotesExpected;
	}

	public void setWiiRemotesExpected(int wiiRemotesExpected) {
		this.wiiRemotesExpected = wiiRemotesExpected;
	}

	@Override
	public WiiRemoteFullListener getWiiRemoteListener() {
		return mediator;
	}

	@Override
	public CoronataFullObserver getCoronataObserver() {
		return mediator;
	}

	public void addObserver(WiiRemoteButtonListener l) {
		observers.buttonListeners.add(l);
	}

	public void addObserver(CoronataWiiRemoteConnectionObserver o) {
		observers.connectionObservers.add(o);
	}

	public void addObserver(WiiRemoteDisconnectionListener l) {
		observers.disconnectionListeners.add(l);
	}

	public void addObserver(CoronataFullObserver o) {
		observers.fullObservers.add(o);
	}

	public void addObserver(CoronataPhaseObserver o) {
		observers.phaseObservers.add(o);
	}

	public void addObserver(CoronataConnectionStateObserver o) {
		// XXX Should not have to call this here; calling this triggers the
		// configuration of initial state on observer. But the observer
		// should configure itself upon construction instead of depending
		// on this method being called.
		o.enteredIdleState();

		observers.connectionStateObservers.add(o);
	}

}
