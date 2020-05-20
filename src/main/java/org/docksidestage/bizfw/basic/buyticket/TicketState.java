package org.docksidestage.bizfw.basic.buyticket;

// TODO kamiura クラスが長くなったら、別ファイルにおいた方がいいかも by winkichanwi 20200520
public class TicketState {
    private int days;
    private int storedQuantity;
    private int ticketPrice;
    private String ticketLabel;

    public TicketState(int days, String ticketLabel, int ticketPrice, int initialQuantity) {
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
