package search.test.androidhive.alarmcustom.alarm;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import search.test.androidhive.alarmcustom.widget.GsonAdaptersState;
import search.test.androidhive.alarmcustom.widget.ImmutableState;
import search.test.androidhive.alarmcustom.widget.State;
import trikita.jedux.Action;
import trikita.jedux.Store;


public class PersistanceController implements Store.Middleware<Action, State> {

    private final SharedPreferences mPreferences;
    private final Gson mGson;

    public PersistanceController(Context context) {
        mPreferences = context.getSharedPreferences("data", 0);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new GsonAdaptersState());
        mGson = gsonBuilder.create();
    }

    public State getSavedState() {
        if (mPreferences.contains("data")) {
            String json = mPreferences.getString("data", "");
            return mGson.fromJson(json, ImmutableState.class);
        }
        return null;
    }

    @Override
    public void dispatch(Store<Action, State> store, Action action, Store.NextDispatcher<Action> next) {
        next.dispatch(action);
        String json = mGson.toJson(store.getState());
        mPreferences.edit().putString("data", json).apply();
    }
}
