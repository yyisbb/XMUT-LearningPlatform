package cn.edu.xmut.learningplatform.constant;

public enum RoleType {
    TEACHER(3),
    STUDENT(2),
    ADMIN(1);

    private int type;

    RoleType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
