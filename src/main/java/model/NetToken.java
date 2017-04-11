package model;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class NetToken extends Token {
    private Net innerNet;
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Net getInnerNet() {
        return innerNet;
    }

    public void setInnerNet(Net innerNet) {
        this.innerNet = innerNet;
    }
}
