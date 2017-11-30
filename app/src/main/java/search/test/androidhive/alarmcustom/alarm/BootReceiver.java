package search.test.androidhive.alarmcustom.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import search.test.androidhive.alarmcustom.app.App;
import search.test.androidhive.alarmcustom.widget.Actions;
import trikita.jedux.Action;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (App.getState().alarm().on()) {
            App.dispatch(new Action<>(Actions.Alarm.RESTART_ALARM));
        }
    }
}
