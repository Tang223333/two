package com.lenovo.manufacture.data;

import java.util.List;

public class Xsgcxx {



    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":1,"partCapacity":100,"carCapacity":50000,"price":1},{"id":2,"partCapacity":10,"carCapacity":50,"price":50000}]
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
         * id : 1
         * partCapacity : 100
         * carCapacity : 50000
         * price : 1
         */

        private int id;
        private int partCapacity;
        private int carCapacity;
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPartCapacity() {
            return partCapacity;
        }

        public void setPartCapacity(int partCapacity) {
            this.partCapacity = partCapacity;
        }

        public int getCarCapacity() {
            return carCapacity;
        }

        public void setCarCapacity(int carCapacity) {
            this.carCapacity = carCapacity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
