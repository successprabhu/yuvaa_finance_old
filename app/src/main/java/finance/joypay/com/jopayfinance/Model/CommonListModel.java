package finance.joypay.com.jopayfinance.Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Pandiyan.M on 1/30/2018.
 */

public class CommonListModel<T> implements Serializable{

    @SerializedName("data")
    @Expose
    public List<T> data = new ArrayList<>();

}
