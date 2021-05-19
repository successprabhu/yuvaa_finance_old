package finance.joypay.com.jopayfinance.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import finance.joypay.com.jopayfinance.Enum.Page;
import finance.joypay.com.jopayfinance.Enum.Status;
import finance.joypay.com.jopayfinance.Fragment.CommonListFragment;
import finance.joypay.com.jopayfinance.Fragment.CustomerCrationFragment;
import finance.joypay.com.jopayfinance.Fragment.DashBoardFragment;
import finance.joypay.com.jopayfinance.Fragment.NewExpenseFragment;
import finance.joypay.com.jopayfinance.Model.UserDetailModel;
import finance.joypay.com.jopayfinance.R;
import finance.joypay.com.jopayfinance.Utils.OnFragmentInteractionListener;

public class DashBoardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    UserDetailModel user;
    TextView txtCustomers, txtsmsBalance, txtLoan, txtLoanOutstanding;
    public FloatingActionButton fab;
    String currentPage;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_dashboard:
                fragment = new DashBoardFragment();
                break;
            case R.id.nav_customergroups:
                fragment = CommonListFragment.newInstance(Page.CUSTOMER_GROUP, "GetAllGroups");
                break;
            case R.id.nav_customers:
                fragment = CommonListFragment.newInstance(Page.CUSTOMER, "GetAllcustomers");
                break;
            case R.id.nav_add_expenses:
                fragment = CommonListFragment.newInstance(Page.ADD_EXPENSES, "GetAllExpenses");
                break;
            case R.id.nav_new_loan:
                fragment = CommonListFragment.newInstance(Page.NEW_INSTALLMENT, "GetAllLoans");
                break;
            case R.id.nav_emi_collection:
                fragment = CommonListFragment.newInstance(Page.EML_COLLECTION, "getloanrepayment");
                break;
            case R.id.nav_collection_report:
                fragment = CommonListFragment.newInstance(Page.COLLATION_REPORT, "GetCollectionReport");
                break;
            case R.id.nav_pending_report:
                fragment = CommonListFragment.newInstance(Page.OVERALL_REPORT, "getoverallpendings");
                break;
            case R.id.nav_logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;

        }
        if (fragment != null) {
            NavigateToFragment(fragment);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        user = (UserDetailModel) getIntent().getSerializableExtra("USERMODEL");
        initViwes();
        NavigateToFragment(new DashBoardFragment());
    }

    @Override
    void initViwes() {
        SetupToolBar();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, getToolBar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView.getHeaderView(0) != null) {
            if (user != null) {
                ((TextView) navigationView.getHeaderView(0).findViewById(R.id.txt_email)).setText(user.datavalue.email);
                ((TextView) navigationView.getHeaderView(0).findViewById(R.id.txt_username)).setText(user.datavalue.fullName);
                ((TextView) navigationView.getHeaderView(0).findViewById(R.id.txt_mobile)).setText(user.datavalue.mobile);
            }
        }
        navigationView.setNavigationItemSelectedListener(this);
        fab = init(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fabClick((Page) view.getTag());
            }
        });
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!removeOtherFragments())
            super.onBackPressed();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void fabClick(Page page) {
        switch (page) {
            case CUSTOMER:
//                ShowMessage(Status.SUCCESS, "CUSTOMER");
                addFragment(CustomerCrationFragment.newInstance("newCustomer", "test"), CustomerCrationFragment.TAG);
                break;
            case CUSTOMER_GROUP:
//                ShowMessage(Status.SUCCESS, "CUSTOMER GROUP");
                openGroupCreatinDialog();
                break;
            case ADD_EXPENSES:
//                ShowMessage(Status.SUCCESS, "ADD EXPENSES");
                addFragment(NewExpenseFragment.newInstance("ADD EXPENSES", "test"), NewExpenseFragment.TAG);
                break;
            case NEW_INSTALLMENT:
//                ShowMessage(Status.SUCCESS, "NEW INSTALLMENT");
                addFragment(NewExpenseFragment.newInstance("NEW INSTALLMENT", "test"), NewExpenseFragment.TAG);
                break;
            case COLLATION_REPORT:
                ShowMessage(Status.SUCCESS, "COLLATION REPORT");
                break;
            case EML_COLLECTION:
                ShowMessage(Status.SUCCESS, "EML COLLECTION");
                break;
            case OVERALL_REPORT:
                ShowMessage(Status.SUCCESS, "OVERALL REPORT");
                break;

        }
    }

    Dialog dialog_newGropup;

    private void openGroupCreatinDialog() {
        if (dialog_newGropup != null) {
            if (!dialog_newGropup.isShowing())
                dialog_newGropup.show();
            return;
        }
        dialog_newGropup = new Dialog(this);
        dialog_newGropup.setContentView(R.layout.dialog_new_group);
        dialog_newGropup.findViewById(R.id.btn_add_groupcreation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMessage(Status.SUCCESS, "CUSTOMER GROUP");
                dialog_newGropup.dismiss();
            }
        });
        dialog_newGropup.show();
    }
}
