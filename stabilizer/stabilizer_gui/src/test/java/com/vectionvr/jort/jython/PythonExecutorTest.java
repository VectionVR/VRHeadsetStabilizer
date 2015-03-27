package com.vectionvr.jort.jython;

import java.util.ArrayList;
import java.util.List;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author nico
 */
@Ignore
public class PythonExecutorTest {

    @Test
    public void getCalibrationData() {
        List<float[]> samples = new ArrayList<float[]>();
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        samples.add(new float[]{1f, 2f, 3f});
        assertThat(new PythonExecuter().getCalibrationData(samples)).hasSize(12);
    }
}
