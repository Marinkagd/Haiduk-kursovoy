package request.tdo.constructors;

import db.entity.Admin;
import db.entity.Assistant;
import db.entity.User;
import request.tdo.AdminTDO;
import request.tdo.AssistantTDO;
import request.tdo.UserTDO;

public class AccountTDOConstructor {
    public UserTDO usertdoconversion(User userentity)
    {
        if(userentity == null)
            return null;
        UserTDO usertdo = new UserTDO();
        usertdo.setId(userentity.getId());
        usertdo.setPassword(userentity.getPassword());
        usertdo.setFirstname(userentity.getFirstname()); 
        usertdo.setSecondname(userentity.getSecondname()); 
        usertdo.setPhonenumber(userentity.getPhonenumber());
        usertdo.setAddress(userentity.getAddress()); 
        usertdo.setEmail(userentity.getEmail());
        return usertdo;
    }
    public AdminTDO admintdoconversion(Admin adminentity)
    {
        if(adminentity == null)
            return null;
        AdminTDO admintdo = new AdminTDO();
        admintdo.setId(adminentity.getId());
        admintdo.setPassword(adminentity.getPassword());
        return admintdo;
    }
    public AssistantTDO assistanttdoconversion(Assistant assistantentity)
    {
        if(assistantentity == null)
        return null;
        AssistantTDO assistanttdo = new AssistantTDO();
        assistanttdo.setId(assistantentity.getId());
        assistanttdo.setPassword(assistantentity.getPassword());
        assistanttdo.setFirstname(assistantentity.getFirstname());
        assistanttdo.setSecondname(assistantentity.getSecondname());
        return assistanttdo;
    }
}
