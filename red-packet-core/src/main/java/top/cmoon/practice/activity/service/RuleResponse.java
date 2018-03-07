package top.cmoon.practice.activity.service;

/**
 * Created by cool_moon on 2018/3/2.
 */
public class RuleResponse {


    public boolean ok;
    public String msg;


    public RuleResponse(boolean ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
