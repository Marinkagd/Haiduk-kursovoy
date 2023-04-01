package menu.db.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import request.tdo.UserOrderTDO;

@Entity
@Table(name = "userorder")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    // @ManyToOne
    // @JoinColumn(name = "user", nullable = false)
    // private User user;
    @Column(name = "user")
    private String user;

    @OneToMany(mappedBy = "userorder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderElement> orderelementlist;

    @Column(name = "orderdate", nullable = false)
    private Date orderdate;

    @Column(name = "orderprice", nullable = false)
    private float orderprice;

    @Column(name = "orderfirstcost", nullable = false)
    private float orderfirstcost;

    public UserOrder(String user, Date date, float orderprice, float orderfirstcost) {
        this.user = user;
        this.orderdate = date;
        this.orderelementlist = null;
        this.orderprice = orderprice;
        this.orderfirstcost = orderfirstcost;
    }

    public UserOrder(String user, List<CartElement> userCartelementlist)
    {
        this.user = user;
        this.orderdate = new Date(System.currentTimeMillis());
        orderelementlist = new ArrayList<>();
        for (CartElement cartElement : userCartelementlist) {
            orderelementlist.add(new OrderElement(this, cartElement));
        }
    }

    public UserOrder(UserOrderTDO userOrderTDO)
    {
        this.id = userOrderTDO.getId();
        this.user = userOrderTDO.getUser();
        this.orderdate = userOrderTDO.getOrderdate();
        this.orderprice = userOrderTDO.getOrderprice();
        this.orderfirstcost = userOrderTDO.getOrderfirstcost();
    }

    public void setPriceAndFirstCost(List<OrderElement> orderelementList)
    {
        for (OrderElement orderElement : orderelementList) {
            orderprice += orderElement.getFurniture().getPrice() * orderElement.getAmount();
            orderfirstcost += orderElement.getFurniture().getFirstcost() * orderElement.getAmount();
        }
    }

    public UserOrder() {
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
    
    public float getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(float orderprice) {
        this.orderprice = orderprice;
    }

    public float getOrdefirstcost() {
        return orderfirstcost;
    }

    public void setOrdefirstcost(float orderfirstcost) {
        this.orderfirstcost = orderfirstcost;
    }

    public List<OrderElement> getOrderelementlist() {
        return orderelementlist;
    }

    public void setOrderelementlist(List<OrderElement> orderelementlist) {
        this.orderelementlist = orderelementlist;
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
