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

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
class SettingsManager {

    private static final String DEFAULT = "_DEFAULT";

    static SettingsManager getSettingsManager(Object object) {
        return new SettingsManager(object);
    }

    private final Object object;
    private final Preferences userPreferences;
    private final List<Field> fields;

    public void reset() {
        for (Field field : fields) {
            try {
                resetFieldContent(field);
            } catch (Exception e) {
                userPreferences.remove(field.getName());
            }
        }
    }

    private SettingsManager(Object object) {
        this.object = object;
        this.fields = asList(object.getClass().getDeclaredFields());
        userPreferences = Preferences.userNodeForPackage(object.getClass());
        extractFieldsContent();
    }

    private void extractFieldsContent() {
        for (Field field : fields) {
            try {
                extractFieldContent(field);
            } catch (Exception e) {
                userPreferences.remove(field.getName());
            }
        }
    }

    private void extractFieldContent(Field field) throws Exception {
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

    private void resetFieldContent(Field field) throws Exception {
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
    }
}
