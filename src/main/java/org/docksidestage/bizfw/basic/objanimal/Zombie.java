/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 */
public class Zombie extends Animal {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========

    public static class ZombieDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }

    protected static class ZombieBarkingProcess extends BarkingProcess {
        private final ZombieDiary zombieDiary;

        public ZombieBarkingProcess (int initialHitPoint, String barkWord, ZombieDiary zombieDiary){
            super(initialHitPoint, barkWord);
            this.zombieDiary = zombieDiary;
        }

        @Override
        public void decrementHitPoint() {
        }

        @Override
        protected void breatheIn(){
            super.breatheIn();
            zombieDiary.countBreatheIn();
        }

        public ZombieDiary getZombieDiary() {
            return zombieDiary;
        }
    }

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
//        super();
        this.barkingProcess = new ZombieBarkingProcess(getInitialHitPoint(), getBarkWord(), new ZombieDiary());
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======

    @Override
    protected String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return barkingProcess.getZombieDiary();
    }
}
