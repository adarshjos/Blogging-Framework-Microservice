package com.IAM.shoutOut.authorization.AsyncTask;

import com.IAM.shoutOut.authorization.model.NotificationEmail;
import com.IAM.shoutOut.context.WorkSpaceContext;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class AsyncTaskExecutor {
    private  static final Logger LOGGER = Logger.getLogger(AsyncTaskExecutor.class.getName());

    @Async
    public CompletableFuture<Boolean> sendMailAsync(WorkSpaceContext mainContext, MimeMessagePreparator mimeMessagePreparator, NotificationEmail notificationEmail) {
        CompletableFuture<Boolean>response= new CompletableFuture<>();
        try{
            long startingMillis = System.currentTimeMillis();
            LOGGER.log(Level.INFO,"Activation Mail---->Send{0}:::Started In:::{1}",new Object[]{notificationEmail.getRecipient(),System.currentTimeMillis()});
            mainContext.getJavaMailSender().send(mimeMessagePreparator);
            response.complete(Boolean.TRUE);
            LOGGER.log(Level.INFO,"Activation Mail---->Send{0}:::Finished In:::{1}",new Object[]{notificationEmail.getRecipient(),System.currentTimeMillis()-startingMillis});
        }catch(Exception ex){
            response.completeExceptionally(ex);
            LOGGER.log(Level.SEVERE,"Exception while executing AsyncTask for mailing operation:::",ex);
        }
        return response;
    }

}
