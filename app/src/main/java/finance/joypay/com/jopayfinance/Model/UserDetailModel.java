package finance.joypay.com.jopayfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pandiyan M on 1/19/2018.
 */

public class UserDetailModel implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("datavalue")
    @Expose
    public Datavalue datavalue;

    public class Datavalue implements Serializable {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("FullName")
        @Expose
        public String fullName;
        @SerializedName("Mobile")
        @Expose
        public String mobile;
        @SerializedName("Email")
        @Expose
        public String email;
        @SerializedName("Password")
        @Expose
        public String password;
        @SerializedName("Role")
        @Expose
        public String role;
        @SerializedName("Status")
        @Expose
        public String status;
        @SerializedName("CreatedDate")
        @Expose
        public String createdDate;
        @SerializedName("CreatedBy")
        @Expose
        public String createdBy;
        @SerializedName("ModifiedDate")
        @Expose
        public String modifiedDate;
        @SerializedName("ModifiedBy")
        @Expose
        public String modifiedBy;
    }


}