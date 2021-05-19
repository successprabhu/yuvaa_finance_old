package finance.joypay.com.jopayfinance.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import finance.joypay.com.jopayfinance.Activity.DashBoardActivity;
import finance.joypay.com.jopayfinance.Enum.Status;
import finance.joypay.com.jopayfinance.R;
import finance.joypay.com.jopayfinance.Utils.OnFragmentInteractionListener;


public class LoanCreationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "LoanCreationFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spin_customer_loancreation,spin_collectionteam_loancreation;
    private OnFragmentInteractionListener mListener;

    public LoanCreationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoanCreationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoanCreationFragment newInstance(String param1, String param2) {
        LoanCreationFragment fragment = new LoanCreationFragment();
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
        View v=inflater.inflate(R.layout.fragment_loan_creation, container, false);
        spin_customer_loancreation=(Spinner) v.findViewById(R.id.spin_customer_loancreation);
        spin_collectionteam_loancreation=(Spinner) v.findViewById(R.id.spin_collectionteam_loancreation);
        spin_customer_loancreation.setAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,new String[]{"Select"}));
        spin_collectionteam_loancreation.setAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,new String[]{"Select"}));
        v.findViewById(R.id.btn_add_loancreatin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DashBoardActivity) getActivity()).ShowMessage(Status.SUCCESS, "New Installment");
                getActivity().onBackPressed();
            }
        });

        return v;
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


}
