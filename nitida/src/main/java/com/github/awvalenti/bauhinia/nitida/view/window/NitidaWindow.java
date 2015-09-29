package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow {

	private final JFrame frame;

	public NitidaWindow(ProjectProperties projectProperties,
			ApplicationStatePanel applicationStatePanel,
			LogPanel logPanel, ActionPanel actionPanel) {
		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(320, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(applicationStatePanel, BorderLayout.NORTH);
		frame.add(logPanel, BorderLayout.CENTER);
		frame.add(actionPanel, BorderLayout.SOUTH);
	}

	public void run() {
		frame.setVisible(true);
	}

}
