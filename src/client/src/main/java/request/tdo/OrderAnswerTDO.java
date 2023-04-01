package request.tdo;

import java.io.Serializable;

public class OrderAnswerTDO implements Serializable {
    private UserOrderTDO userOrderTDO;
    private boolean isaccepted;
    private String description;
    public OrderAnswerTDO(UserOrderTDO userOrderTDO, boolean isaccepted, String description) {
        this.userOrderTDO = userOrderTDO;
        this.isaccepted = isaccepted;
        this.description = description;
    }
    public UserOrderTDO getUserOrderTDO() {
        return userOrderTDO;
    }
    public void setUserOrderTDO(UserOrderTDO userOrderTDO) {
        this.userOrderTDO = userOrderTDO;
    }
    public boolean isIsaccepted() {
        return isaccepted;
    }
    public void setIsaccepted(boolean isaccepted) {
        this.isaccepted = isaccepted;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
