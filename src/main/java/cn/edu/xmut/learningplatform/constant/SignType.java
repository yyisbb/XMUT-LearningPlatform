package cn.edu.xmut.learningplatform.constant;

public enum SignType {
    SUCCESS("成功"),
    FAILED("旷课"),
    LEAVE("请假");

    private String status;

    SignType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
