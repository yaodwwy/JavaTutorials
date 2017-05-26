package com.comet4j.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.comet4j.Constant;

/**
 * 聊天信息传输对象
 * @author wbh
 * @date 2014-8-5
 */

public class TalkDTO {

        private final String transtime;
        private String type;
        private String id;
        private String name;
        private String text;
        private String userId;

        public TalkDTO(String id, String name, String text) {
                this.type = Constant.TALK;
                this.id = id;
                this.name = name;
                this.text = text;
                this.transtime = System.currentTimeMillis()+"";
                /*Date d = new Date(System.currentTimeMillis());
                SimpleDateFormat f = new SimpleDateFormat("HH:mm");
                transtime = f.format(d);*/
        }

        public TalkDTO(String id, String name, String text,String userId) {
            this.type = Constant.TALK;
            this.id = id;
            this.name = name;
            this.text = text;
            this.userId = userId;
            this.transtime = System.currentTimeMillis()+"";
            /*Date d = new Date(System.currentTimeMillis());
            SimpleDateFormat f = new SimpleDateFormat("HH:mm");
            transtime = f.format(d);*/
    }
        
		public String getUserId() {
		
			return userId;
		}


		
		public void setUserId(String userId) {
		
			this.userId = userId;
		}


		public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getText() {
                return text;
        }

        public void setText(String text) {
                this.text = text;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getTranstime() {
                return transtime;
        }
}