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
package org.docksidestage.javatry.basic.st6.os;

/**
 * @author jflute
 */
abstract class St6OperationSystem {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final String loginId;
    private String osType;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem(String loginId) {
        this.loginId = loginId;
        this.osType = initOsType();
    }

    // done kamiura 初期化のためであればメソッド名はinitOsTypeなどがいいかな by winkichanwi 20200520
    protected String initOsType() {
        return "unknown";
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    // done ここで定義されている getFileSeparator をそのまま呼び出すシチュエーションって想定されるかな？ by subaru (2020/04/23)
    // つまり new St6OperationSystem().getFileSeparator() みたいな呼び出しだね。
    // あくまでこのクラスは概念的なもので実際に呼び出される時は、Mac OS や Windows OS という具体的な OS の時のみということであれば
    // 抽象メソッドとして定義した方がよいかも。

    // TODO done teachers abstarct メソッドに変更するにあたり, abstract クラスに変更しました。
    // この場合、他の変数やメソッドの扱いはこのままでよろしいのでしょうか？
    // いいと思います by winkichanwi 20200520
    abstract protected String getFileSeparator();

    abstract protected String getUserDirectory();
}