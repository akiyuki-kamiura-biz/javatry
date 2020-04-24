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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.objanimal.*;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.mover.Crawler;
import org.docksidestage.bizfw.basic.objanimal.mover.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.os.St6MacOs;
import org.docksidestage.javatry.basic.st6.os.St6OldWindowsOs;
import org.docksidestage.javatry.basic.st6.os.St6WindowsOs;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author akiyuki_kamiura
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        --quantity;
        salesProceeds = handedMoney;

        //
        // [ticket info]
        //
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        }
        alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);

        // displayPrice の変更と、引数の入れ替えを行いました
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // only logging here (normally e.g. DB insert)
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // #fixme you if step05 has been finished, you can use this code by jflute (2019/06/15)
        // _/_/_/_/_/_/_/_/_/_/
        //        Ticket ticket = booth.buyOneDayPassport(10000);
        TicketBuyResult tbr = booth.buyOneDayPassport(10000); // as temporary, remove if you finished steo05
        Ticket ticket = tbr.getTicket();
        //        Ticket ticket = new Ticket(7400); // also here

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    // 0. オブジェクトとは何か？ということに関して、「変数と処理をまとめたもの」という定義のされ方をよく見ると思います。
    // より有機的には、自分が社会で生きている中で、その内部での処理を知らなくても、
    // 「こう働きかけたら、こういう結果が得られる」と知っているだけで便利に使用できるものが存在して、
    // そのようなものを使っている感覚で使用できるもの、ということだと思いました。

    // 1. オブジェクトを適切に使うことによって、見る側の負担が一気に改善されているというのが第一に感じました。
    // 適切な関数名とメソッド名を用いることで、見る側に大きな処理の流れをまず見てから、その後細かい処理を考えさせることができると思います。
    // ただ、その分メソッドが正しく動いているかわからないところがデメリットでもあると思います。
    // 2. Step05 を実装していて、OneDayとTwoDayで処理を共通化できるところにもメリットがあると感じました。
    // TODO [comment] Good! by subaru (2020/04/23)

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getOneDayQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.isAlreadyIn());
    }

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = dog.getHitPoint();
        log(land); // your answer? => 7

        // Animal クラスの　breathedIn(), prepareAbdominalMuscle(), doBark() で
        // hitpoint がそれぞれ減っていることは確認できました。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => null
        int land = animal.getHitPoint();
        log(land); // your answer? => 7

        // 勘違いしておりました。。
        // Dogとしてインスタンス化されているため、Animal クラスに代入してもエラーにならない
        // Dogクラスの getBarkWord() は保持されたまま動く
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7

        // createAnyAnimal() 関数の返り値として Dog が返っているので、
        // 上の問題とやりたいことは同じ
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
        log(dog.getHitPoint());
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7

        // 関数の引数、親クラスとして与えられた場合も、動作は同じ
        // 参照渡しになっていると思います。
        // TODO [comment] いいですね、その通りです。 by subaru (2020/04/23)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5rd_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 10 - (1 + 2 + 2) = 5

        // 関数のオーバーライドはわかりやすかったです。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1

        // getInitialHitPoint() がオーバーライドされているところが新しかったです。
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie(); // インターフェースの継承?
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => null
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo

        // Animal クラスの soundLoudly() を見つけられていませんでした。
        // intelliJ で Shift+⌘+F でファイルを超えた検索機能を使おうと思います。
        // TODO done [comment] (もし知っていなければ)今回のように特定のメソッドがどこで override されているかどうか調べたい時はメソッドで command + option + b が便利です。 by subaru (2020/04/23)
        // 今回でいうと Loudable インターフェースの soundLoudly メソッドで上のショートカットを実行すると soundLoudly の実装先一覧が表示されます。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false

        // Loudable -> AlarmClock, Loudable -> Animal だけど,
        // 今回は AlarmClock のインスタンスなので、Animal のインスタンスにはなっていないと判断しました
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false

        // NOTE teachers このようなクラスやインターフェースの関係を理解する場合、
        // ノートの上で木構造を作りながら理解していけば大丈夫なのですか？
        // TODO done [comment] java においてはそれで良いと思います。 by subaru (2020/04/23)
        // 別に木構造が絶対と言うことではないけど、いい方法の一つということで理解して大丈夫だと思います。
        // ただし多重継承が可能なプログラミング言語においては単純な木構造にはならなくなるので注意です（java は多重継承は不可です）。
        // 少し話は脱線するけど、時間があれば多重継承の問題などこちらの記事わかりやすいのでおすすめです。
        // https://ufcpp.net/study/csharp/oop/oo_multipleinheritance/
    }

    /**
     * Make Dog class implement FastRunner interface. (the implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
        Animal seaAnimal = new Dog();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea);

        FastRunner seaFastRunner = new Dog();
        seaFastRunner.run();

        Dog seaDog = new Dog();
        log(seaDog instanceof FastRunner);
        log(seaDog instanceof Animal);
        seaDog.run();
        BarkedSound barkedSound = seaDog.bark();
        log(barkedSound.getBarkWord());

        // NOTE teachers クラスやインスタンスの継承元が複数ある場合は、
        // 宣言時のクラスをどれにするかで、
        // 使用できるメソッドが異なるという理解で大丈夫ですか？
        // TODO done [comment] そうだね。 by subaru (2020/04/23)
        // ただしダウンキャストしてあげればそのキャストしたクラスに実装されているメソッドは使うことができます。
        // 不適切なダウンキャストをするとエラーになるので注意は必要だけど。
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here

        // コンクリートクラスの意味を、（Animalクラスのような）抽象クラスに対して、
        // 直接インスタンス化するレベルのクラスであると解釈しました。
        // よって、Turtle クラスを実装しました。

        Turtle turtle = new Turtle();
        log(turtle instanceof FastRunner);
        log(turtle instanceof Animal);
        BarkedSound barkedSound = turtle.bark();
        log(barkedSound.getBarkWord());
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here

        // Crawler と HighJumper を思いつきましたが、
        // Turtle に implement させるために、runner 内に　Crawler を実装しました。
        // TODO done [質問] Crawler を runner パッケージに配置するのは適切でしょうか？ by subaru (2020/04/23)
        // TODO subaru runner パッケージ名を mover に変更しましたが、命名にはしっくりきていません。
        Crawler turtle = new Turtle();
        turtle.crawl();

    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here

        // St6Rdb という抽象クラスを作成して、
        // St6MySql, St6PostgreSql の抽象クラスとして実装しました。
        // 中身は共通化する部分がそれほどなかったので、
        // 今回は St6MySql の内容で共通化しました。

        St6MySql seaMySql = new St6MySql();
        St6PostgreSql seqPostgreSql = new St6PostgreSql();
        int pageSize = 20;
        int pageNumber = 6;
        log(seaMySql.buildPagingQuery(pageSize, pageNumber));
        log(seqPostgreSql.buildPagingQuery(pageSize, pageNumber));
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here

        String loginId = "akiyuki";
        String relativePath = "aaa/bbb/ccc";
        // relative path : home からみた相対パスであり、　../../aaa みたいなものではない、ということはわかりました。

        St6MacOs macOs = new St6MacOs(loginId);
        log(macOs.buildUserResourcePath(relativePath));

        St6WindowsOs windowsOs = new St6WindowsOs(loginId);
        log(windowsOs.buildUserResourcePath(relativePath));

        St6OldWindowsOs oldWindowsOs = new St6OldWindowsOs(loginId);
        log(oldWindowsOs.buildUserResourcePath(relativePath));
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here

        // TODO teachers レビューお願いします。
        // TODO teachers この問題の修正版(HitPoint クラスを使わないもの)を下のコミットに含めて行いました。

        // delegation の意味を調べました。
        // delegation (委譲) は処理を別クラスのメソッドに任せる場合に用いるものであり、
        // 継承との違いはクラスの関係が分離されていること、インスタンス化しなければメソッドを使用することができないこと、だと理解しました。

        // BarkingProcessの別ファイルを作成しました。

        // BarkingProcess を共通化した場合に、downHitPoint() をその中に含める必要がありますが、
        // cat クラスに特有の downHitPoint() があり、そこの共通化をどう行うかが課題だと思いました。
        // HitPointクラスを作成し、Animalクラスに持たせた上で、Catクラス内でのみ damaged() のオーバライドを行いました。
        // 次に、その実装の影響で、hitPoint の値が0になってしまった場合に投げるエラー分の文章が統一化されてしまいました。
        // 今回は汚いと思いましたが、HitPoint クラスに barkWard の情報を持たせることにして解決しました。

        // もう少しいい方法があれば教えていただきたいです。

        Dog dog = new Dog();
        BarkedSound barkedSound = dog.bark();
        log(barkedSound.getBarkWord());

    }
    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // do nothing here

        // HitPoint を 作る必要はなく、BarkingProcess のオーバーライドを行うことによって、
        // Cat 特有のプロセスを記述できることに気づいたので書き直しました。
        // 前の問題が動くことも確認しました。

        // その上で、zombie にも共通化を行おうと思った場合に、
        // ZombieDiary などという特有の処理が共通化を妨げているため、適切でないと思いました。
    }
}
