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
    protected int hitPoint;
    private String barkWord;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    BarkingProcess(int currentHitPoint, String barkWord) {
        this.hitPoint = currentHitPoint;
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
        decrementHitPoint();
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        decrementHitPoint();
    }

    private BarkedSound doBark(String barkWord) {
        decrementHitPoint();
        return new BarkedSound(barkWord);
    }

    public void decrementHitPoint() {
        --hitPoint;
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + barkWord);
        }
    }

    // ===================================================================================
    //                                                                               Accessor
    //                                                                              ======
    public int getHitPoint(){
        return hitPoint;
    }

    public Zombie.ZombieDiary getZombieDiary() {
        return null;
    }
}
