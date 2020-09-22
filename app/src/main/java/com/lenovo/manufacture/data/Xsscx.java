package com.lenovo.manufacture.data;

import java.util.List;

public class Xsscx {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":2469,"userWorkId":1,"stageId":5,"productionLineId":2,"type":0,"position":3,"isAI":0},{"id":2468,"userWorkId":1,"stageId":44,"productionLineId":1,"type":0,"position":2,"isAI":1}]
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
         * id : 2469
         * userWorkId : 1
         * stageId : 5
         * productionLineId : 2
         * type : 0
         * position : 3
         * isAI : 0
         */

        private int id;
        private int userWorkId;
        private int stageId;
        private int productionLineId;
        private int type;
        private int position;
        private int isAI;

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

        public int getStageId() {
            return stageId;
        }

        public void setStageId(int stageId) {
            this.stageId = stageId;
        }

        public int getProductionLineId() {
            return productionLineId;
        }

        public void setProductionLineId(int productionLineId) {
            this.productionLineId = productionLineId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getIsAI() {
            return isAI;
        }

        public void setIsAI(int isAI) {
            this.isAI = isAI;
        }
    }
}
