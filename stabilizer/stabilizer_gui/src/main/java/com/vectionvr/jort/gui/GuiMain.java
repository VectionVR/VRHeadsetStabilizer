package com.vectionvr.jort.gui;

import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Main program entry point -- this should never change -- or just slightly
 * @author nico
 * @version 1
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
