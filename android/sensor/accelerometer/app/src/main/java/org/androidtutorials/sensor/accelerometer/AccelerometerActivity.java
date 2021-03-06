

package org.androidtutorials.sensor.accelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This application demonstrates access to the accelerometer of Android devices.
 * The current (x, y, z) values of the accelerometer are shown in TextViews on
 * the main screen of the activity. Note that AccelerometerActivity implements
 * the interface android.hardware.SensorEventListener. Implementing this
 * interface requires to override methods onAccuracyChanged() and
 * onSensorChanged().
 */
public class AccelerometerActivity extends Activity implements SensorEventListener {

    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        xValue = (TextView) findViewById(R.id.lblX);
        yValue = (TextView) findViewById(R.id.lblY);
        zValue = (TextView) findViewById(R.id.lblZ);

        /*
         * Retrieve the SensorManager.
         */
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        /*
         * Retrieve the default Sensor for the accelerometer.
         */
        Sensor sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        /*
         * Register this activity as the listener for accelerometer events.
         */
        sensorManager
                .registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
     * This method will be called whenever the accuracy of the sensor has
     * changed. This is not applicable for the accelerometer sensor so the
     * implementation is left empty.
     */
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Intentionally left empty

    }

    /*
     * This method will be called whenever there are sensor events (in this case
     * accelerometer events).
     */
    public void onSensorChanged(SensorEvent event) {
        /*
         * The SensorEvent object contains a field 'values' which is an array of
         * float values. The size of the array depends on the kind of sensor.
         * Since this application only registers for accelerometer events, the
         * array will always be of size 3 representing the (x, y, z) values.
         */
        xValue.setText(Float.toString(event.values[0]));
        yValue.setText(Float.toString(event.values[1]));
        zValue.setText(Float.toString(event.values[2]));
    }
}