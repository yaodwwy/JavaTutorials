package com.comet4j.dto;

import com.comet4j.Constant;


/**
 * 系统健康状态的dto
 * @author user
 *
 */
public class HealthDTO {

	 private final String type = Constant.HEALTH;
     private String totalMemory;//可使用的内存容量
     private String freeMemory;//空闲内存
     private String maxMemory;//最大内存
     private String usedMemory;//已使用内存
     private String connectorCount;//连接数量
     private String startup;//系统运行时间
     private final long divider = 1024 * 1024;

     public String getTotalMemory() {
             return totalMemory;
     }

     public void setTotalMemory(long totalMemory) {
             Long result = totalMemory / divider;
             this.totalMemory = result.toString();
     }

     public String getFreeMemory() {
             return freeMemory;
     }

     public void setFreeMemory(long freeMemory) {
             Long result = freeMemory / divider;
             this.freeMemory = result.toString();
     }

     public String getMaxMemory() {
             return maxMemory;
     }

     public void setMaxMemory(long maxMemory) {
             Long result = maxMemory / divider;
             this.maxMemory = result.toString();
     }

     public String getUsedMemory() {
             return usedMemory;
     }

     public void setUsedMemory(long usedMemory) {
             Long result = usedMemory / divider;
             this.usedMemory = result.toString();
     }

     public String getConnectorCount() {
             return connectorCount;
     }

     public void setConnectorCount(String connectorCount) {
             this.connectorCount = connectorCount;
     }

     public String getType() {
             return type;
     }

     public String getStartup() {

             return startup;
     }

     public void setStartup(String startup) {
             this.startup = startup;
     }
}
