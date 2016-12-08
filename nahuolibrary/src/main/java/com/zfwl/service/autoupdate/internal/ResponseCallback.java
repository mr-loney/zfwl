package com.zfwl.service.autoupdate.internal;

import com.zfwl.service.autoupdate.Version;


public interface ResponseCallback {
	void onFoundLatestVersion(Version version);
	void onCurrentIsLatest();
}
