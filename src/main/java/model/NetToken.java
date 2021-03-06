package model;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class NetToken extends Token {
    private Net innerNet;

    public String getId() {
        return innerNet.getId();
    }

    public void setId(String id) {
        innerNet.setId(id);
    }

    public Net getInnerNet() {
        return innerNet;
    }

    public void setInnerNet(Net innerNet) {
        this.innerNet = innerNet;
    }
}
