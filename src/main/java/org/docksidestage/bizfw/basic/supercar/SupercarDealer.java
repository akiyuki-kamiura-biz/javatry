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
package org.docksidestage.bizfw.basic.supercar;

import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;

/**
 * The dealer(販売業者) of supercar.
 * @author jflute
 */
public class SupercarDealer {

    public Supercar orderSupercar(String clientRequirement) { // clientRequirement = "steering wheel is like sea"
        if (clientRequirement.contains("steering wheel is like sea")) { // true
            return makeSupercar("piari");
        } else if (clientRequirement.contains("steering wheel is useful on land")) {
            return makeSupercar("land");
        } else if (clientRequirement.contains("steering wheel has many shop")) {
            return makeSupercar("piari");
        } else {
            throw new IllegalStateException("Cannot understand the client requirement: " + clientRequirement);
        }
    }

    private Supercar makeSupercar(String catalogKey) throws DealerCannotProvideSupercarException {
        SupercarManufacturer manufacturer = createSupercarManufacturer();

        // error handling
        Supercar supercar;
        try {
            supercar = manufacturer.makeSupercar(catalogKey);
        } catch (SupercarManufacturer.SupercarCannotMakeBySteeringWheelException e) {
            throw new DealerCannotProvideSupercarException("Dealer cannot prepare ordered supercar because of manufacturer disorder.", e);
        }
        return supercar;
    }

    protected SupercarManufacturer createSupercarManufacturer() {
        return new SupercarManufacturer();
    }

    public static class DealerCannotProvideSupercarException extends RuntimeException {
        public DealerCannotProvideSupercarException(String msg, Throwable e) {
            super(msg, e);
        }
    }
}
