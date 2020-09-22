package com.lenovo.manufacture.data;

import java.util.List;

public class Cpck {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":3627,"userWorkId":1,"userProductionLineId":2469,"carId":2,"num":1},{"id":3625,"userWorkId":1,"userProductionLineId":2469,"carId":2,"num":1},{"id":3624,"userWorkId":1,"userProductionLineId":2469,"carId":2,"num":1},{"id":3621,"userWorkId":1,"userProductionLineId":2468,"carId":1,"num":50},{"id":3620,"userWorkId":1,"userProductionLineId":2468,"carId":1,"num":1},{"id":3618,"userWorkId":1,"userProductionLineId":2468,"carId":1,"num":1},{"id":3615,"userWorkId":1,"userProductionLineId":2468,"carId":1,"num":1}]
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
         * id : 3627
         * userWorkId : 1
         * userProductionLineId : 2469
         * carId : 2
         * num : 1
         */

        private int id;
        private int userWorkId;
        private int userProductionLineId;
        private int carId;
        private int num;

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

        public int getUserProductionLineId() {
            return userProductionLineId;
        }

        public void setUserProductionLineId(int userProductionLineId) {
            this.userProductionLineId = userProductionLineId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
