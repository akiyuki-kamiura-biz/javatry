package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author akiyuki_kamiura
 */
abstract class St6Rdb {
    // TODO done [質問] St6Rdb が 'limit xxx, yyy' のフォーマットの paging query を返すのは適切でしょうか？ by subaru (2020/04/23)
    // ここで定義している Rdb とはどのような概念でしょうか？
    // そしてその概念は 'limit xxx, yyy' というフォーマットのクエリとの関係はどうでしょうか？
    // その回答によってはこの実装は適切ではないかも。。

    // TODO teachers [回答] ここでは、query を作成するメソッドしかないクラスに対して、
    // 細かい定義や動作まで決めることができていませんでした。
    // abstract method について知ることができたので、抽象クラス、抽象メソッドとして定義することで、
    // サブクラス側での実装に拡張性を持たせることを採用しようと思います。
    abstract protected String buildPagingQuery(int pageSize, int pageNumber);
}
