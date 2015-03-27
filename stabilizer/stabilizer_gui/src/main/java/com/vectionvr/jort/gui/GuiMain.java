/**
 * Copyright (C) 2014 Bnome SPRL (info@bnome.be)
 *
 * This file is part of VectionVR Stabilizer.
 *
 * VectionVR Stabilizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VectionVR Stabilizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VectionVR Stabilizer.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vectionvr.jort.gui;

import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
public class GuiMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuiMain.class);
    public static void main(String args[]) {
        System.setProperty("apple.laf.useScreenMenuBar", "false");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Vection-VR Stabilizer");
        try {
        	setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            LOGGER.error("Unable to start program", ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final MainFrame mainFrame = new MainFrame();
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setVisible(true);
                } catch (Exception ex) {
                    LOGGER.error("Unable to launch thread", ex);
                }
            }
        });
    }
}
