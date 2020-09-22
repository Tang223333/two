package com.lenovo.manufacture.data;

import java.util.List;

public class Hgl {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":1,"passRateName":"轿车汽车维修","content":"轿车汽车维修","carId":1,"rate":70,"costTime":2},{"id":2,"passRateName":"MPV汽车维修","content":"MPV汽车维修","carId":2,"rate":60,"costTime":3},{"id":3,"passRateName":"SUV汽车维修","content":"SUV汽车维修","carId":3,"rate":50,"costTime":3}]
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
         * passRateName : 轿车汽车维修
         * content : 轿车汽车维修
         * carId : 1
         * rate : 70
         * costTime : 2
         */

        private int id;
        private String passRateName;
        private String content;
        private int carId;
        private int rate;
        private int costTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassRateName() {
            return passRateName;
        }

        public void setPassRateName(String passRateName) {
            this.passRateName = passRateName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public int getCostTime() {
            return costTime;
        }

        public void setCostTime(int costTime) {
            this.costTime = costTime;
        }
    }
}
