package finance.joypay.com.jopayfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pandiyan.M on 2/6/2018.
 */

public class EmiCollectionModel implements Serializable {

    @SerializedName("loannumber")
    @Expose
    public String loannumber;
    @SerializedName("emino")
    @Expose
    public String emino;
    @SerializedName("emidate")
    @Expose
    public String emidate;
    @SerializedName("emiamount")
    @Expose
    public String emiamount;
    @SerializedName("remarks")
    @Expose
    public String remarks;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("paid_date")
    @Expose
    public String paidDate;
    @SerializedName("FullName")
    @Expose
    public String fullName;
}
