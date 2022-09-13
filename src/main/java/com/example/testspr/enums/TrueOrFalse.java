package com.example.testspr.enums;

/**
 * 是否
 *
 * @author xiongmw@wbpharma.com
 * @version 1.0
 */
public enum TrueOrFalse {

    /** 否 */
    FALSE(0),
    /** 是 */
    TRUE(1);

    public final Integer val;

    public final static int TRUE_OR_FALSE = 1;

    public int value() {
        return this.val;
    }

    TrueOrFalse(int value) {
        this.val = value;
    }
}
