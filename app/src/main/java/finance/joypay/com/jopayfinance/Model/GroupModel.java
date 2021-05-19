package finance.joypay.com.jopayfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pandiyan.M on 2/6/2018.
 */

public class GroupModel implements Serializable{

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("groupname")
    @Expose
    public String groupname;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("updated_date")
    @Expose
    public String updatedDate;
    @SerializedName("created_by")
    @Expose
    public String createdBy;
    @SerializedName("updated_by")
    @Expose
    public String updatedBy;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("is_deleted")
    @Expose
    public String isDeleted;
}
