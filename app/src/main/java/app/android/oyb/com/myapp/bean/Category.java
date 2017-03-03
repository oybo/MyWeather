package app.android.oyb.com.myapp.bean;

import java.io.Serializable;

/**
 * Created by O on 2017/2/23.
 */

public class Category implements Serializable {


    /**
     * id : 1
     * name : 玄幻
     * key : xuanhuan
     * pic :
     * num : 3450
     */

    private String id;
    private String name;
    private String key;
    private String pic;
    private int num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
