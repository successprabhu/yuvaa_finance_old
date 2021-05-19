package finance.joypay.com.jopayfinance.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import finance.joypay.com.jopayfinance.Fragment.OtpFragment;
import finance.joypay.com.jopayfinance.Fragment.RegisterFragment;
import finance.joypay.com.jopayfinance.R;
import finance.joypay.com.jopayfinance.Utils.OnFragmentInteractionListener;

public class RegisterActivity extends BaseActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigateToFragment(RegisterFragment.newInstance("test", "Signup"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    void initViwes() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
