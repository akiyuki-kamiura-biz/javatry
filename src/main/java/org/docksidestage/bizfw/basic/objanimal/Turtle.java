package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.runner.Crawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for turtle(亀).
 * @author akiyuki_kamiura
 */
public class Turtle extends Animal implements Crawler {
    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Cat.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Turtle() {};

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected String getBarkWord() {
        return "......";
    }
    // ===================================================================================
    //                                                                               Bark
    //
    @Override
    public void crawl() {
        // dummy implementation
        logger.debug("..........................Crawling now");
    }
}
