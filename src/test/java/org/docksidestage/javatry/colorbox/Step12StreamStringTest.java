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
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author akiyuki_kamiura
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // getColorBoxList() -> 10個の異なる StandardColorBox を返す
        // StandardColorBox (<- AbstractColorBox) は, color と spaceList と size を持つ

        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);
        // => green: 5文字
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        // TODO done teachers 冗長ではないか、正しい処理になっているのかについて、
        //  このような書き方でいいのかをフィードバックいただきたいです。

        String answer = colorBoxList.stream()
                // TODO done kamiura ここから2行は .flatMap(colorBox -> colorBox.getSpaceList().stream()) に書き換えられる by winkichanwi 20200520
                // ここは多分flatMapあまりわかってないでしょうか？
                // TODO done teachers おそらく、flatMap について理解が及んでいないと思うし、List は Stream 化できるという発想もないと思います。
                //  flatMap は複数の stream を平坦にまとめて一つの stream にする効用があるということですか？
                // [comment] すごーい完全理解したじゃん、その通りです by winkichanwi 20200520
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                // TODO done kamiura .maxのドキュメントをよく見るとComparatorの引数を求めているから、書き換えてみてわからないなら聞いてください by winkicahnwi 20200520
                .max(Comparator.comparingInt(String::length))
                .orElse("*not found");

        log(answer);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        IntSummaryStatistics iss = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .mapToInt(obj -> ((String) obj).length())
                .summaryStatistics();

        int answer = iss.getMax() - iss.getMin();
        log(answer);
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .map(obj -> (String) obj)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .skip(1)
                .findFirst()
                .orElse("*not found");

        log(answer);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .mapToInt(obj -> ((String) obj).length())
                .sum();

        log(answer);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String answer = colorBoxList.stream()
                .map(colorBox -> colorBox.getColor().getColorName())
                .max(Comparator.comparingInt(String::length))
                .orElse("*not found");

        log(answer);

        // TODO done teachers yellow と purple がありますが、この場合、yellow だけでいいのでしょうか？
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String answer = colorBoxList.stream()
                // TODO kamiura mapしてからnullをfilterするよりfilterしてからmapした方がnull扱わなくて良いので、書き換えてみたください by winkichanwi 20200521
                .map(colorBox -> {
                    Boolean isContainedTargetString = colorBox.getSpaceList()
                            .stream()
                            .map(boxSpace -> boxSpace.getContent())
                            .filter(obj -> obj instanceof String)
                            .map(obj -> (String) obj)
                            // [comment] anyMatchの使い方いいね by winkichanwi 20205021
                            .anyMatch(str -> str.startsWith("Water"));

                    return isContainedTargetString ? colorBox.getColor().getColorName() : null;
                }).filter(str -> str != null).findFirst().orElse("*not found");

        log(answer);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String answer = colorBoxList.stream().map(colorBox -> {
            Boolean isContainedTargetString = colorBox.getSpaceList()
                    .stream()
                    .map(boxSpace -> boxSpace.getContent())
                    .filter(obj -> obj instanceof String)
                    .map(obj -> (String) obj)
                    .anyMatch(str -> str.endsWith("front"));

            return isContainedTargetString ? colorBox.getColor().getColorName() : null;
        }).filter(str -> str != null).findFirst().orElse("*not found");

        log(answer);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .filter(str -> str.endsWith("front"))
                .map(str -> str.indexOf("front") + 1)
                .findFirst()
                .orElse(-1);

        // TODO kamiura -1はどういう意味ですか？意味のある出力をしてください by winkichanwi 20200520
        log(answer);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .map(str -> {
                    // TODO kamiura [訓練] indexOfのやり方では悪くないけど、splitのバージョン余裕あればやってみてください by winkichanwi 20200521
                    int firstIndex = str.indexOf("ど");
                    int secondIndex = -1;
                    if (firstIndex != -1 && firstIndex != str.length() - 1) {
                        // TODO kamiura .subStringと使うより、indexOf(String str, int fromIndex)というメソッドありますよ by winkichanwi 20200521
                        secondIndex = str.substring(firstIndex + 1).indexOf("ど");
                    }

                    return (secondIndex != -1) ? (firstIndex + secondIndex + 1) + 1 : null;
                })
                .filter(str -> str != null)
                .findFirst()
                .orElse(-1);

        log(answer);
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .filter(str -> str.endsWith("front"))
                .map(str -> String.valueOf(str.charAt(0)))
                .findFirst()
                .orElse("*not found");

        log(answer);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .filter(str -> str.startsWith("Water"))
                .map(str -> String.valueOf(str.charAt(str.length() - 1)))
                .findFirst()
                .orElse("*not found");

        log(answer);
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .filter(str -> str.contains("o"))
                .map(str -> str.replace("o", ""))
                .mapToInt(str -> str.length())
                .sum();

        log(answer);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof File)
                .map(obj -> ((File) obj).toString())
                .map(str -> "c:¥¥" + str.substring(1))
                .map(str -> str.replace("/", "¥¥"))
                .findFirst()
                .orElse("*not found");

        log(answer);

        // NOTE 問題とは関係ないけど、c:¥¥　を最初につけるべき...?

    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(obj -> obj instanceof YourPrivateRoom.DevilBox)
                .map(obj -> (YourPrivateRoom.DevilBox) obj)
                .peek(devilBox -> devilBox.wakeUp())
                .peek(devilBox -> devilBox.allowMe())
                .peek((devilBox -> devilBox.open()))
                .map(devilBox -> {
                    String text = null;
                    try {
                        text = devilBox.getText();
                    } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                        log(e.getMessage());
                    }
                    return text;
                })
                .filter(str -> str != null)
                .mapToInt(str -> str.length())
                .sum();

        log(answer);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
