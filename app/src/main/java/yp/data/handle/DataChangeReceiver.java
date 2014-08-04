package yp.data.handle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yashdeep.patel on 8/3/2014.
 */
public class DataChangeReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getName(), "on broadcast receive: "+intent.getExtras().toString());
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            if(bundle.containsKey("wifiEnable")){
                YPUtils.setWifiEnable(context, bundle.getBoolean("wifiEnable"));
            }
            if(bundle.containsKey("mobileDataEnable")){
                YPUtils.setMobileDataEnabled(context, bundle.getBoolean("mobileDataEnable"));
            }
        }
    }
}
