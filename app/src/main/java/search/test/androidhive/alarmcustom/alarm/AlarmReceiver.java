package search.test.androidhive.alarmcustom.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import search.test.androidhive.alarmcustom.app.App;
import search.test.androidhive.alarmcustom.widget.Actions;
import trikita.jedux.Action;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        App.dispatch(new Action<>(Actions.Alarm.WAKEUP));
    }
}
