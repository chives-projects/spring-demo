package com.csc.spring.oauth.client.infrastructure.json;

import net.sf.json.JsonConfig;

/**
 * @author Shengzhao Li
 */

public interface CustomJsonConfigurer {


    void config(JsonConfig jsonConfig);

}