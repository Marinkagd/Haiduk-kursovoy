package menu.db.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import request.tdo.OrderAnswerTDO;
import request.tdo.UserOrderTDO;

@Entity
@Table(name = "servicedorder")
public class ServicedOrder{
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    // @ManyToOne
    // @JoinColumn(name = "user", nullable = false)
    // private User user;
    @Column(name = "user")
    private String user;

    // @ManyToOne
    // @JoinColumn(name = "assistant", nullable = false)
    // private Assistant assistant;
    @Column(name = "assistant")
    private String assistant;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "isaccepted", nullable = false)
    private int isaccepted;

    @Column(name = "orderdate", nullable = false)
    private Date orderdate;

    @Column(name = "answerdate", nullable = false)
    private Date answerdate;

    @Column(name = "orderprice", nullable = false)
    private float orderprice;

    @Column(name = "orderfirstcost", nullable = false)
    private float orderfirstcost;
  
    @OneToMany(mappedBy = "servicedorder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ServicedOrderElement> servicedorderelementlist;

    public ServicedOrder() {}
    
    public ServicedOrder(String user, String assistant, String description, int isaccepted, Date orderdate,
            Date answerdate, float orderprice, float orderfirstcost) {
        this.user = user;
        this.assistant = assistant;
        this.description = description;
        this.isaccepted = isaccepted;
        this.orderdate = orderdate;
        this.answerdate = answerdate;
        this.orderprice = orderprice;
        this.orderfirstcost = orderfirstcost;
    }

    public ServicedOrder(UserOrder userOrder, String assistant, String despcription, int isaccepted)
    {
        this.id = userOrder.getId();
        this.user = userOrder.getUser();
        this.assistant = assistant;
        this.description = despcription;
        this.isaccepted = isaccepted;
        this.orderdate = userOrder.getOrderdate();
        this.answerdate = new Date(System.currentTimeMillis());
        servicedorderelementlist = new ArrayList<>();
        for (OrderElement orderElement : userOrder.getOrderelementlist()) {
            servicedorderelementlist.add(new ServicedOrderElement(this, orderElement));
        }
    }

    public ServicedOrder(OrderAnswerTDO orderAnswer, String assistant)
    {
        UserOrderTDO userOrder = orderAnswer.getUserOrderTDO();
        this.id = userOrder.getId();
        this.user = userOrder.getUser();
        this.assistant = assistant;
        this.description = orderAnswer.getDescription();
        this.orderdate = userOrder.getOrderdate();
        this.answerdate = new Date(System.currentTimeMillis());
        this.orderprice = userOrder.getOrderprice();
        this.orderfirstcost = userOrder.getOrderfirstcost();
        this.isaccepted = orderAnswer.isIsaccepted() ? 1 : 0;
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

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsaccepted() {
        return isaccepted;
    }

    public void setIsaccepted(int isaccepted) {
        this.isaccepted = isaccepted;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getAnswerdate() {
        return answerdate;
    }

    public void setAnswerdate(Date answerdate) {
        this.answerdate = answerdate;
    }

    public List<ServicedOrderElement> getOrderelementlist() {
        return servicedorderelementlist;
    }

    public void setOrderelementlist(List<ServicedOrderElement> orderelementlist) {
        this.servicedorderelementlist = orderelementlist;
    }

    public float getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(float orderprice) {
        this.orderprice = orderprice;
    }
 
    public float getOrderfirstcost() {
        return orderfirstcost;
    }

    public void setOrderfirstcost(float orderfirstcost) {
        this.orderfirstcost = orderfirstcost;
    }

    @Override
    public String toString() {
        return "ServicedOrder [id=" + id + ", user=" + user + ", assistant=" + assistant + ", description="
                + description + ", isaccepted=" + isaccepted + ", orderdate=" + orderdate + ", answerdate=" + answerdate
                + "]";
    }
}
