package finance.joypay.com.jopayfinance.Utils;

import android.app.Application;

import finance.joypay.com.jopayfinance.Service.IJoyPayService;
import retrofit.RestAdapter;

/**
 * Created by Pandiyan M on 1/19/2018.
 */

public class JoPayApplication extends Application {

    public static IJoyPayService IJoyPayService;
    @Override
    public void onCreate() {
        super.onCreate();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(IJoyPayService.SERVICE_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        IJoyPayService = restAdapter.create(IJoyPayService.class);

    }
}

