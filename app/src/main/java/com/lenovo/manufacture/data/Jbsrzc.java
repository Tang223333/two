package com.lenovo.manufacture.data;

import java.util.List;

public class Jbsrzc {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":"230","userWorkId":1,"price":400,"endPrice":null,"time":1600656070,"type":null}]
     */

    private int status;
    private String message;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 230
         * userWorkId : 1
         * price : 400
         * endPrice : null
         * time : 1600656070
         * type : null
         */

        private String id;
        private int userWorkId;
        private int price;
        private Object endPrice;
        private int time;
        private Object type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserWorkId() {
            return userWorkId;
        }

        public void setUserWorkId(int userWorkId) {
            this.userWorkId = userWorkId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Object getEndPrice() {
            return endPrice;
        }

        public void setEndPrice(Object endPrice) {
            this.endPrice = endPrice;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }
    }
}
