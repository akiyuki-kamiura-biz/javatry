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

// TODO done kamiura add your author tag to javadoc please by jflute (2020/04/23)
/**
 * @author jflute　
 * @author akiyuki_kamiura
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;

    class Quantity {
        private int quantity;

        public Quantity(int initialQuantity) {
            this.quantity = initialQuantity;
        }

        public void decrement() {
            quantity -= 1;
        }

        public int getQuantity() { return quantity; }
    }

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========

    private Quantity oneDayQuantity = new Quantity(MAX_QUANTITY);
    private Quantity twoDayQuantity = new Quantity(MAX_QUANTITY);
    private Quantity fourDayQuantity = new Quantity(MAX_QUANTITY);

    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // TODO done kamiura これらのメソッドは、クラス内だけでの再利用ですか？であれば、privateの方がいいかなと by jflute (2020/04/23)
    // また、そういった再利用メソッド(ちょっとしたヘルパーメソッド)は、それらを使うpublicメソッドよりも下に宣言することが多いです。
    // (人にも寄るけど、新卒研修ではそのようにお願いします。でも良い再利用ですね^^)

    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        String ticketLabel = "OneDay";
        return buyTicket(1, ticketLabel, handedMoney, ONE_DAY_PRICE, oneDayQuantity);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        String ticketLabel = "TwoDay";
        return buyTicket(1, ticketLabel, handedMoney, TWO_DAY_PRICE, twoDayQuantity);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney){
        String ticketLabel = "FourDay";
        return buyTicket(1, ticketLabel, handedMoney, FOUR_DAY_PRICE, fourDayQuantity);
    }

    // TODO teachers ヘルパーメソッドをこの位置に移動しました！
    // 個人的には、helper メソッドは、Attiribute, Constructor, Accessor のように、
    // 大枠で区切られていた方がわかりやすいと思ったのですが、これに関してはどう思われますか？

    private TicketBuyResult buyTicket(int days, String ticketLabel, int handedMoney, int ticketPrice, Quantity ticketQuantity) {
        if (ticketQuantity.getQuantity() <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < ticketPrice) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }

        ticketQuantity.decrement();

        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketPrice;
        } else {
            salesProceeds = ticketPrice;
        }

        int change = handedMoney - ticketPrice;

        Ticket ticket;
        if (days == 1) {
            ticket = new OneDayTicket(ticketPrice, ticketLabel);
        } else {
            ticket = new MultipleDaysTicket(ticketPrice, days, ticketLabel);
        }

        TicketBuyResult tbr = new TicketBuyResult(ticket, change);
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
        return oneDayQuantity.getQuantity();
    }

    public int getTwoDayQuantity() {
        return twoDayQuantity.getQuantity();
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
