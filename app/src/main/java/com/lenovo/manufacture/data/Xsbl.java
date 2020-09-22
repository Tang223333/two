package com.lenovo.manufacture.data;

import java.util.List;

public class Xsbl {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":14430,"userWorkId":1,"userProductionLineId":2469,"partId":14,"num":1}]
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
         * id : 14430
         * userWorkId : 1
         * userProductionLineId : 2469
         * partId : 14
         * num : 1
         */

        private int id;
        private int userWorkId;
        private int userProductionLineId;
        private int partId;
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

        public int getPartId() {
            return partId;
        }

        public void setPartId(int partId) {
            this.partId = partId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
