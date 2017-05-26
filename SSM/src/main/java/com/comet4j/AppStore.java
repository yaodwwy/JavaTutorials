package com.comet4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用全局存储
 * @author wbh
 * @date 2014-8-5
 */

public class AppStore {

        private static Map<String, String> map;
        private static AppStore instance;

        public static AppStore getInstance() {

                if (instance == null) {
                        instance = new AppStore();
                        map = new HashMap<String, String>();
                }
                return instance;
        }

        public void put(String key, String value) {
                map.put(key, value);
        }

        public String get(String key) {
                return map.get(key);
        }

        public Map<String, String> getMap() {
                return map;
        }

        public void destroy() {
                map.clear();
                map = null;
        }

}