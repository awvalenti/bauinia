package com.github.awvalenti.bauhinia.nitida.model;

import com.github.awvalenti.bauhinia.coronata.CoronataFailure;
import com.github.awvalenti.bauhinia.coronata.Phase;

public interface NitidaOutputListener {

	void run();

	void running(Phase phase);

	void success(Phase phase);

	void failure(Phase phase, CoronataFailure failure);

	void wiimoteDisconnected();

}
