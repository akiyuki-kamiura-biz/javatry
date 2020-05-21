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
package org.docksidestage.javatry.framework;

import org.docksidestage.bizfw.di.nondi.NonDiDirectFirstAction;
import org.docksidestage.bizfw.di.nondi.NonDiDirectSecondAction;
import org.docksidestage.bizfw.di.nondi.NonDiFactoryMethodAction;
import org.docksidestage.bizfw.di.nondi.NonDiIndividualFactoryAction;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Dependency Injection (DI) as beginner level. <br>
 * Show answer by log() or write answer on comment for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step41DependencyInjectionBeginnerTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        Precondition
    //                                                                        ============
    /**
     * Search "Dependency Injection" by internet and learn it in thirty minutes. (study only) <br>
     * ("Dependency Injection" をインターネットで検索して、30分ほど学んでみましょう。(勉強のみ))
     */
    public void test_whatis_DependencyInjection() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // What is Dependency Injection?
        // - - - - - (your answer?)
        // wikipedia によると、
        //      "コンポーネント間の依存関係をプログラムのソースコードから排除するために、
        //      外部の設定ファイルなどでオブジェクトを注入できるようにするソフトウェアパターンである。"
        // と定義されていました。
        //
        // クラス間の依存関係とは、あるクラスが別のクラスのインスタンス(や定数や変数)を固定して持っていることで、
        //      そのクラスを使用する時に変更に対しての柔軟でなかったり、テストのし易さが制限されていること、であり、
        // クラスをインスタンス化する時やした後に、動作の変更が難しくなってしまうことが問題だと捕らえられていることがわかりました。
        //
        // クラスをインスタンス化するタイミングで、依存クラスや定数や変数を注入することで、依存性を克服しようというのがDIの考え方であり、
        // セッターとして内部にセットする方法(セッターインジェクション)や、コンストラクタ内での宣言(コンストラクタインジェクション)があり、
        // そこでセットできる依存先のクラスの選択性を確保しておくことが、変更への柔軟性を保持しておくために大事なのだと思いました。
        //
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                                 Non DI Code Reading
    //                                                                 ===================
    /**
     * What is the difference between NonDiDirectFirstAction and NonDiDirectSecondAction? <br>
     * (NonDiDirectFirstAction と NonDiDirectSecondAction の違いは？)
     */
    public void test_nondi_difference_between_first_and_second() {
        // your answer? =>
        // 実装レベルの違いをまとめながら考察するためまとめます。
        // callFriend() について:  first では Dog クラスを初期化して、 bark() しているだけの実装に対して、
        //                       second では TooLazyDog クラスを使用して、内部では Cat クラスが　bark()　しており、
        //                       second は TooLazyDog と Cat の二つに依存しています。
        // wakeupMe() について  :  second では、Cat の2回目の bark() で hitPoint が0になってしまい、Exceptionを吐かれます
        // goToOffice(), sendGift() について:  first では、superCarDealer が初期化され、orderSupercar していますが、
        //                                    特定の clientRequirement にて実行すると必ずエラーを吐かれる実装になっています
        //                                    second では、superCarManufacturer の makeSuperCar のオーバーライドを行なっており、
        //                                    結果として、誤った clientRequirement にて実行している goToOffice() のみエラーが起こります
        //
        // first と second の違いというよりは、依存していることで不利だと感じた点を以下に列挙します
        // - callFriend(), wakeupMe() での first においては、dog.bark() はプリントするものではなく、
        //                  　barkedSound を返すものなので、正しい使い方を理解しなければならないこと　or BarkedSound へも暗に依存していること
        // - callFriend(), wakeupMe() での second については、new するだけではなく、
        //                   はじめにいくつかの処理を経ないと必ずエラーを吐く処理が存在するため、知っておく必要があること
        // - goToOffice(), sendGift() については、 dealer.orderSupercar について、特定の条件では必ずエラーが吐かれることを知っておく必要があること
        //                   or おそらくこれらのメソッドをテストする時に、すごくテストしづらい状況になってしまうため、
        //                      デバッグする際には second のような手法を用いてデバッグしなければならないことがすごく手間であること
        // and your confirmation code here freely

        NonDiDirectFirstAction nddfa = new NonDiDirectFirstAction();
        NonDiDirectSecondAction nddsa = new NonDiDirectSecondAction();

        log("========== callFriend() ");
        nddfa.callFriend();
        nddsa.callFriend();

        log("========== wakeupMe() ");
//        nddfa.wakeupMe();
//        nddsa.wakeupMe();

        log("========== goToOffice()");
//        nddfa.goToOffice();
//        nddsa.goToOffice();

        log("========== sendGift()");
//        nddfa.sendGift();
//        nddsa.sendGift();

    }

    /**
     * What is the difference between NonDiDirectSecondAction and NonDiFactoryMethodAction? <br>
     * (NonDiDirectSecondAction と NonDiFactoryMethodAction の違いは？)
     */
    public void test_nondi_difference_between_second_and_FactoryMethod() {
        // your answer? => Factory Method では SecondAction における生成ロジックを共通化して切り出しているため、
        //          生成ロジックを変更する際の変更箇所が少ないし、可読性が高いと思いました
        // and your confirmation code here freely

        NonDiDirectSecondAction nddsa = new NonDiDirectSecondAction();
        NonDiFactoryMethodAction ndfma = new NonDiFactoryMethodAction();

        log("========== callFriend() ");
        nddsa.callFriend();
        ndfma.callFriend();

        log("========== wakeupMe() ");
        nddsa.wakeupMe();
        ndfma.wakeupMe();

        log("========== goToOffice()");
//        nddsa.goToOffice();
//        ndfma.goToOffice();

        log("========== sendGift()");
//        nddsa.sendGift();
//        ndfma.sendGift();
    }

    /**
     * What is the difference between NonDiFactoryMethodAction and NonDiIndividualFactoryAction? <br>
     * (NonDiFactoryMethodAction と NonDiIndividualFactoryAction の違いは？)
     */
    public void test_nondi_difference_between_FactoryMethod_and_IndividualFactory() {
        // your answer? => FactoryMethod に対して、IndividualFactory では、
        //                    生成ロジックを別ファイルに切り出しているため、変更が容易でした
        // and your confirmation code here freely

        NonDiIndividualFactoryAction ndifa = new NonDiIndividualFactoryAction();
        // "tofu" => "spagetti" に変更しました
        ndifa.callFriend();
//        ndifa.wakeupMe();

    }

    // ===================================================================================
    //                                                               Using DI Code Reading
    //                                                               =====================
    /**
     * What is the difference between UsingDiAccessorAction and UsingDiAnnotationAction? <br>
     * (UsingDiAccessorAction と UsingDiAnnotationAction の違いは？)
     */
    public void test_usingdi_difference_between_Accessor_and_Annotation() {
        // your answer? => 
        // and your confirmation code here freely

    }

    /**
     * What is the difference between UsingDiAnnotationAction and UsingDiDelegatingAction? <br>
     * (UsingDiAnnotationAction と UsingDiDelegatingAction の違いは？)
     */
    public void test_usingdi_difference_between_Annotation_and_Delegating() {
        // your answer? => 
        // and your confirmation code here freely
    }

    // ===================================================================================
    //                                                           Execute like WebFramework
    //                                                           =========================
    /**
     * Execute callFriend() of accessor action by UsingDiWebFrameworkProcess. <br>
     * (accessor の Action の callFriend() を UsingDiWebFrameworkProcess 経由で実行してみましょう)
     */
    public void test_usingdi_UsingDiWebFrameworkProcess_callfriend_accessor() {
        // execution code here
    }

    /**
     * Execute callFriend() of annotation and delegating actions by UsingDiWebFrameworkProcess.
     * (And you can increase hit-points of sleepy cat in this method) <br>
     * (annotation, delegating の Action の callFriend() を UsingDiWebFrameworkProcess 経由で実行してみましょう。
     * (眠い猫のヒットポイントをこのメソッド内で増やしてもOK))
     */
    public void test_usingdi_UsingDiWebFrameworkProcess_callfriend_annotation_delegating() {
        // execution code here
    }

    /**
     * What is concrete class of instance variable "animal" of UsingDiAnnotationAction? (when registering UsingDiModule) <br>
     * (UsingDiAnnotationAction のインスタンス変数 "animal" の実体クラスは？ (UsingDiModuleを登録した時))
     */
    public void test_usingdi_whatis_animal() {
        // your answer? => 
        // and your confirmation code here freely
    }

    // ===================================================================================
    //                                                                        DI Container
    //                                                                        ============
    /**
     * What is DI container? <br>
     * (DIコンテナとは？)
     */
    public void test_whatis_DIContainer() {
        // your answer? => 
        // and your confirmation code here freely
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Lasta Di application? <br>
     * (以下のLasta DiアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/lastaflute/lastaflute-example-harbor
     */
    public void test_zone_search_component_on_LastaDi() {
        // your answer? => 
    }

    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Spring application? <br>
     * (以下のSpringアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/dbflute-example/dbflute-example-on-springboot
     */
    public void test_zone_search_component_on_Spring() {
        // your answer? => 
    }
}
