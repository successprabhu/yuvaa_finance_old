package finance.joypay.com.jopayfinance.Service;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import finance.joypay.com.jopayfinance.Model.CommonListModel;
import finance.joypay.com.jopayfinance.Model.CustomerModel;
import finance.joypay.com.jopayfinance.Model.DashBoardModel;
import finance.joypay.com.jopayfinance.Model.UserDetailModel;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Pandiyan M on 1/19/2018.
 */

public interface IJoyPayService {
    String SERVICE_ENDPOINT = "http://www.luckyfinance.in/php";
    @POST("/finance.php?f=androidlogin")
    @FormUrlEncoded
    Observable<UserDetailModel> login(@Field("name") String username, @Field("password") String password);

    @GET("/finance.php?f=dashboard")
    Observable<DashBoardModel> getDashBordDeatails();

    @GET("/finance.php")
    Observable<JsonElement> getCommonData(@Query(value = "f") String value);

    @GET("/finance.php")
    Observable<JsonElement> getTodayCollectionReport(@Query(value = "f") String value ,@Query(value = "date") String date);
}
