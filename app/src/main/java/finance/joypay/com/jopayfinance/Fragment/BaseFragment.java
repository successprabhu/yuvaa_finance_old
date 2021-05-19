package finance.joypay.com.jopayfinance.Fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Pandiyan M on 1/29/2018.
 */

public abstract class BaseFragment extends Fragment {
    protected View mView;
    ProgressDialog mProgressDialog;
    public FragmentActivity mActivity;

    @SuppressWarnings("unchecked")
    public <T> T init(int viewId) {
        return ((T) mView.findViewById(viewId));
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
        return get(((View) mView.findViewById(resid)));
    }

    public void set(View view, String text) {
        ((TextView) view).setText(text);
    }

    public void set(int resid, String text) {
        set(((View) mView.findViewById(resid)), text);
    }

    protected boolean isNullOrEmpty(String s) {
        if (s != null) {
            s = s.trim();
        }
        return s == null || s.length() == 0;
    }

    public void showDialogPopup() {
        showDialogPopup("Loading...");
    }

    public void showDialogPopup(String display) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(mActivity, "Please wait",
                    display, true);
            mProgressDialog.setCancelable(false);
        }
    }

    public void dismissDialogPopup() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }

    }
    abstract void initViwes();
}
