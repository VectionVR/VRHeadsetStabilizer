/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vectionvr.jort.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nico
 */
class SettingsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsManager.class);
    private static final String DEFAULT = "_DEFAULT";

    static SettingsManager getSettingsManager(Object object) {
        return new SettingsManager(object);
    }

    private final Object object;
    private Preferences userPreferences;
    private List<Field> fields;

    public void reset() {
        for (Field field : fields) {
            resetFieldContent(field);
        }
    }

    private SettingsManager(Object object) {
        this.object = object;
        this.fields = asList(object.getClass().getDeclaredFields());
        userPreferences = Preferences.userNodeForPackage(object.getClass());
        try {
            extractFieldsContent();
        } catch (Exception ex) {
            LOGGER.warn("Unable to retreive class data from preferences", ex);
        }
    }

    private void extractFieldsContent() throws Exception {
        for (Field field : fields) {
            extractFieldContent(field);
        }
    }

    private void extractFieldContent(Field field) {
        try {
            field.setAccessible(true);
            final Object theField = field.get(object);
            if (theField != null) {
                if (theField.getClass().isAssignableFrom(JTextField.class)) {
                    handleTextField(field, ((JTextField) theField));
                } else if (theField.getClass().isAssignableFrom(JFormattedTextField.class)) {
                    handleTextField(field, ((JTextField) theField));
                } else if (theField.getClass().isAssignableFrom(JCheckBox.class)) {
                    handleCheckbox(field, ((JCheckBox) theField));
                } else if (theField.getClass().isAssignableFrom(JComboBox.class)) {
                    handleComboBox(field, ((JComboBox) theField));
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Unable to extract field content", ex);
        }
    }

    private void handleTextField(final Field field, final JTextField textField) {
        userPreferences.put(field.getName() + DEFAULT, textField.getText());
        textField.setText(userPreferences.get(field.getName(), textField.getText()));
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChange();
            }

            public void handleChange() {
                userPreferences.put(field.getName(), textField.getText());
            }
        });
    }

    private void handleCheckbox(final Field field, final JCheckBox checkBox) {
        userPreferences.putBoolean(field.getName() + DEFAULT, checkBox.isSelected());
        checkBox.setSelected(userPreferences.getBoolean(field.getName(), checkBox.isSelected()));
        checkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                userPreferences.putBoolean(field.getName(), checkBox.isSelected());
            }
        });
    }

    private void handleComboBox(final Field field, final JComboBox comboBox) {
        userPreferences.putInt(field.getName() + DEFAULT, comboBox.getSelectedIndex());
        comboBox.setSelectedIndex(userPreferences.getInt(field.getName(), comboBox.getSelectedIndex()));
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPreferences.putInt(field.getName(), comboBox.getSelectedIndex());
            }
        });
    }

    private void resetFieldContent(Field field) {
        try {
            field.setAccessible(true);
            final Object theField = field.get(object);
            if (theField != null) {
                if (theField.getClass().isAssignableFrom(JTextField.class)) {
                    ((JTextField) theField).setText(userPreferences.get(field.getName() + DEFAULT, ""));
                } else if (theField.getClass().isAssignableFrom(JFormattedTextField.class)) {
                    ((JTextField) theField).setText(userPreferences.get(field.getName() + DEFAULT, ""));
                } else if (theField.getClass().isAssignableFrom(JCheckBox.class)) {
                    ((JCheckBox) theField).setSelected(userPreferences.getBoolean(field.getName() + DEFAULT, false));
                } else if (theField.getClass().isAssignableFrom(JComboBox.class)) {
                    ((JComboBox) theField).setSelectedIndex(userPreferences.getInt(field.getName() + DEFAULT, 0));
                }
                userPreferences.remove(field.getName());
            }
        } catch (Exception ex) {
            LOGGER.error("Unable to reset field content", ex);
        }
    }

    public void storeValue(String key, String value) {
        userPreferences.put("_SPECIFIC_" + key, value);
    }

    public String getValue(String key) {
        return userPreferences.get("_SPECIFIC_" + key, null);
    }

    public void deleteValue(String key) {
        userPreferences.remove("_SPECIFIC_" + key);
    }

}
