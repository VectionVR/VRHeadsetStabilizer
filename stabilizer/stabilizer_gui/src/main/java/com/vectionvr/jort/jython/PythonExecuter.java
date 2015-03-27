package com.vectionvr.jort.jython;

import static org.python.core.Py.getSystemState;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.python.core.PyString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nico
 * @version 1
 */
public class PythonExecuter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PythonExecuter.class);

    public List<Double> getCalibrationData(List<float[]> samples) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
            getSystemState().path.append(new PyString(getClass().getClassLoader().getResource("python_lib").getFile()));
            engine.eval(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("csensor.py")));
            engine.eval("vec = generate_target_list(" + convertToVec3(new StringBuilder(), samples.get(0)) + ")");
            engine.eval("result = calc_gradient_descent_params([" + convertToVec3List(samples) + "], vec)");
            return (List<Double>) engine.get("result");
        } catch (ScriptException e) {
            LOGGER.error("Unable to execute script", e);
        }
        return new ArrayList<Double>();
    }

    private String convertToVec3List(List<float[]> samples) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < samples.size(); ++i) {
            float[] sample = samples.get(i);
            convertToVec3(stringBuilder, sample);
            if (i < samples.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    private StringBuilder convertToVec3(StringBuilder stringBuilder, float[] sample) {
        return stringBuilder.append("vec3( ")
                .append(sample[0]).append(", ")
                .append(sample[1]).append(", ")
                .append(sample[2]).append(")");
    }
}
