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

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int oneDayQuantity = MAX_QUANTITY;
    private int twoDayQuantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========

    public void judgePassportAvailable(int handedMoney, int ticketPrice, int ticketQuantity){
        if (ticketQuantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < ticketPrice) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    public int calculateSalesAndChange(int handedMoney, int ticketPrice) {
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketPrice;
        } else {
            salesProceeds = ticketPrice;
        }

        return handedMoney - ticketPrice;
    }

    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        judgePassportAvailable(handedMoney, ONE_DAY_PRICE, oneDayQuantity);
        --oneDayQuantity;
        int change = calculateSalesAndChange(handedMoney, ONE_DAY_PRICE);
        Ticket oneDayTicket = new Ticket(ONE_DAY_PRICE, Ticket.TicketType.OneDay);
        TicketBuyResult tbr = new TicketBuyResult(oneDayTicket, change);
        return tbr;
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        judgePassportAvailable(handedMoney, TWO_DAY_PRICE, oneDayQuantity);
        --twoDayQuantity;

        int change = calculateSalesAndChange(handedMoney, TWO_DAY_PRICE);
        Ticket twoDayTicket = new Ticket(TWO_DAY_PRICE, Ticket.TicketType.TwoDay);

        TicketBuyResult tbr = new TicketBuyResult(twoDayTicket, change);
        return tbr;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getOneDayQuantity() {
        return oneDayQuantity;
    }

    public int getTwoDayQuantity() {
        return twoDayQuantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
