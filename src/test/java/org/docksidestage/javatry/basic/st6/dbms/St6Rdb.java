package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author akiyuki_kamiura
 */
public class St6Rdb {
    // TODO [質問] St6Rdb が 'limit xxx, yyy' のフォーマットの paging query を返すのは適切でしょうか？ by subaru (2020/04/23)
    // ここで定義している Rdb とはどのような概念でしょうか？
    // そしてその概念は 'limit xxx, yyy' というフォーマットのクエリとの関係はどうでしょうか？
    // その回答によってはこの実装は適切ではないかも。。
    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return "limit " + offset + ", " + pageSize;
    }
}
