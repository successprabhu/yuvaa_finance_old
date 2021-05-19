package finance.joypay.com.jopayfinance.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import finance.joypay.com.jopayfinance.Fragment.CustomerCrationFragment;
import finance.joypay.com.jopayfinance.R;
import finance.joypay.com.jopayfinance.Utils.AppToastMessage;
import finance.joypay.com.jopayfinance.Enum.Status;

/**
 * Created by Pandiyan M on 1/19/2018.
 */
abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public static String TAG = "BaseActivity";
    private static ProgressDialog mDialog;

    void SetupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getAppActionBar();
    }

    public Toolbar getToolBar() {
        return toolbar;
    }

    public ActionBar getAppActionBar() {
        return getSupportActionBar();
    }

    public void SetTitle(String title) {
        getAppActionBar().setTitle(title);
    }

    abstract void initViwes();

    @SuppressWarnings("unchecked")
    public <T> T init(int viewId) {
        return ((T) findViewById(viewId));
    }

    protected String get(View view) {
        String result = "";
        String superClass = view.getClass().getSuperclass().getName();
        if (superClass.equalsIgnoreCase("android.widget.textview")
                || superClass.equalsIgnoreCase("android.widget.edittext")) {
            result = ((TextView) view).getText().toString().trim();
        } else if (superClass.equalsIgnoreCase("android.widget.absspinner")) {
            result = ((Spinner) view).getSelectedItem().toString().trim();
        }
        return result;
    }

    protected String get(int resid) {
        return get(((View) findViewById(resid)));
    }

    public void set(View view, String text) {
        ((TextView) view).setText(text);
    }

    public void set(int resid, String text) {
        set(((View) findViewById(resid)), text);
    }

    @SuppressWarnings("unchecked")
    protected <T> void display(int viewId, boolean display) {
        try {
            display(((T) findViewById(viewId)), display);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage() + "");
        }
    }

    protected <T> void display(T view, boolean display) {
        try {
            ((View) view).setVisibility(display ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage() + "");
        }
    }

    @SuppressLint("DefaultLocale")
    public String capitalize(String s) {
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    protected boolean isNullOrEmpty(String s) {
        if (s != null) {
            s = s.trim();
        }
        return s == null || s.length() == 0;
    }

    public void showProgressDialog() {
        removeProgressDialog();
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("Loading...");
        }
        if (!isFinishing())
            mDialog.show();
    }

    public static void removeProgressDialog() {
        try {
            if (mDialog != null) {
                mDialog.dismiss();
                mDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isValidPhone(CharSequence phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }

    public boolean isValidMail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

    }

    public void ShowMessage(Status status, String msg) {

        switch (status) {
            case SUCCESS:
                AppToastMessage.with(this)
                        .setTitle(getString(R.string.app_name))
                        .setMessage(msg)
                        .ToastSuccess();
                break;
            case ERROR:
                AppToastMessage.with(this)
                        .setTitle(getString(R.string.app_name))
                        .setMessage(msg)
                        .ToastError();
                break;
            case WARNING:
                AppToastMessage.with(this)
                        .setTitle(getString(R.string.app_name))
                        .setMessage(msg)
                        .ToastWarning();
                break;
        }

    }

    public void NavigateToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainContainer, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    public void addFragment(Fragment fragment, String TAG) {
        removeOtherFragments();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.mainContainer, fragment, TAG)
                .commit();
    }

    public boolean removeOtherFragments() {
        boolean result = false;
        for (Fragment f : getSupportFragmentManager().getFragments()) {
            if (f.getTag() != null)
                if (f.getTag() instanceof String)
                    switch ((String) f.getTag()) {
                        case CustomerCrationFragment.TAG:
                            getSupportFragmentManager().beginTransaction().remove(f).commit();
                            result = true;
                            break;
                    }
        }
        return result;
    }

}
