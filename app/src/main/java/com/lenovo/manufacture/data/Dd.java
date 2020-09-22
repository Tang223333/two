package com.lenovo.manufacture.data;

import java.util.List;

public class Dd {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":359,"userWorkId":1,"userAppointmentName":"订单一","content":"初始订单","type":0,"carId":1,"time":1571639400,"num":1,"gold":1000,"engine":"2","speed":"0","wheel":"0","control":"0","brake":"0","hang":"0","color":"0"},{"id":360,"userWorkId":1,"userAppointmentName":"订单二","content":"初始订单","type":0,"carId":2,"time":1571639400,"num":1,"gold":2000,"engine":"1","speed":"0","wheel":"0","control":"0","brake":"1","hang":"1","color":"1"}]
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
         * id : 359
         * userWorkId : 1
         * userAppointmentName : 订单一
         * content : 初始订单
         * type : 0
         * carId : 1
         * time : 1571639400
         * num : 1
         * gold : 1000
         * engine : 2
         * speed : 0
         * wheel : 0
         * control : 0
         * brake : 0
         * hang : 0
         * color : 0
         */

        private int id;
        private int userWorkId;
        private String userAppointmentName;
        private String content;
        private int type;
        private int carId;
        private int time;
        private int num;
        private int gold;
        private String engine;
        private String speed;
        private String wheel;
        private String control;
        private String brake;
        private String hang;
        private String color;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserWorkId() {
            return userWorkId;
        }

        public void setUserWorkId(int userWorkId) {
            this.userWorkId = userWorkId;
        }

        public String getUserAppointmentName() {
            return userAppointmentName;
        }

        public void setUserAppointmentName(String userAppointmentName) {
            this.userAppointmentName = userAppointmentName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public String getEngine() {
            return engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getWheel() {
            return wheel;
        }

        public void setWheel(String wheel) {
            this.wheel = wheel;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getBrake() {
            return brake;
        }

        public void setBrake(String brake) {
            this.brake = brake;
        }

        public String getHang() {
            return hang;
        }

        public void setHang(String hang) {
            this.hang = hang;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
