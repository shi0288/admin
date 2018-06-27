package com.xyauto.qa.util;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.xyauto.qa.cons.ApiUserCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shiqm on 2017/3/13.
 */

@Service
public class FeignClientUtil {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private ApiUserCons apiCons;

    private InstanceInfo instance;

    public InstanceInfo getInstance() {
        this.setInstance(eurekaClient.getNextServerFromEureka(apiCons.getServer_user_name(), false));
        return instance;
    }

    public void setInstance(InstanceInfo instance) {
        this.instance = instance;
    }
}
