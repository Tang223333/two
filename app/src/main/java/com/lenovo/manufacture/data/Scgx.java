package com.lenovo.manufacture.data;

import java.util.List;

public class Scgx {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":5,"stageName":"MPV汽车放置底盘","content":"将汽车底盘放置在作业线上","plStepId":5,"nextStageId":6},{"id":6,"stageName":"MPV汽车左前悬挂","content":"机械臂将汽车左前悬挂固定在汽车底盘左前部","plStepId":6,"nextStageId":7},{"id":7,"stageName":"MPV汽车右前悬挂","content":"机械臂将汽车右前悬挂固定在汽车底盘右前部","plStepId":7,"nextStageId":8},{"id":8,"stageName":"MPV汽车左后悬挂","content":"机械臂将汽车左后悬挂固定在汽车底盘左后部","plStepId":8,"nextStageId":9},{"id":9,"stageName":"MPV汽车右后悬挂","content":"机械臂将汽车右后悬挂固定在汽车底盘左后部","plStepId":9,"nextStageId":10},{"id":10,"stageName":"MPV汽车固定车架","content":"将汽车车架固定在底盘上","plStepId":10,"nextStageId":12},{"id":11,"stageName":"MPV汽车发动机","content":"将发动机放置进车架前部","plStepId":12,"nextStageId":13},{"id":12,"stageName":"MPV汽车椅子","content":"将前排座椅后排座椅固定在车内","plStepId":11,"nextStageId":11},{"id":13,"stageName":"MPV汽车方向盘","content":"将方向盘放入车内前部","plStepId":13,"nextStageId":14},{"id":14,"stageName":"MPV汽车左前车门","content":"将左前车门固定在车架上","plStepId":14,"nextStageId":15},{"id":15,"stageName":"MPV汽车右前车门","content":"将右前车门固定在车架上","plStepId":15,"nextStageId":16},{"id":16,"stageName":"MPV汽车左后车门","content":"将左后车门固定在车架上","plStepId":16,"nextStageId":17},{"id":17,"stageName":"MPV汽车右后车门","content":"将右后车门固定在车架上","plStepId":17,"nextStageId":18},{"id":18,"stageName":"MPV汽车引擎盖","content":"安装引擎盖","plStepId":18,"nextStageId":19},{"id":19,"stageName":"MPV汽车前挡风玻璃","content":"安装前挡风玻璃","plStepId":19,"nextStageId":20},{"id":20,"stageName":"MPV汽车后挡风玻璃","content":"安装后挡风玻璃","plStepId":20,"nextStageId":21},{"id":21,"stageName":"MPV汽车左前轮胎","content":"机械臂在底盘上安装左前轮胎","plStepId":21,"nextStageId":22},{"id":22,"stageName":"MPV汽车右前轮胎","content":"机械臂在底盘上安装右前轮胎","plStepId":22,"nextStageId":23},{"id":23,"stageName":"MPV汽车左后轮胎","content":"机械臂在底盘上安装左后轮胎","plStepId":23,"nextStageId":24},{"id":24,"stageName":"MPV汽车右后轮胎","content":"机械臂在底盘上安装右后轮胎","plStepId":24,"nextStageId":null},{"id":25,"stageName":"轿车汽车放置底盘","content":"将汽车底盘放置在作业线上","plStepId":25,"nextStageId":26},{"id":26,"stageName":"轿车汽车左前悬挂","content":"机械臂将汽车左前悬挂固定在汽车底盘左前部","plStepId":26,"nextStageId":27},{"id":27,"stageName":"轿车汽车右前悬挂","content":"机械臂将汽车右前悬挂固定在汽车底盘右前部","plStepId":27,"nextStageId":28},{"id":28,"stageName":"轿车汽车左后悬挂","content":"机械臂将汽车左后悬挂固定在汽车底盘左后部","plStepId":28,"nextStageId":29},{"id":29,"stageName":"轿车汽车右后悬挂","content":"机械臂将汽车右后悬挂固定在汽车底盘左后部","plStepId":29,"nextStageId":30},{"id":30,"stageName":"轿车汽车固定车架","content":"将汽车车架固定在底盘上","plStepId":30,"nextStageId":32},{"id":31,"stageName":"轿车汽车发动机","content":"将发动机放置进车架前部","plStepId":32,"nextStageId":33},{"id":32,"stageName":"轿车汽车椅子","content":"将前排座椅后排座椅固定在车内","plStepId":31,"nextStageId":31},{"id":33,"stageName":"轿车汽车方向盘","content":"将方向盘放入车内前部","plStepId":33,"nextStageId":34},{"id":34,"stageName":"轿车汽车左前车门","content":"将左前车门固定在车架上","plStepId":34,"nextStageId":35},{"id":35,"stageName":"轿车汽车右前车门","content":"将右前车门固定在车架上","plStepId":35,"nextStageId":36},{"id":36,"stageName":"轿车汽车左后车门","content":"将左后车门固定在车架上","plStepId":36,"nextStageId":37},{"id":37,"stageName":"轿车汽车右后车门","content":"将右后车门固定在车架上","plStepId":37,"nextStageId":38},{"id":38,"stageName":"轿车汽车引擎盖","content":"安装引擎盖","plStepId":38,"nextStageId":39},{"id":39,"stageName":"轿车汽车前挡风玻璃","content":"安装前挡风玻璃","plStepId":39,"nextStageId":40},{"id":40,"stageName":"轿车汽车后挡风玻璃","content":"安装后挡风玻璃","plStepId":40,"nextStageId":41},{"id":41,"stageName":"轿车汽车左前轮胎","content":"机械臂在底盘上安装左前轮胎","plStepId":41,"nextStageId":42},{"id":42,"stageName":"轿车汽车右前轮胎","content":"机械臂在底盘上安装右前轮胎","plStepId":42,"nextStageId":43},{"id":43,"stageName":"轿车汽车左后轮胎","content":"机械臂在底盘上安装左后轮胎","plStepId":43,"nextStageId":44},{"id":44,"stageName":"轿车汽车右后轮胎","content":"机械臂在底盘上安装右后轮胎","plStepId":44,"nextStageId":null},{"id":45,"stageName":"SUV汽车放置底盘","content":"将汽车底盘放置在作业线上","plStepId":45,"nextStageId":46},{"id":46,"stageName":"SUV汽车左前悬挂","content":"机械臂将汽车左前悬挂固定在汽车底盘左前部","plStepId":46,"nextStageId":47},{"id":47,"stageName":"SUV汽车右前悬挂","content":"机械臂将汽车右前悬挂固定在汽车底盘右前部","plStepId":47,"nextStageId":48},{"id":48,"stageName":"SUV汽车左后悬挂","content":"机械臂将汽车左后悬挂固定在汽车底盘左后部","plStepId":48,"nextStageId":49},{"id":49,"stageName":"SUV汽车右后悬挂","content":"机械臂将汽车右后悬挂固定在汽车底盘左后部","plStepId":49,"nextStageId":50},{"id":50,"stageName":"SUV汽车固定车架","content":"将汽车车架固定在底盘上","plStepId":50,"nextStageId":52},{"id":51,"stageName":"SUV汽车发动机","content":"将发动机放置进车架前部","plStepId":52,"nextStageId":53},{"id":52,"stageName":"SUV汽车椅子","content":"将前排座椅后排座椅固定在车内","plStepId":51,"nextStageId":51},{"id":53,"stageName":"SUV汽车方向盘","content":"将方向盘放入车内前部","plStepId":53,"nextStageId":54},{"id":54,"stageName":"SUV汽车左前车门","content":"将左前车门固定在车架上","plStepId":54,"nextStageId":55},{"id":55,"stageName":"SUV汽车右前车门","content":"将右前车门固定在车架上","plStepId":55,"nextStageId":56},{"id":56,"stageName":"SUV汽车左后车门","content":"将左后车门固定在车架上","plStepId":56,"nextStageId":57},{"id":57,"stageName":"SUV汽车右后车门","content":"将右后车门固定在车架上","plStepId":57,"nextStageId":58},{"id":58,"stageName":"SUV汽车引擎盖","content":"安装引擎盖","plStepId":58,"nextStageId":59},{"id":59,"stageName":"SUV汽车前挡风玻璃","content":"安装前挡风玻璃","plStepId":59,"nextStageId":60},{"id":60,"stageName":"SUV汽车后挡风玻璃","content":"安装后挡风玻璃","plStepId":60,"nextStageId":61},{"id":61,"stageName":"SUV汽车左前轮胎","content":"机械臂在底盘上安装左前轮胎","plStepId":61,"nextStageId":62},{"id":62,"stageName":"SUV汽车右前轮胎","content":"机械臂在底盘上安装右前轮胎","plStepId":62,"nextStageId":63},{"id":63,"stageName":"SUV汽车左后轮胎","content":"机械臂在底盘上安装左后轮胎","plStepId":63,"nextStageId":64},{"id":64,"stageName":"SUV汽车右后轮胎","content":"机械臂在底盘上安装右后轮胎","plStepId":64,"nextStageId":null}]
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
         * id : 5
         * stageName : MPV汽车放置底盘
         * content : 将汽车底盘放置在作业线上
         * plStepId : 5
         * nextStageId : 6
         */

        private int id;
        private String stageName;
        private String content;
        private int plStepId;
        private int nextStageId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getPlStepId() {
            return plStepId;
        }

        public void setPlStepId(int plStepId) {
            this.plStepId = plStepId;
        }

        public int getNextStageId() {
            return nextStageId;
        }

        public void setNextStageId(int nextStageId) {
            this.nextStageId = nextStageId;
        }
    }
}
