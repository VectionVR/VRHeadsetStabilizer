package com.vectionvr.jort.gui;

import com.jogamp.opengl.util.FPSAnimator;
import com.vectionvr.jort.jogl.CameraViews;
import com.vectionvr.jort.jogl.WorldScene;
import com.vectionvr.jort.serial.ImuCalibration;
import com.vectionvr.jort.serial.SampleData;
import com.vectionvr.jort.serial.SensorException;
import java.awt.BorderLayout;
import static java.lang.Math.toRadians;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
 
/**
 *
 * @author nico
 */
public class CalibrationDataGatheringDialog extends javax.swing.JDialog {

    private static final int MAX_STEPS = 24;
    private int currentStep = 1;
    private final SampleData samples = new SampleData();
    private String portName;
    private ImuCalibration calibration;
    private static final float[][] ANGLES = new float[][]{
        new float[]{0.0f, 1.0f, 0.0f, 0.0f},
        new float[]{0.0f, 1.0f, 0.0f, 90.0f},
        new float[]{0.0f, 1.0f, 0.0f, 180.0f},
        new float[]{0.0f, 1.0f, 0.0f, 270.0f},
        new float[]{0.0f, 0.0f, 1.0f, 180.0f},
        new float[]{0.707f, 0.0f, 0.707f, 180.0f},
        new float[]{1.0f, 0.0f, 0.0f, 180.0f},
        new float[]{-0.707f, 0.0f, 0.707f, 180.0f},
        new float[]{-1.0f, 0.0f, 0.0f, 90.0f},
        new float[]{-0.58f, 0.58f, 0.58f, 120.0f},
        new float[]{0.0f, 0.707f, 0.707f, 180.0f},
        new float[]{0.58f, 0.58f, 0.58f, 240.0f},
        new float[]{0.0f, 0.707f, -0.707f, 180.0f},
        new float[]{0.58f, 0.58f, -0.58f, 120.0f},
        new float[]{1.0f, 0.0f, 0.0f, 90.0f},
        new float[]{-0.58f, 0.58f, -0.58f, 240.0f},
        new float[]{-0.58f, 0.58f, -0.58f, 120.0f},
        new float[]{-0.707f, 0.707f, 0, 180.0f},
        new float[]{-0.58f, 0.58f, 0.58f, 240.0f},
        new float[]{0.0f, 0.0f, 1.0f, 270.0f},
        new float[]{-0.58f, -0.58f, 0.58f, 120.0f},
        new float[]{-0.707f, -0.707f, 0.0f, 180.0f},
        new float[]{-0.58f, -0.58f, -0.58f, 240.0f},
        new float[]{0.0f, 0.0f, -1.0f, 270.0f}};
    private final FPSAnimator animator;
    private final WorldScene scene = new WorldScene();

    /**
     * Creates new form CalibrationDialog
     */
    public CalibrationDataGatheringDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);
        caps.setSampleBuffers(true);
        final GLJPanel canvas = new GLJPanel(caps);
        scene.setBackgroundColor(new float[]{0f,0f,0f,1f});
        scene.setCamera(CameraViews.FirstPerson);
        scene.setLineWidth(2f);
        scene.setCubeSize(10f);
        scene.setGridEnabled(false);
        animator = new FPSAnimator(canvas, 100);
        animator.setIgnoreExceptions(true);
        animator.setPrintExceptions(false);
        canvas.addGLEventListener(scene);
        imagePanel.add(canvas, BorderLayout.CENTER);
        animator.start();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nextButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        stepProgressBar = new javax.swing.JProgressBar();
        stepLabel = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        imagePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Calibrate - Step 1");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        nextButton.setText("Next >>");
        nextButton.setMaximumSize(new java.awt.Dimension(100, 29));
        nextButton.setMinimumSize(new java.awt.Dimension(100, 29));
        nextButton.setPreferredSize(new java.awt.Dimension(100, 29));
        nextButton.setSize(new java.awt.Dimension(100, 29));
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setMaximumSize(new java.awt.Dimension(100, 29));
        cancelButton.setMinimumSize(new java.awt.Dimension(100, 29));
        cancelButton.setPreferredSize(new java.awt.Dimension(100, 29));
        cancelButton.setSize(new java.awt.Dimension(100, 29));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        stepProgressBar.setMaximum(24);
        stepProgressBar.setMinimum(1);

        stepLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stepLabel.setText("1 / 24");

        resetButton.setText("Reset");
        resetButton.setEnabled(false);
        resetButton.setMaximumSize(new java.awt.Dimension(100, 29));
        resetButton.setMinimumSize(new java.awt.Dimension(100, 29));
        resetButton.setPreferredSize(new java.awt.Dimension(100, 29));
        resetButton.setSize(new java.awt.Dimension(100, 29));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        imagePanel.setMaximumSize(new java.awt.Dimension(280, 278));
        imagePanel.setMinimumSize(new java.awt.Dimension(280, 278));
        imagePanel.setSize(new java.awt.Dimension(280, 278));
        imagePanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(stepLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stepProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        samples.clear();
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        ++currentStep;
        try {
            calibration.connect();
            SampleData sample = calibration.getSample();
            calibration.disconnect();
            samples.withAccelerometerValues(sample.getAccelerometerValues().get(0));
            samples.withMagnetometerValues(sample.getMagnetometerValues().get(0));
            resetButton.setEnabled(true);
            if (currentStep <= MAX_STEPS) {
                updateLabels();
                if (currentStep == MAX_STEPS) {
                    nextButton.setText("Finish");
                }
            } else {
                dispose();
            }
        } catch (SensorException e) {
            // handle read error
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void updateLabels() {
        stepLabel.setText(currentStep + " / " + MAX_STEPS);
        stepProgressBar.setValue(currentStep);
        updatePanel();
    }

    private void updatePanel() {
        scene.update(ANGLES[currentStep - 1], (float)toRadians(ANGLES[currentStep - 1][3]));
    }

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        currentStep = 1;
        samples.clear();
        updateLabels();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        calibration = new ImuCalibration(portName);
        updateLabels();
    }//GEN-LAST:event_formWindowOpened

    public boolean isCompleted() {
        return samples.size() == MAX_STEPS;
    }

    public SampleData getSamples() {
        return samples;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JProgressBar stepProgressBar;
    // End of variables declaration//GEN-END:variables

}