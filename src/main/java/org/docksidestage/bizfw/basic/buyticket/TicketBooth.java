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

// done kamiura add your author tag to javadoc please by jflute (2020/04/23)

/**
 * @author jflute　
 * @author akiyuki_kamiura
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========

    private static final int MAX_QUANTITY = 10;

    class TicketInfo {
        private int days;
        private int storedQuantity;
        private int ticketPrice;
        private String ticketLabel;

        public TicketInfo(int days, String ticketLabel, int ticketPrice, int initialQuantity) {
            this.days = days;
            this.ticketLabel = ticketLabel;
            this.ticketPrice = ticketPrice;
            this.storedQuantity = initialQuantity;
        }

        public void decrementQuantity() {
            storedQuantity -= 1;
        }

        // accessor
        public int getDays() {
            return days;
        }

        public String getTicketLabel() {
            return ticketLabel;
        }

        public int getTicketPrice() {
            return ticketPrice;
        }

        public int getStoredQuantity() {
            return storedQuantity;
        }
    }

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========

    private TicketInfo oneDayTicketInfo = new TicketInfo(1, "OneDay", 7400, MAX_QUANTITY);
    private TicketInfo twoDayTicketInfo = new TicketInfo(2, "TwoDay", 13200, MAX_QUANTITY);
    private TicketInfo fourDayTicketInfo = new TicketInfo(4, "FourDay", 22400, MAX_QUANTITY);

    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // done kamiura これらのメソッドは、クラス内だけでの再利用ですか？であれば、privateの方がいいかなと by jflute (2020/04/23)
    // また、そういった再利用メソッド(ちょっとしたヘルパーメソッド)は、それらを使うpublicメソッドよりも下に宣言することが多いです。
    // (人にも寄るけど、新卒研修ではそのようにお願いします。でも良い再利用ですね^^)

    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        return buyTicket(oneDayTicketInfo, handedMoney);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        return buyTicket(twoDayTicketInfo, handedMoney);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        return buyTicket(fourDayTicketInfo, handedMoney);
    }

    // done teachers ヘルパーメソッドをこの位置に移動しました！
    // 個人的には、helper メソッドは、Attribute, Constructor, Accessor のように、
    // 大枠で区切られていた方がわかりやすいと思ったのですが、これに関してはどう思われますか？
    // NOTE Helperメソッドは基本的にHelperメソッドを使うメソッドの近くに置くことで、追いやすい方向ですね by winkichanwi 20200511

    // done kamiura さらに訓練ですが、他のStep進んで余裕があったこそここに戻ってくださいね by winkichanwi 20200511
    // 基本的にticketLabelによって、どのticketPriceとどのQuantityを渡すのが決まっていますよね。それを表現するためにリファクタしてみてもらえます？

    // done teachers (winki さんの上の質問に対して) テストの考え方という講義の Code Smell という考え方の中に、
    // 「同じ引数の組み合わせを多くの関数で使用されている場合はクラスとして抽出する（データの群れ）」という方法を教わったので、今回はそれを適用しました。
    // この手法で適切であったかのフィードバックをいただきたいです。
    // ticketQuantity クラスの用途を広げて、ticketLabel, ticketPrice をメンバ変数として持つ ticketInfo クラスを用意しました。
    // NOTE うまいですね！早速コードスメルについての知識活用したんだね。ここはdata clumpsというコードスメルの対策です。by winkichanwi 20200520

    // done teachers また、ticketInfo クラスという命名が適切かについても少し疑問が残ります。。。
    // より良い命名があれば教えていただきたいです。。
    // NOTE 命名って基本的に役割を表すで、確かチケット情報もっているが、その中のquantityはmutableだから、quantityの状態を持っているクラスでも言えるので、
    // TicketStateくらいは思いつきましたが今のままでもいいですね。 by winkichanwi 20200520

    private TicketBuyResult buyTicket(TicketInfo ticketInfo, int handedMoney) {
        if (ticketInfo.getStoredQuantity() <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < ticketInfo.getTicketPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }

        ticketInfo.decrementQuantity();

        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketInfo.getTicketPrice();
        } else {
            salesProceeds = ticketInfo.getTicketPrice();
        }

        int change = handedMoney - ticketInfo.getTicketPrice();

        Ticket ticket;
        if (ticketInfo.getDays() == 1) {
            ticket = new OneDayTicket(ticketInfo.getTicketPrice(), ticketInfo.getTicketLabel());
        } else {
            ticket = new MultipleDaysTicket(ticketInfo.getTicketPrice(), ticketInfo.getDays(), ticketInfo.getTicketLabel());
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
        return oneDayTicketInfo.getStoredQuantity();
    }

    public int getTwoDayQuantity() {
        return twoDayTicketInfo.getStoredQuantity();
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
