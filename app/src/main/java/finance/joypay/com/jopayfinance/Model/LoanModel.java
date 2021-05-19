package finance.joypay.com.jopayfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pandiyan.M on 2/6/2018.
 */

public class LoanModel implements Serializable{
    @SerializedName("loannumber")
    @Expose
    public String loannumber;
    @SerializedName("LoanDate")
    @Expose
    public String loanDate;
    @SerializedName("FullName")
    @Expose
    public String fullName;
    @SerializedName("loanamount")
    @Expose
    public String loanamount;
    @SerializedName("numberofdues")
    @Expose
    public String numberofdues;
    @SerializedName("collectionamount")
    @Expose
    public String collectionamount;
    @SerializedName("termname")
    @Expose
    public String termname;
    @SerializedName("status")
    @Expose
    public String status;
}
