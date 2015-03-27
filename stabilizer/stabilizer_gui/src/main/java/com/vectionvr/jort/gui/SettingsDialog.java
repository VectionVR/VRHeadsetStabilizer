/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vectionvr.jort.gui;

import static com.vectionvr.jort.gui.SettingsManager.getSettingsManager;
import static java.lang.Integer.parseInt;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

/**
 *
 * @author nico
 */
public class SettingsDialog extends javax.swing.JDialog {
    private final SettingsManager settingsManager;

    /**
     * Creates new form SettingsDialog
     */
    public SettingsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        settingsManager = getSettingsManager(this);
    }

    public JComboBox getAccelerometerRange() {
        return accelerometerRange;
    }

    public JComboBox getCompassRange() {
        return compassRange;
    }

    public JComboBox getFilterModeSelector() {
        return filterModeSelector;
    }

    public JComboBox getGyroscopeRange() {
        return gyroscopeRange;
    }

    public JFormattedTextField getIntervalInput() {
        return intervalInput;
    }

    public JComboBox getRunningAverageSelector() {
        return runningAverageSelector;
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        runningAverageSelector = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        accelerometerRange = new javax.swing.JComboBox();
        gyroscopeRange = new javax.swing.JComboBox();
        compassRange = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        filterModeSelector = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        intervalInput = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");
        setResizable(false);

        runningAverageSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None (*)", "20%", "40%", "60%", "80%", "100%" }));

        jLabel6.setLabelFor(runningAverageSelector);
        jLabel6.setText("Average");
        jLabel6.setToolTipText("Smooth out sensor reading by applying averaging techniques");

        jLabel17.setLabelFor(accelerometerRange);
        jLabel17.setText("Accelerometer");
        jLabel17.setToolTipText("Choose accelerometer sensitivity. Increase this value to decrease the impact of small movements.");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        accelerometerRange.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2G (*)", "4G", "8G" }));

        gyroscopeRange.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "250 DPS", "500 DPS", "2000 DPS (*)" }));
        gyroscopeRange.setSelectedIndex(2);

        compassRange.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ".88G", "1.3G (*)", "1.9G", "2.5G", "4.0G", "4.7G", "5.6G", "8.1G" }));
        compassRange.setSelectedIndex(1);
        compassRange.setToolTipText("");

        jLabel18.setLabelFor(gyroscopeRange);
        jLabel18.setText("Gyro");
        jLabel18.setToolTipText("Choose gyroscope sensitivity");

        jLabel19.setLabelFor(compassRange);
        jLabel19.setText("Compass");
        jLabel19.setToolTipText("Choose compass sensitivity");

        jLabel20.setLabelFor(filterModeSelector);
        jLabel20.setText("Algorithm");
        jLabel20.setToolTipText("Select which stabilization algorithm to use, the slowest is the most accurate");

        filterModeSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Slow", "Fast (*)" }));
        filterModeSelector.setSelectedIndex(1);

        jLabel21.setLabelFor(intervalInput);
        jLabel21.setText("Interval");
        jLabel21.setToolTipText("Interval between each data packet (0 means as fast as device can)");

        intervalInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
        intervalInput.setText("1000");
        intervalInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                intervalInputFocusLost(evt);
            }
        });

        jLabel1.setText("µs");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gyroscopeRange, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(compassRange, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accelerometerRange, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runningAverageSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterModeSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(intervalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(filterModeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(intervalInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(runningAverageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(accelerometerRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gyroscopeRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compassRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void intervalInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_intervalInputFocusLost
        try {
            parseInt(intervalInput.getText());
        } catch (NumberFormatException ex) {
            intervalInput.setText("0");
        }
    }//GEN-LAST:event_intervalInputFocusLost
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accelerometerRange;
    private javax.swing.JComboBox compassRange;
    private javax.swing.JComboBox filterModeSelector;
    private javax.swing.JComboBox gyroscopeRange;
    private javax.swing.JFormattedTextField intervalInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox runningAverageSelector;
    // End of variables declaration//GEN-END:variables
}