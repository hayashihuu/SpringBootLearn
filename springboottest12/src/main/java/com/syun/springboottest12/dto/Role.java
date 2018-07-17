package com.syun.springboottest12.dto;




public class Role {
    private Integer id;


    private String name;

    private String jsms;

    private String bz;

    private Integer jlzt;

    private String glbm;

    private Integer userid;

    public Role(Integer id, String name, String jsms, String bz, Integer jlzt, String glbm, Integer userid) {
        this.id = id;
        this.name = name;
        this.jsms = jsms;
        this.bz = bz;
        this.jlzt = jlzt;
        this.glbm = glbm;
        this.userid = userid;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJsms() {
        return jsms;
    }

    public void setJsms(String jsms) {
        this.jsms = jsms == null ? null : jsms.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public Integer getJlzt() {
        return jlzt;
    }

    public void setJlzt(Integer jlzt) {
        this.jlzt = jlzt;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm == null ? null : glbm.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jsms='" + jsms + '\'' +
                ", bz='" + bz + '\'' +
                ", jlzt=" + jlzt +
                ", glbm='" + glbm + '\'' +
                ", userid=" + userid +
                '}';
    }
}