package com.zfwl.share;/*
 * Copyright (C) 2015 Bilibili <jungly.ik@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.bilibili.socialize.share.core.SharePlatformConfig;
import com.bilibili.socialize.share.util.SharePlatformConfigHelper;

/**
 * @author Jungly
 * @email jungly.ik@gmail.com
 * @since 2015/10/8
 */
public class ConfigHelper {

    // FIXME: 16/4/12
    public static final String QQ_APPID = "";
    public static final String QQ_APPKEY = "";
    public static final String WECHAT_APPID = "";
    public static final String WECHAT_APPSECRET = "";
    public static final String SINA_APPKEY = "";

    private ConfigHelper() {
    }

    public static void configPlatformsIfNeed() {
        if (SharePlatformConfig.hasAlreadyConfig())
            return;

        SharePlatformConfigHelper.configQQPlatform(QQ_APPID, QQ_APPKEY);
        SharePlatformConfigHelper.configWeixinPlatform(WECHAT_APPID, WECHAT_APPSECRET);
        SharePlatformConfigHelper.configSina(SINA_APPKEY);

    }
}