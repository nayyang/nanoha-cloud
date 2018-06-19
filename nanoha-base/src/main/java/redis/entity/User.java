/**  
 *User.java         2016-9-20下午4:25:01
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis.entity;

import java.io.Serializable;

/**
 * @Title:
 * @Description:
 * @Author:nanoha
 * @Since:2016-9-20
 * @Version:1.0
 */
public class User implements Serializable {

    /**  
     *   
     */
    private static final long serialVersionUID = -1567393224509241960L;

    private String id;
    private String name;
    private String password;

    public User() {
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", password=");
        builder.append(password);
        builder.append("]");
        return builder.toString();
    }

}
