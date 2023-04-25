package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class yilian {
    private int code;
    private Data data;

    public static class Data {
        private String name;
        private String key;
        private String token;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", key='" + key + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }
}

