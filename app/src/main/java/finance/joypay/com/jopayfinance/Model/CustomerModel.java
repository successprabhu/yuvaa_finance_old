package finance.joypay.com.jopayfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pandiyan M on 1/29/2018.
 */

public class CustomerModel implements Serializable {

    @SerializedName("FullName")
    @Expose
    public String fullName;
    @SerializedName("Address")
    @Expose
    public String address;
    @SerializedName("City")
    @Expose
    public String city;
    @SerializedName("Pincode")
    @Expose
    public String pincode;
    @SerializedName("Mobile")
    @Expose
    public String mobile;
    @SerializedName("photopath")
    @Expose
    public String photopath;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("Aadharnumber")
    @Expose
    public String aadharnumber;
    @SerializedName("groupname")
    @Expose
    public String groupname;
    @SerializedName("Status")
    @Expose
    public String status;

}
