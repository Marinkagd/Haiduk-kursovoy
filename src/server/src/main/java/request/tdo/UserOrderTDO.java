package request.tdo;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
public class UserOrderTDO implements Serializable{
 
    private int id;
    private String user;
    private List<OrderElementTDO> orderelementlist;
    private Date orderdate;
    private float orderprice;
    private float orderfirstcost;

    public UserOrderTDO(String user, Date date) {
        this.user = user;
        this.orderdate = date;
        this.orderelementlist = null;
    }

    public UserOrderTDO(String user, List<CartElementTDO> userCartelementlist)
    {
        this.user = user;
        this.orderdate = new Date(System.currentTimeMillis());
        orderelementlist = new ArrayList<>();
        for (CartElementTDO cartElement : userCartelementlist) {
            orderelementlist.add(new OrderElementTDO(id, cartElement));
        }
    }

    public UserOrderTDO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<OrderElementTDO> getOrderelementlist() {
        return orderelementlist;
    }

    public void setOrderelementlist(List<OrderElementTDO> orderelementlist) {
        this.orderelementlist = orderelementlist;
    }

    public float getOrderfirstcost() {
        return orderfirstcost;
    }

    public void setOrderfirstcost(float orderfirstcost) {
        this.orderfirstcost = orderfirstcost;
    }


    public float getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(float orderprice) {
        this.orderprice = orderprice;
    }


    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date date) {
        this.orderdate = date;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", user=" + user + ", orderdate=" + orderdate + "]";
    }
}
