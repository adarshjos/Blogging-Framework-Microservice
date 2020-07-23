package com.IAM.shoutOut.authorization.authService.commands;


import com.IAM.shoutOut.context.WorkSpaceContext;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthTokenGenerator implements Filter {
    private static final Logger logger=Logger.getLogger(AuthTokenGenerator.class.getName());

    @Override
    public boolean execute(Context context) throws Exception {
        WorkSpaceContext mainContext=(WorkSpaceContext)context;
        generateVerificationToken(mainContext);
        return false;
    }
    private void generateVerificationToken(WorkSpaceContext mainContext){
        try{
            String token = UUID.randomUUID().toString();
            mainContext.setAuthToken(token);
            logger.log(Level.INFO,"::Token Generated::");
        }catch(Exception e){
            logger.log(Level.SEVERE,"Exception:: Generating token::",e);
        }
    }

    @Override
    public boolean postprocess(Context context, Exception e) {
        return false;
    }
}
