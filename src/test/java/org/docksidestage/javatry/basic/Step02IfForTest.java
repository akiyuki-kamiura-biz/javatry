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

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() {
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else {
            sea = 9;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList(); // broadway, dockside, hangar, magiclamp
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList(); // broadway, dockside, hangar, magiclamp
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
        // 便利な書き方ができる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList(); // broadway, dockside, hangar, magiclamp
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList(); // broadway, dockside, hangar, magiclamp
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
        // 書き方が特徴的...
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList(); // broadway, dockside, hangar, magiclamp
        List<String> stageListContainA = new ArrayList<>();

        for (String stage : stageList) {
            if (stage.contains("a")) {
                stageListContainA.add(stage);
            }
        }

        for (String stage : stageListContainA) {
            log(stage);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList(); // broadway, dockside, hangar, magiclamp
        String[] sea = { null };
        boolean[] isEnd = { false };
        stageList.forEach(stage -> {
            if (stage.startsWith("br") || isEnd[0]) {
                return;
            }
            sea[0] = stage;
            if (stage.contains("ga")) {
                isEnd[0] = true;
            }
        });
        // TODO kamiura 一応、自分の Eclipse 上だと、このような警告が出ています by jflute (2020/04/22)
        // Type String[] of the last argument to method log(Object...) doesn't exactly match the vararg parameter type.
        // Cast to Object[] to confirm the non-varargs invocation,
        // or pass individual arguments of type Object for a varargs invocation
        // log()メソッドが可変長引数なので配列をそのまま入れると、exactly match にならないということですね。
        // 警告なので動かなくはないけど、ちょっと危険なので、もうちょい明示的な方が安全だということですね。
        // でも、log((Object[]) sea); は相当気持ち悪いですね。。。
        log(sea); // should be same as before-fix

        // 外部の変数がfinal化されてしまう問題について、
        // 参照型の変数を使用することで克服しました
        // もう少し上手いやり方があるなら教えていただきたいです。
        //
        // TODO kamiura すごい回避の仕方。でもこれしかないですね by jflute (2020/04/22)
        // Javaは安全のためにコールバック内で利用する変数は自然とfinalにしています。
        // (コールバックは、厳密にはいつどういうタイミングで実行されるかわからない、というのもあって)
        // なので、こういうことをやりたくなったら、isEndみたいに配列とかのオブジェクトにします。
        //
        // そもそも、このように外側の変数を書き換える必要があるようなループの場合は、forEach()メソッドは向いていないとも言えます。
        // 一方で、forEach()メソッドは、「外側の変数を間違えて書き換えてしまう危険性が少ないやり方」と言えます。適材適所ですね。
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を): どのような外部変数ならforEachの中で変更できるのかを調べよう
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
        List<String> stageList = prepareStageList();

        Integer[] intVar = { null };
        char[] charVar = { 0 };
        StringBuilder sb = new StringBuilder();

        stageList.forEach(stage -> {
            intVar[0] = 9;
            charVar[0] = 0;
            sb.append(stage);
        });

        log(sb);

        // 上手いやり方がわからないので、教えていただけるとありがたいです...
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
