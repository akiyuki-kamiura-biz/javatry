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
package org.docksidestage.bizfw.basic.buyticket;

public class MultipleDaysTicket implements Ticket {
    // ===================================================================================
    //                                                                         Attribute
    //                                                                         ===========
    private int displayPrice;
    private int remainingDays;
    private String ticketLabel;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public MultipleDaysTicket(int displayPrice, int remainingDays, String ticketLabel) {
        this.displayPrice = displayPrice;
        this.remainingDays = remainingDays;
        this.ticketLabel = ticketLabel;
    }

    // ===================================================================================
    //                                                                         In Park
    //                                                                         ===========
    public void doInPark() {
        if (remainingDays <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        --remainingDays;
    }

    // ===================================================================================
    //                                                                         Accessor
    //                                                                         ===========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public String getTicketLabel() { return ticketLabel; }
}
