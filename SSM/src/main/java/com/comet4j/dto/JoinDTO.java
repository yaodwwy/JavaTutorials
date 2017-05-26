package com.comet4j.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.comet4j.Constant;

/**
 * 上线通知传输对象
 * @author wbh
 * @date 2018-4-5
 */
public class JoinDTO {

        private final String transtime;
        private String type;
        private String id;
        private String name;
        private String userId;
        
        

        
		public String getUserId() {
		
			return userId;
		}


		
		public void setUserId(String userId) {
		
			this.userId = userId;
		}





		public JoinDTO(String id, String name) {
                this.type = Constant.UP;
                this.id = id;
                this.name = name;
                Date d = new Date(System.currentTimeMillis());
                SimpleDateFormat f = new SimpleDateFormat("HH:mm");
                transtime = f.format(d);
        }



		public JoinDTO(String id, String name, String userId) {
			this.id = id;
			this.name = name;
			this.userId = userId;
			Date d = new Date(System.currentTimeMillis());
            SimpleDateFormat f = new SimpleDateFormat("HH:mm");
            transtime = f.format(d);
		}



		public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
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