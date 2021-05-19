package finance.joypay.com.jopayfinance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import finance.joypay.com.jopayfinance.Enum.Status;
import finance.joypay.com.jopayfinance.Model.UserDetailModel;
import finance.joypay.com.jopayfinance.R;
import finance.joypay.com.jopayfinance.Utils.JoPayApplication;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText userName,Password ;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViwes();
    }

    @Override
    void initViwes() {
        userName = init(R.id.edt_email);
        Password = init(R.id.edt_password);
        login = init(R.id.btn_login);
        login.setOnClickListener(this);
    }

    public void moveToDashBoard( View v){

    }
    public void moveToRegister( View v){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
//            if ((isValidPhone(get(userName)) || isValidMail(get(userName))) && !TextUtils.isEmpty(get(Password))) {
//                showProgressDialog();
//                JoPayApplication.IJoyPayService.login(get(userName), get(Password))
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<UserDetailModel>() {
//                            @Override
//                            public final void onCompleted() {
//                                // do nothing
//                            }
//
//                            @Override
//                            public final void onError(Throwable e) {
//                                removeProgressDialog();
//                            }
//
//                            @Override
//                            public final void onNext(UserDetailModel user) {
//                                removeProgressDialog();
//                                if (user.status.contains("success")) {
//                                    //TODO Navigation to DashBoard
//                                    NavigateDashboard(user);
//                                } else
//                                    ShowMessage(Status.ERROR, "Incorrect username or password");
//                            }
//                        });
//
//            } else {
//                ShowMessage(Status.ERROR, "Please enter the valid Details");
//            }
            NavigateDashboard(null);
        }
    }

    void NavigateDashboard(UserDetailModel model){
        String data = "{\"otp\":4712,\"status\":\"success\",\"datavalue\":{\"id\":\"1\",\"FullName\":\"PRABHU P\",\"Mobile\":\"9940715788\",\"Email\":\"successprabhu@gmail.com\",\"Password\":\"test\",\"Role\":\"1\",\"Status\":\"1\",\"CreatedDate\":\"2017-12-13\",\"CreatedBy\":\"\",\"ModifiedDate\":\"0000-00-00\",\"ModifiedBy\":\"\"}}";
        if(model==null){
            model = new Gson().fromJson(data,UserDetailModel.class);
        }
        Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
        i.putExtra("USERMODEL",model);
        startActivity(i);
        finish();
    }
}
