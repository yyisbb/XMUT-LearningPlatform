package cn.edu.xmut.learningplatform.constant;

public enum Status {
    DISABLE(0),
    ENABLE(1);

    private int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
