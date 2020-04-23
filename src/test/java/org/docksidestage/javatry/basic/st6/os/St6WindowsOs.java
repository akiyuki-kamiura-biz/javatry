package org.docksidestage.javatry.basic.st6.os;

/**
 * @author akiyuki_kamiura
 */
public class St6WindowsOs extends St6OperationSystem {

    public St6WindowsOs(String loginId) {
        super(loginId);
    }

    @Override
    protected String getOsType() {
        return "Windows";
    }

    @Override
    protected String  getFileSeparator() {
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
