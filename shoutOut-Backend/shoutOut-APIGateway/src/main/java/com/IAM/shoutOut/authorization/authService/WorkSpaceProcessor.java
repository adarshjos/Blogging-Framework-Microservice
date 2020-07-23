package com.IAM.shoutOut.authorization.authService;

import com.IAM.shoutOut.populate.PopulateDataBean;
import com.IAM.shoutOut.authorization.authService.commands.AuthTokenGenerator;
import com.IAM.shoutOut.authorization.authService.commands.MailOperation;
import com.IAM.shoutOut.authorization.authService.commands.PopulateDB;
import com.IAM.shoutOut.authorization.authService.commands.VerifyAccount;
import com.IAM.shoutOut.context.WorkSpaceContext;
import com.IAM.shoutOut.util.BeanUtil;
import org.apache.commons.chain.impl.ChainBase;


public class WorkSpaceProcessor extends ChainBase{

    WorkSpaceContext mainContext;
    public WorkSpaceProcessor(WorkSpaceContext mainContext){
        this.mainContext=mainContext;
    }

    public void execute()throws Exception
    {
        preProcess();
        process();
    }

    private void preProcess() throws Exception
    {
        initCommands();
        mainContext.populateDataBean=getPopulateDataBean();

    }
    private void process() throws Exception {
        execute(this.mainContext);
    }

    private void initCommands() {
        switch(this.mainContext.getAction()){
            case SIGNUP: {
                addCommand(new AuthTokenGenerator());
                addCommand(new PopulateDB());
                addCommand(new MailOperation());
                break;
            }
            case VERIFYACCOUNT:{
                addCommand(new VerifyAccount());
                break;
            }
        }

    }

    private PopulateDataBean getPopulateDataBean() throws Exception {
            return (PopulateDataBean) BeanUtil.loadBean(PopulateDataBean.class);
        }


}
