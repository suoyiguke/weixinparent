package com.yinkai.entities.custom;

        import com.yinkai.entities.Forum;

        import java.io.Serializable;

public class ForumCustom extends Forum implements Serializable {

    private String adminName;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
