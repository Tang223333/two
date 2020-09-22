package com.lenovo.manufacture.data;

import java.util.List;

public class Ycl {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":1,"partName":"轿车引擎","content":"轿车汽车发动机","area":2,"icon":"UI_fadongji01"},{"id":4,"partName":"轿车前玻璃","content":"轿车汽车前玻璃","area":2,"icon":"UI_qianboli01"},{"id":5,"partName":"MPV前玻璃","content":"MPV汽车前玻璃","area":2,"icon":"UI_qianboli02"},{"id":8,"partName":"MPV轮胎","content":"MPV汽车轮胎","area":1,"icon":"UI_luntai02"},{"id":7,"partName":"轿车轮胎","content":"轿车汽车轮胎","area":1,"icon":"UI_luntai01"},{"id":9,"partName":"SUV轮胎","content":"SUV汽车轮胎","area":1,"icon":"UI_luntai03"},{"id":10,"partName":"SUV前玻璃","content":"SUV汽车前玻璃","area":1,"icon":"UI_qianboli03"},{"id":11,"partName":"MPV引擎","content":"MPV汽车发动机","area":2,"icon":"UI_fadongji02"},{"id":12,"partName":"SUV引擎","content":"SUV汽车发动机","area":2,"icon":"UI_fadongji03"},{"id":13,"partName":"轿车底盘","content":"轿车汽车底盘","area":3,"icon":"UI_dipan01"},{"id":14,"partName":"MPV底盘","content":"MPV汽车底盘","area":3,"icon":"UI_dipan02"},{"id":15,"partName":"SUV底盘","content":"SUV汽车底盘","area":3,"icon":"UI_dipan03"},{"id":16,"partName":"轿车前盖","content":"轿车汽车前盖","area":1,"icon":"UI_qiangai01"},{"id":17,"partName":"MPV前盖","content":"MPV汽车前盖","area":1,"icon":"UI_qiangai02"},{"id":18,"partName":"SUV前盖","content":"SUV汽车前盖","area":1,"icon":"UI_qiangai03"},{"id":19,"partName":"SUV车架","content":"SUV汽车SUV车架","area":2,"icon":"UI_body03"},{"id":20,"partName":"轿车悬挂","content":"轿车汽车悬挂","area":1,"icon":"UI_xuangua01"},{"id":21,"partName":"MPV悬挂","content":"MPV汽车悬挂","area":1,"icon":"UI_xuangua02"},{"id":22,"partName":"SUV悬挂","content":"SUV汽车悬挂","area":1,"icon":"UI_xuangua03"},{"id":23,"partName":"轿车座椅","content":"轿车汽车","area":2,"icon":"UI_zhuoyi01"},{"id":24,"partName":"MPV座椅","content":"MPV汽车座椅","area":2,"icon":"UI_zhuoyi02"},{"id":25,"partName":"轿车方向盘","content":"轿车汽车方向盘","area":1,"icon":"UI_fangxiangpan01"},{"id":26,"partName":"MPV方向盘","content":"MPV汽车方向盘","area":1,"icon":"UI_fangxiangpan02"},{"id":29,"partName":"轿车车架","content":"轿车汽车红色车架","area":2,"icon":"UI_body01"},{"id":30,"partName":"MPV车架","content":"MPV汽车白色车架","area":2,"icon":"UI_body02"},{"id":31,"partName":"轿车车门","content":"轿车汽车红色车门","area":1,"icon":"UI_door01"},{"id":32,"partName":"MPV车门","content":"MPV汽车白色车门","area":1,"icon":"UI_door02"},{"id":33,"partName":"SUV车门","content":"SUV汽车SUV车门","area":1,"icon":"UI_door03"},{"id":34,"partName":"SUV方向盘","content":"SUV汽车方向盘","area":1,"icon":"UI_fangxiangpan03"},{"id":35,"partName":"轿车后玻璃","content":"轿车汽车后玻璃","area":1,"icon":"UI_houboli01"},{"id":36,"partName":"MPV后玻璃","content":"MPV汽车后玻璃","area":1,"icon":"UI_houboli02"},{"id":37,"partName":"SUV后玻璃","content":"SUV汽车后玻璃","area":1,"icon":"UI_houboli03"},{"id":38,"partName":"SUV座椅","content":"SUV汽车座椅","area":2,"icon":"UI_zhuoyi03"}]
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
         * partName : 轿车引擎
         * content : 轿车汽车发动机
         * area : 2
         * icon : UI_fadongji01
         */

        private int id;
        private String partName;
        private String content;
        private int area;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPartName() {
            return partName;
        }

        public void setPartName(String partName) {
            this.partName = partName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
