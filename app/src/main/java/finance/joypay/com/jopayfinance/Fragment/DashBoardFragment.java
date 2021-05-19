package finance.joypay.com.jopayfinance.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import finance.joypay.com.jopayfinance.Activity.DashBoardActivity;
import finance.joypay.com.jopayfinance.Adapter.CustomerListAdapter;
import finance.joypay.com.jopayfinance.Enum.Page;
import finance.joypay.com.jopayfinance.Model.CollectionReportModel;
import finance.joypay.com.jopayfinance.Model.CommonListModel;
import finance.joypay.com.jopayfinance.Model.DashBoardModel;
import finance.joypay.com.jopayfinance.R;
import finance.joypay.com.jopayfinance.Utils.JoPayApplication;
import finance.joypay.com.jopayfinance.Utils.OnFragmentInteractionListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txtCustomers, txtsmsBalance, txtLoan, txtLoanOutstanding;
    private RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashBoardFragment newInstance(String param1, String param2) {
        DashBoardFragment fragment = new DashBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_dash_board, container, false);
        mActivity = getActivity();
        initViwes();
        FillValue();
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    void initViwes() {
        txtCustomers = init(R.id.act_dash_txt_customers);
        txtLoan = init(R.id.act_dash_txt_loans);
        txtLoanOutstanding = init(R.id.act_dash_txt_loan_outstanding);
        txtsmsBalance = init(R.id.act_dash_txt_SMS_balance);
        recyclerView = init(R.id.todayCollectionReport);
        ((DashBoardActivity) mActivity).fab.setVisibility(View.GONE);
        mActivity.setTitle("DashBoard");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    private void FillValue() {
        ((DashBoardActivity) mActivity).showProgressDialog();
        JoPayApplication.IJoyPayService.getDashBordDeatails()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DashBoardModel>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                        Log.e("DrillManager", "onCompleted");
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("DrillManager", e.getMessage());
                        ((DashBoardActivity) mActivity).removeProgressDialog();
                    }

                    @Override
                    public final void onNext(DashBoardModel user) {
                        if (user != null) {
                            set(txtCustomers, user.customer);
                            set(txtLoan, user.loans);
                            set(txtLoanOutstanding, user.loanoutstanding);
                            set(txtsmsBalance, user.smsbalance.replace("\n", ""));
                        }
                        ((DashBoardActivity) mActivity).removeProgressDialog();
                        GetTodayCollectionReport();
                        //((DashBoardActivity)mActivity).ShowMessage(Status.SUCCESS,"Logged in Successfully");
                    }
                });
    }

    private void GetTodayCollectionReport() {
        ((DashBoardActivity) mActivity).showProgressDialog();
        JoPayApplication.IJoyPayService.getCommonData("GetTodayCollectionReport")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonElement>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                        Log.e("DrillManager", "onCompleted");
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("DrillManager", e.getMessage());
                        ((DashBoardActivity) mActivity).removeProgressDialog();
                    }

                    @Override
                    public final void onNext(JsonElement data) {
                        CommonListModel<CollectionReportModel> mCollectionReport = new Gson().fromJson(data, new TypeToken<CommonListModel<CollectionReportModel>>() {
                        }.getType());
                        CustomerListAdapter adapter = new CustomerListAdapter(mActivity, Page.COLLATION_REPORT, mCollectionReport);
                        if (adapter != null) {
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
                            recyclerView.setAdapter(adapter);
                        }
                        ((DashBoardActivity) mActivity).removeProgressDialog();
                    }

                });
    }

}
