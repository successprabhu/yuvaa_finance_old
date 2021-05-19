package finance.joypay.com.jopayfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pandiyan.M on 2/6/2018.
 */

public class PendingLoanModel implements Serializable {

    @SerializedName("FullName")
    @Expose
    public String fullName;
    @SerializedName("totalemi")
    @Expose
    public String totalemi;
    @SerializedName("emiamount")
    @Expose
    public String emiamount;
    @SerializedName("loannumber")
    @Expose
    public String loannumber;
}
