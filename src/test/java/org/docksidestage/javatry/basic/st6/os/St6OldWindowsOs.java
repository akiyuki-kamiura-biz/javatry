package org.docksidestage.javatry.basic.st6.os;

/**
 * @author akiyuki_kamiura
 */
public class St6OldWindowsOs extends St6OperationSystem {
    public St6OldWindowsOs(String loginId) {
        super(loginId);
    }

    @Override
    protected String getOsType() {
        return "OldWindows";
    }

    @Override
    protected String  getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settigs/" + loginId;
    }
}
