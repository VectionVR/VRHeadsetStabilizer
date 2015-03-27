package com.vectionvr.jort.serial;

import static com.vectionvr.jort.license.LicenseManager.getLicenseManager;
import static java.nio.ByteBuffer.wrap;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import jssc.SerialPort;
import jssc.SerialPortList;

/**
 *
 * @author nico
 * @version 1
 */
public final class SerialDeviceAnalyser {

    public final String getDevicePort() {
        for (final String portName : SerialPortList.getPortNames()) {
            final int portId = getPortId(portName);
            if (portId > 0 && validatePortId(portId)) {
                return portName;
            }
        }
        return null;
    }

    private final int getPortId(final String portName) {
        final SerialPortAnalyser serialPortAnalyser = new SerialPortAnalyser(portName);
        try {
            serialPortAnalyser.openSerialPort();
            if (serialPortAnalyser.validateActionAnswerMatches(new Action(230), "TSS-MUSB\\s+.+", 32)
                    && serialPortAnalyser.validateActionAnswerMatches(new Action(223), "[0-9]{2}[A-Za-z]{3}[0-9]{4}[A-Za-z0-9]{3}", 12)) {
                final byte[] id = new byte[4];
                serialPortAnalyser.applyActionWithAnswer(new Action(237), id);
                return wrap(id).getInt();
            }
        } catch (final SensorException e) {
        } finally {
            try {
                serialPortAnalyser.closeSerialPort();
            } catch (final SensorException e) {
            }
        }
        return 0;
    }

    private final boolean validatePortId(final int portId) {
        return getLicenseManager().getUid() == portId;
    }

    private final class SerialPortAnalyser extends SerialClient {

        @Override
        protected int getSerialSpeed() {
            return SerialPort.BAUDRATE_256000;
        }

        SerialPortAnalyser(final String portName) {
            super(portName);
        }

        final boolean validateActionAnswerMatches(final Action action, final String patternString, final int length) {
            try {
                final byte[] answer = new byte[length];
                applyActionWithAnswer(action, answer);
                if (Pattern.compile(patternString).matcher(new String(answer, "ISO-8859-1")).matches()) {
                    return true;
                }
            } catch (UnsupportedEncodingException e) {
            } catch (SensorException e) {
            }
            return false;
        }
    }
}
