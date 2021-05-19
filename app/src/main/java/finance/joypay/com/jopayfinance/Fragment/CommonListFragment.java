package finance.joypay.com.jopayfinance.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import finance.joypay.com.jopayfinance.Activity.DashBoardActivity;
import finance.joypay.com.jopayfinance.Adapter.CustomerListAdapter;
import finance.joypay.com.jopayfinance.Enum.Page;
import finance.joypay.com.jopayfinance.Model.CollectionReportModel;
import finance.joypay.com.jopayfinance.Model.CommonListModel;
import finance.joypay.com.jopayfinance.Model.CustomerModel;
import finance.joypay.com.jopayfinance.Model.EmiCollectionModel;
import finance.joypay.com.jopayfinance.Model.ExpencesModel;
import finance.joypay.com.jopayfinance.Model.GroupModel;
import finance.joypay.com.jopayfinance.Model.LoanModel;
import finance.joypay.com.jopayfinance.Model.PendingLoanModel;
import finance.joypay.com.jopayfinance.R;
import finance.joypay.com.jopayfinance.Utils.JoPayApplication;
import finance.joypay.com.jopayfinance.Enum.Status;
import finance.joypay.com.jopayfinance.Utils.OnFragmentInteractionListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CommonListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CommonListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommonListFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Page page;
    private String Url;
    FloatingActionButton newFabButton;
    private RecyclerView recyclerView;
    private CustomerListAdapter mAdapter;
    private OnFragmentInteractionListener mListener;
    private RecyclerView.Adapter adapter;

    public CommonListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Page Type
     * @return A new instance of fragment CommonListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommonListFragment newInstance(Page page,String Url) {
        CommonListFragment fragment = new CommonListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page.ordinal());
        args.putString(ARG_PARAM2, Url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = Page.values()[ getArguments().getInt(ARG_PARAM1)];
            Url =  getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_common_list, container, false);
        mActivity = getActivity();
        initViwes();
        CustomerList();
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    void initViwes() {
        recyclerView = init(R.id.recycleview);
        ((DashBoardActivity)mActivity).fab.setVisibility(View.VISIBLE);
        ((DashBoardActivity)mActivity).fab.setTag(page);
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


    private void CustomerList() {
        ((DashBoardActivity) mActivity).showProgressDialog();
        JoPayApplication.IJoyPayService.getCommonData(Url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Subscriber<JsonElement>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable e) {
                        ((DashBoardActivity) mActivity).removeProgressDialog();
                    }

                    @Override
                    public final void onNext(JsonElement data) {
                        if (data != null) {

                            switch (page) {
                                case CUSTOMER:
                                    CommonListModel<CustomerModel> contact = new Gson().fromJson(data,new TypeToken<CommonListModel<CustomerModel>>(){}.getType());
                                    adapter = new CustomerListAdapter(mActivity, page,contact);
                                    mActivity.setTitle("Customers");
                                    break;
                                case ADD_EXPENSES:
                                    CommonListModel<ExpencesModel> mExpences = new Gson().fromJson(data,new TypeToken<CommonListModel<ExpencesModel>>(){}.getType());
                                    adapter = new CustomerListAdapter(mActivity, page,mExpences);
                                    mActivity.setTitle("Expenses");
                                    break;
                                case CUSTOMER_GROUP:
                                    CommonListModel<GroupModel> mGroupModel = new Gson().fromJson(data,new TypeToken<CommonListModel<GroupModel>>(){}.getType());
                                    adapter = new CustomerListAdapter(mActivity, page,mGroupModel);
                                    mActivity.setTitle("Customer Group");
                                    break;
                                case NEW_INSTALLMENT:
                                    CommonListModel<LoanModel> mLoan = new Gson().fromJson(data,new TypeToken<CommonListModel<LoanModel>>(){}.getType());
                                    adapter = new CustomerListAdapter(mActivity, page,mLoan);
                                    mActivity.setTitle("New Installments");
                                    break;
                                case COLLATION_REPORT:
                                    CommonListModel<CollectionReportModel> mCollectionReport = new Gson().fromJson(data,new TypeToken<CommonListModel<CollectionReportModel>>(){}.getType());
                                    adapter = new CustomerListAdapter(mActivity, page,mCollectionReport);
                                    mActivity.setTitle("Collection Report");
                                    break;
                                case EML_COLLECTION:
                                    CommonListModel<EmiCollectionModel> mEMI = new Gson().fromJson(data,new TypeToken<CommonListModel<EmiCollectionModel>>(){}.getType());
                                    adapter = new CustomerListAdapter(mActivity, page,mEMI);
                                    mActivity.setTitle("EMI");
                                    break;
                                case OVERALL_REPORT:
                                    CommonListModel<PendingLoanModel> mPendingModel = new Gson().fromJson(data,new TypeToken<CommonListModel<PendingLoanModel>>(){}.getType());
                                    adapter = new CustomerListAdapter(mActivity, page,mPendingModel);
                                    mActivity.setTitle("Report");
                                    break;
                            }
                                if(adapter != null) {
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());
    //                                recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
                                    recyclerView.setAdapter(adapter);
                                }

                        }
                        ((DashBoardActivity) mActivity).removeProgressDialog();
                    }
                });


    }
}
