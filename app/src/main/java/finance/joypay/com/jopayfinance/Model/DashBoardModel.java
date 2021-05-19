package finance.joypay.com.jopayfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pandiyan M on 1/19/2018.
 */

public class DashBoardModel implements Serializable{
    @SerializedName("customer")
    @Expose
    public String customer;
    @SerializedName("loans")
    @Expose
    public String loans;
    @SerializedName("loanoutstanding")
    @Expose
    public String loanoutstanding;
    @SerializedName("smsbalance")
    @Expose
    public String smsbalance;
}
