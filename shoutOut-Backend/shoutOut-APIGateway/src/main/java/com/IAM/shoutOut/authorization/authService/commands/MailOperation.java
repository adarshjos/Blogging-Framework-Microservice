package com.IAM.shoutOut.authorization.authService.commands;

import com.IAM.shoutOut.authorization.AsyncTask.AsyncTaskExecutor;
import com.IAM.shoutOut.authorization.model.NotificationEmail;
import com.IAM.shoutOut.context.WorkSpaceContext;
import com.IAM.shoutOut.util.BeanUtil;
import com.IAM.shoutOut.util.DataModelConstants;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.thymeleaf.TemplateEngine;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MailOperation implements Filter {

    private  static final Logger logger = Logger.getLogger(MailOperation.class.getName());

    @Override
    public boolean execute(Context context) throws Exception {
        WorkSpaceContext mainContext=(WorkSpaceContext)context;
        NotificationEmail notificationEmail = new NotificationEmail("Please activate your account",mainContext.getSignUpRequestDTO().getEmail(),mailBuilder(mainContext));
        MimeMessagePreparator mimeMessagePreparator= mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("admin@shoutout.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(notificationEmail.getBody());
        };
        getAsyncTaskExecutor().sendMailAsync(mainContext,mimeMessagePreparator,notificationEmail);
        return false;
    }

    private String mailBuilder(WorkSpaceContext mainContext){
        String message = "Click on the below link to activate: "+ DataModelConstants.ACTIVATION_EMAIL+"/"+mainContext.getAuthToken();
        org.thymeleaf.context.Context context=new org.thymeleaf.context.Context();
        context.setVariable("message",message);
        try{
            TemplateEngine templateEngine=mainContext.getTemplateEngine();
            String content = templateEngine.process("mailTemplate",context);
            logger.log(Level.INFO,"");
            return content;
        }catch (Exception e){
            logger.log(Level.SEVERE,"Exception while generating mail content::->",e);
        }
       return null;

    }
    private AsyncTaskExecutor getAsyncTaskExecutor() throws Exception {
        return (AsyncTaskExecutor) BeanUtil.loadBean(AsyncTaskExecutor.class);
    }
    @Override
    public boolean postprocess(Context context, Exception e) {
        return false;
    }
}
