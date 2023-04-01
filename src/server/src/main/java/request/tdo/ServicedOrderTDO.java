package request.tdo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ServicedOrderTDO implements Serializable{
    private int id;
    private String user;
    private String assistantFIO;
    private String description;
    private boolean isaccepted;
    private String isacceptedString;
    private float orderprice;
    private float orderfirstcost;
    private Date orderdate;
    private Date answerdate;
    private List<ServicedOrderElementTDO> servicedorderelementlist;

    public ServicedOrderTDO() {}
    
    public ServicedOrderTDO(String user, String assistant, String description, boolean isaccepted, Date orderdate,
            Date answerdate) {
        this.user = user;
        this.assistantFIO = assistant;
        this.description = description;
        this.isaccepted = isaccepted;
        isacceptedString = isaccepted ? "Подтверждено" : "Отклонено";
        this.orderdate = orderdate;
        this.answerdate = answerdate;
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

    public String getAssistantFIO() {
        return assistantFIO;
    }

    public void setAssistantFIO(String assistant) {
        this.assistantFIO = assistant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsaccepted() {
        return isaccepted;
    }

    public void setIsaccepted(boolean isaccepted) {
        this.isaccepted = isaccepted;
        isacceptedString = isaccepted ? "Подтверждено" : "Отклонено";
    }

    public String getIsacceptedString() {
        return isacceptedString;
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

    public List<ServicedOrderElementTDO> getOrderelementlist() {
        return servicedorderelementlist;
    }

    public void setOrderelementlist(List<ServicedOrderElementTDO> orderelementlist) {
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
        return "ServicedOrder [id=" + id + ", user=" + user + ", assistant=" + assistantFIO + ", description="
                + description + ", isaccepted=" + isaccepted + ", orderdate=" + orderdate + ", answerdate=" + answerdate
                + "]";
    }
}
