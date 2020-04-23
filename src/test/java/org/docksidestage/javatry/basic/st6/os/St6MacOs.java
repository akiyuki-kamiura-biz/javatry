package org.docksidestage.javatry.basic.st6.os;

/**
 * @author akiyuki_kamiura
 */
public class St6MacOs extends St6OperationSystem {
    public St6MacOs(String loginId) {
        super(loginId);
    }

    @Override
    protected String getOsType() {
        return "Mac";
    }

    @Override
    protected String  getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
