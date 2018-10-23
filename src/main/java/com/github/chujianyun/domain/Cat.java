package com.github.chujianyun.domain;

/**
 * @author 明明如月
 * @date 2018/10/23
 */
public class Cat {

    private String cname;

    private Byte cage;

    public Cat(String cname, Byte cage) {
        this.cname = cname;
        this.cage = cage;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Byte getCage() {
        return cage;
    }

    public void setCage(Byte cage) {
        this.cage = cage;
    }

}
