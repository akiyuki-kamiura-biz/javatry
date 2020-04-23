package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author akiyuki_kamiura
 */
public class St6Rdb {
    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return "limit " + offset + ", " + pageSize;
    }
}
