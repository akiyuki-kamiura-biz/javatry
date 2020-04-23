package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author akiyuki_kamiura
 */
public class HitPoint {
    protected int hitPoint;
    protected String barkWord;

    public HitPoint(int initialHitPoint, String barkWord){
        this.hitPoint = initialHitPoint;
        this.barkWord = barkWord;
    }

    public void damaged() {
        --hitPoint;
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + barkWord);
        }
    }

    public int getHitPoint () {
        return hitPoint;
    }
}