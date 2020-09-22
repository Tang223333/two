package com.lenovo.manufacture.data;

import java.util.List;

public class Xsyg {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":3846,"userWorkId":1,"power":100,"peopleId":8,"userProductionLineId":2468,"workPostId":""},{"id":3845,"userWorkId":1,"power":100,"peopleId":7,"userProductionLineId":2468,"workPostId":""},{"id":3844,"userWorkId":1,"power":100,"peopleId":5,"userProductionLineId":2468,"workPostId":""},{"id":3843,"userWorkId":1,"power":90,"peopleId":30,"userProductionLineId":2468,"workPostId":2},{"id":3842,"userWorkId":1,"power":40,"peopleId":23,"userProductionLineId":2469,"workPostId":7},{"id":3841,"userWorkId":1,"power":100,"peopleId":25,"userProductionLineId":2469,"workPostId":5},{"id":3840,"userWorkId":1,"power":100,"peopleId":22,"userProductionLineId":2469,"workPostId":""},{"id":3839,"userWorkId":1,"power":50,"peopleId":17,"userProductionLineId":2469,"workPostId":""},{"id":3838,"userWorkId":1,"power":100,"peopleId":15,"userProductionLineId":2469,"workPostId":""},{"id":3837,"userWorkId":1,"power":100,"peopleId":16,"userProductionLineId":2469,"workPostId":""},{"id":3836,"userWorkId":1,"power":70,"peopleId":4,"userProductionLineId":2468,"workPostId":4},{"id":3835,"userWorkId":1,"power":70,"peopleId":3,"userProductionLineId":2468,"workPostId":3},{"id":3834,"userWorkId":1,"power":100,"peopleId":2,"userProductionLineId":2468,"workPostId":""},{"id":3833,"userWorkId":1,"power":70,"peopleId":1,"userProductionLineId":2468,"workPostId":1}]
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
         * id : 3846
         * userWorkId : 1
         * power : 100
         * peopleId : 8
         * userProductionLineId : 2468
         * workPostId :
         */

        private int id;
        private int userWorkId;
        private int power;
        private int peopleId;
        private int userProductionLineId;
        private String workPostId;
        private String userLineName;
        private String peopleName;
        private String peoplePrice;

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }

        public String getPeoplePrice() {
            return peoplePrice;
        }

        public void setPeoplePrice(String peoplePrice) {
            this.peoplePrice = peoplePrice;
        }

        public String getUserLineName() {
            return userLineName;
        }

        public void setUserLineName(String userLineName) {
            this.userLineName = userLineName;
        }

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

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getPeopleId() {
            return peopleId;
        }

        public void setPeopleId(int peopleId) {
            this.peopleId = peopleId;
        }

        public int getUserProductionLineId() {
            return userProductionLineId;
        }

        public void setUserProductionLineId(int userProductionLineId) {
            this.userProductionLineId = userProductionLineId;
        }

        public String getWorkPostId() {
            return workPostId;
        }

        public void setWorkPostId(String workPostId) {
            this.workPostId = workPostId;
        }
    }
}
