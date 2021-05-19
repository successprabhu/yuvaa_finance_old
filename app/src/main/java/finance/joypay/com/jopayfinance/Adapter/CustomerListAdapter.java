package finance.joypay.com.jopayfinance.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

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

/**
 * Created by Pandiyan M on 1/29/2018.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {

    private Context context;
    private CommonListModel cartList;
    private Page page;

    public CustomerListAdapter(Context context, Page page, CommonListModel cartList) {
        this.context = context;
        this.cartList = cartList;
        this.page = page;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = 0;
        switch (page) {
            case CUSTOMER:
                layoutId = R.layout.item_customer_list;
                break;
            case CUSTOMER_GROUP:
                layoutId = R.layout.item_customer_group_list;
                break;
            case ADD_EXPENSES:
                layoutId = R.layout.item_add_expences_list;
                break;
            case NEW_INSTALLMENT:
                layoutId = R.layout.item_installment_loan;
                break;
            case COLLATION_REPORT:
                layoutId = R.layout.item_dashboard_collection_report;
                break;
            case EML_COLLECTION:
                layoutId = R.layout.item_emi_collection_list;
                break;
            case OVERALL_REPORT:
                layoutId = R.layout.item_overall_report_list;
                break;

        }
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        switch (page) {
            case CUSTOMER:
                CustomerModel Customer = (CustomerModel) cartList.data.get(position);
                holder.customerName.setText(Customer.fullName);
                holder.customerAddress.setText(Customer.address + "," + Customer.city + "," + Customer.pincode);
                holder.customerStatus.setText(Customer.status);
                holder.customerStatus.setTextColor(Customer.status.equalsIgnoreCase("Active") ? ResourcesCompat.getColor(context.getResources(), R.color.colorActionBar, null) : ResourcesCompat.getColor(context.getResources(), R.color.isActiviefalse, null));
                break;
            case CUSTOMER_GROUP:
                GroupModel Group = (GroupModel) cartList.data.get(position);
                holder.groupName.setText(Group.groupname);
                break;
            case ADD_EXPENSES:
                ExpencesModel mExpences = (ExpencesModel) cartList.data.get(position);
                //holder.groupName.setText(mExpences.);
                break;
            case NEW_INSTALLMENT:
                LoanModel mLoan = (LoanModel) cartList.data.get(position);
                holder.customerName.setText(mLoan.fullName);
                //holder.customerImage.setImageResource(R.drawable.ic_loan);
                holder.firstcount.setText(mLoan.numberofdues);
                holder.LastCount.setText(mLoan.collectionamount);
                holder.LoanAmount.setText("Loan Amount : "+mLoan.loanamount);
                holder.LoanDate.setText("Loan Date : "+mLoan.loanDate);
                break;
            case COLLATION_REPORT:
                CollectionReportModel mCollectionReportModel = (CollectionReportModel) cartList.data.get(position);
                holder.groupName.setText(mCollectionReportModel.fullName);
                holder.customerEmiAmount.setText(mCollectionReportModel.emiamount);
                holder.customerStatus.setBackground(mCollectionReportModel.status.equalsIgnoreCase("UnPaid")? ContextCompat.getDrawable(context,R.drawable.cornerradius_dashboard_sms) : ContextCompat.getDrawable(context,R.drawable.cornerradius_dashboard_red));
                break;
            case EML_COLLECTION:
                EmiCollectionModel mEmiCollectionModel = (EmiCollectionModel) cartList.data.get(position);
                holder.groupName.setText(mEmiCollectionModel.fullName);
                holder.customerImage.setImageResource(R.drawable.ic_test_user);
                break;
            case OVERALL_REPORT:
                PendingLoanModel mPendingLoanModel = (PendingLoanModel) cartList.data.get(position);
                holder.groupName.setText(mPendingLoanModel.fullName);
                holder.customerImage.setImageResource(R.drawable.ic_overall_report);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return cartList.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName,customerEmiAmount, customerAddress, customerStatus, groupName,firstcount,LastCount,LoanAmount,LoanDate;
        public ImageView customerImage;

        public MyViewHolder(View view) {
            super(view);
            switch (page) {
                case CUSTOMER:
                    customerName = view.findViewById(R.id.customer_name);
                    customerAddress = view.findViewById(R.id.customer_address);
                    customerStatus = view.findViewById(R.id.customer_Active);
                    customerImage = view.findViewById(R.id.customer_image);
                    break;
                case CUSTOMER_GROUP:
                    groupName = view.findViewById(R.id.listview_item_title);
                    customerImage = view.findViewById(R.id.listview_image);
                    break;
                case ADD_EXPENSES:
                    groupName = view.findViewById(R.id.listview_item_title);
                    customerImage = view.findViewById(R.id.listview_image);
                    break;
                case NEW_INSTALLMENT:
                    customerName = view.findViewById(R.id.txt_cus_name);
                    customerAddress = view.findViewById(R.id.txt_cus_address);
                    LoanAmount = view.findViewById(R.id.txt_cus_loanamount);
                    LoanDate = view.findViewById(R.id.txt_cus_loandate);
                    firstcount = view.findViewById(R.id.count_textview);
                    LastCount = view.findViewById(R.id.last_count_textview);
                    break;
                case COLLATION_REPORT:
                    groupName = view.findViewById(R.id.txt_cusname);
                    customerEmiAmount = view.findViewById(R.id.txt_cusamount);
                    customerStatus =view.findViewById(R.id.txt_cusstatus);
                    break;
                case EML_COLLECTION:
                    groupName = view.findViewById(R.id.listview_item_title);
                    customerImage = view.findViewById(R.id.listview_image);
                    break;
                case OVERALL_REPORT:
                    groupName = view.findViewById(R.id.listview_item_title);
                    customerImage = view.findViewById(R.id.listview_image);
                    break;
            }

        }
    }
}
