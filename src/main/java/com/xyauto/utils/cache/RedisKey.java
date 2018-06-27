package com.xyauto.utils.cache;

public class RedisKey {
	
	public enum QuestionEnum {		
		del_question_uid("del_question_uids","删除用户问题key"),
		question_speed("qa:sync:third:display:accelerate","放题速度");
		private String key;
        private String keyName;
		public String getKey() {
			return key;
		}		
		public String getKeyName() {
			return keyName;
		}
		private QuestionEnum(String key,String keyName){
			this.key=key;
			this.keyName=keyName;
		}		
	}
	public enum AnswerEnum{
		del_answer_uid("del_answer_uids","删除用户回复key");
        private String key;
        private String keyName;
		public String getKey() {
			return key;
		}
		public String getKeyName() {
			return keyName;
		}
		private AnswerEnum(String key,String keyName){
			this.key=key;
		}		
	}
	
	public enum UserEnum{
		recover_user("recover_user_statu","恢复用户身份");
        private String key;
        private String keyName;
		public String getKey() {
			return key;
		}
		public String getKeyName() {
			return keyName;
		}
		private UserEnum(String key,String keyName){
			this.key=key;
		}
	}
}
