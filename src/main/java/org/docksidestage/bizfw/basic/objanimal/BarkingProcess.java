package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author akiyuki_kamiura
 */
public class BarkingProcess {
    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private HitPoint hitPoint;
    private String barkWord;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    BarkingProcess(HitPoint hitPoint, String barkWord) {
        this.hitPoint = hitPoint;
        this.barkWord = barkWord;
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    private void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        hitPoint.damaged();
    }

    private void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        hitPoint.damaged();
    }

    private BarkedSound doBark(String barkWord) {
        hitPoint.damaged();
        return new BarkedSound(barkWord);
    }
}
