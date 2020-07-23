package com.shoutOut.client.util;

public final class DataModelConstants {

    public static final String ACTIVATION_EMAIL = "http://localhost:8080/api/auth/accountVerification";

    public static enum POST_REACTION{
            UPVOTE(1,"Upvote"),
            DOWNVOTE(-1,"DownVote");
            
        private final int intValue;
        private final String stringValue;

        POST_REACTION(int i,String string) {
            this.intValue=i;
            this.stringValue=string;
        }
        public int getIntValue(){
            return this.intValue;
        }

        public String getStringValue() { return this.stringValue; }

        public static POST_REACTION fromValue(int value){
            for(POST_REACTION each:values()){
                if(each.intValue==value){
                    return each;
                }
            }
            throw new IllegalArgumentException("InvalidPostReaction"+value);
        }
        public static POST_REACTION fromValue(String value){
            for(POST_REACTION each:values()){
                if(each.stringValue.equals(value)){
                    return each;
                }
            }
            throw new IllegalArgumentException("InvalidPostReaction"+value);
        }

    }
    //Post status constants
    public static enum PUBLISH_STATUS{
            DRAFTED(0,"Drafted"),
            PUBLISHED(1,"Published");
        private final int intValue;
        private final String stringValue;


        PUBLISH_STATUS(int intValue, String stringValue) {
            this.intValue = intValue;
            this.stringValue = stringValue;
        }
        public static PUBLISH_STATUS fromValue(int value){
            for(PUBLISH_STATUS each:values()){
                if(each.intValue==value){
                    return each;
                }
            }
            throw new IllegalArgumentException("InvalidPostReaction"+value);
        }
        public static PUBLISH_STATUS fromValue(String value){
            for(PUBLISH_STATUS each:values()){
                if(each.stringValue.equals(value)){
                    return each;
                }
            }
            throw new IllegalArgumentException("InvalidPostReaction"+value);
        }
    }




}
