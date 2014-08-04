package yp.data.handle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyActivity extends ActionBarActivity implements View.OnClickListener {

    private CircleButton mobileStatus;
    private CircleButton wifiStatus;
    private Handler mHandler;

    BroadcastReceiver networkChangeBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(getClass().getName(), "On network data change..");
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    setSetupViews();
                }
            });
//            Toast.makeText(MyActivity.this, "Mobile: " + YPUtils.isMobileDataEnable(MyActivity.this) +
//                    "Wifi: " + YPUtils.isWifiEnable(MyActivity.this), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mHandler = new Handler();
        mobileStatus = (CircleButton) findViewById(R.id.mobileDataStatus);
        wifiStatus = (CircleButton) findViewById(R.id.wifiStatus);
        setSetupViews();
        mobileStatus.setOnClickListener(this);
        wifiStatus.setOnClickListener(this);
        final IntentFilter filters = new IntentFilter();
        filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filters.addAction("android.net.wifi.STATE_CHANGE");
        filters.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        super.registerReceiver(networkChangeBroadcastReceiver, filters);
    }

    private void setSetupViews() {
        setStatus(mobileStatus, YPUtils.isMobileDataEnable(this));
        setStatus(wifiStatus, YPUtils.isWifiEnable(this));
    }

    private void setStatus(CircleButton mobileStatus, boolean mobileDataEnable) {
        if (mobileDataEnable) {
            mobileStatus.setColor(getResources().getColor(R.color.enable_color));
            mobileStatus.setImageResource(R.drawable.btn_right);
        } else {
            mobileStatus.setColor(getResources().getColor(R.color.disable_color));
            mobileStatus.setImageResource(R.drawable.btn_cross);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        super.unregisterReceiver(networkChangeBroadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_help) {
            startActivity(new Intent(this, HelpActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mobileDataStatus:
                YPUtils.setMobileDataEnabled(this, !YPUtils.isMobileDataEnable(this));
                break;
            case R.id.wifiStatus:
                YPUtils.setWifiEnable(this, !YPUtils.isWifiEnable(this));
                break;
        }
    }
}
