package com.lenovo.manufacture.data;

import java.util.List;

public class Schjylxh {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":22,"plStepId":22,"partId":8,"num":1},{"id":21,"plStepId":21,"partId":8,"num":1},{"id":23,"plStepId":23,"partId":8,"num":1},{"id":5,"plStepId":5,"partId":14,"num":1},{"id":6,"plStepId":6,"partId":21,"num":1},{"id":7,"plStepId":7,"partId":21,"num":1},{"id":8,"plStepId":8,"partId":21,"num":1},{"id":9,"plStepId":9,"partId":21,"num":1},{"id":10,"plStepId":10,"partId":30,"num":1},{"id":11,"plStepId":11,"partId":11,"num":1},{"id":12,"plStepId":12,"partId":24,"num":1},{"id":13,"plStepId":13,"partId":26,"num":1},{"id":14,"plStepId":14,"partId":32,"num":1},{"id":15,"plStepId":15,"partId":32,"num":1},{"id":16,"plStepId":16,"partId":32,"num":1},{"id":17,"plStepId":17,"partId":32,"num":1},{"id":18,"plStepId":18,"partId":17,"num":1},{"id":19,"plStepId":19,"partId":5,"num":1},{"id":20,"plStepId":20,"partId":36,"num":1},{"id":24,"plStepId":24,"partId":8,"num":1},{"id":25,"plStepId":25,"partId":13,"num":1},{"id":26,"plStepId":26,"partId":20,"num":1},{"id":27,"plStepId":27,"partId":20,"num":1},{"id":28,"plStepId":28,"partId":20,"num":1},{"id":29,"plStepId":29,"partId":20,"num":1},{"id":30,"plStepId":30,"partId":29,"num":1},{"id":31,"plStepId":31,"partId":1,"num":1},{"id":32,"plStepId":32,"partId":23,"num":1},{"id":33,"plStepId":33,"partId":25,"num":1},{"id":34,"plStepId":34,"partId":31,"num":1},{"id":35,"plStepId":35,"partId":31,"num":1},{"id":36,"plStepId":36,"partId":31,"num":1},{"id":37,"plStepId":37,"partId":31,"num":1},{"id":38,"plStepId":38,"partId":16,"num":1},{"id":39,"plStepId":39,"partId":4,"num":1},{"id":40,"plStepId":40,"partId":35,"num":1},{"id":41,"plStepId":41,"partId":7,"num":1},{"id":42,"plStepId":42,"partId":7,"num":1},{"id":43,"plStepId":43,"partId":7,"num":1},{"id":44,"plStepId":44,"partId":7,"num":1},{"id":45,"plStepId":45,"partId":15,"num":1},{"id":46,"plStepId":46,"partId":22,"num":1},{"id":47,"plStepId":47,"partId":22,"num":1},{"id":48,"plStepId":48,"partId":22,"num":1},{"id":49,"plStepId":49,"partId":22,"num":1},{"id":50,"plStepId":50,"partId":19,"num":1},{"id":51,"plStepId":51,"partId":12,"num":1},{"id":52,"plStepId":52,"partId":38,"num":1},{"id":53,"plStepId":53,"partId":34,"num":1},{"id":54,"plStepId":54,"partId":33,"num":1},{"id":55,"plStepId":55,"partId":33,"num":1},{"id":56,"plStepId":56,"partId":33,"num":1},{"id":57,"plStepId":57,"partId":33,"num":1},{"id":58,"plStepId":58,"partId":18,"num":1},{"id":59,"plStepId":59,"partId":10,"num":1},{"id":60,"plStepId":60,"partId":37,"num":1},{"id":61,"plStepId":61,"partId":9,"num":1},{"id":62,"plStepId":62,"partId":9,"num":1},{"id":63,"plStepId":63,"partId":9,"num":1},{"id":64,"plStepId":64,"partId":9,"num":1}]
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
         * id : 22
         * plStepId : 22
         * partId : 8
         * num : 1
         */

        private int id;
        private int plStepId;
        private int partId;
        private int num;
        private String partName;

        public String getPartName() {
            return partName;
        }

        public void setPartName(String partName) {
            this.partName = partName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPlStepId() {
            return plStepId;
        }

        public void setPlStepId(int plStepId) {
            this.plStepId = plStepId;
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
