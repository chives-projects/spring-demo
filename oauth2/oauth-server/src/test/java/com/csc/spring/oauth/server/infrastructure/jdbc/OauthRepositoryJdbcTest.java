/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.csc.spring.oauth.server.infrastructure.jdbc;

import com.csc.spring.oauth.server.domain.oauth.OauthClientDetails;
import com.csc.spring.oauth.server.domain.oauth.OauthRepository;
import com.csc.spring.oauth.server.domain.shared.GuidGenerator;
import com.csc.spring.oauth.server.infrastructure.AbstractRepositoryTest;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.*;


/*
  * @author Shengzhao Li
  */
public class OauthRepositoryJdbcTest extends AbstractRepositoryTest {


    @Autowired
    private OauthRepository oauthRepositoryMyBatis;


    @Test
    public void findOauthClientDetails() {
        OauthClientDetails oauthClientDetails = oauthRepositoryMyBatis.findOauthClientDetails("unity-client");
        assertNull(oauthClientDetails);

    }


    @Test
    public void saveOauthClientDetails() {

        final String clientId = GuidGenerator.generate();

        OauthClientDetails clientDetails = new OauthClientDetails().clientId(clientId);
        oauthRepositoryMyBatis.saveOauthClientDetails(clientDetails);

        final OauthClientDetails oauthClientDetails = oauthRepositoryMyBatis.findOauthClientDetails(clientId);
        assertNotNull(oauthClientDetails);
        assertNotNull(oauthClientDetails.clientId());
        assertNull(oauthClientDetails.clientSecret());

    }

    @Test
    public void findAllOauthClientDetails() {
        final List<OauthClientDetails> allOauthClientDetails = oauthRepositoryMyBatis.findAllOauthClientDetails();
        assertNotNull(allOauthClientDetails);
        assertTrue(allOauthClientDetails.isEmpty());
    }

    @Test
    public void updateOauthClientDetailsArchive() {
        oauthRepositoryMyBatis.updateOauthClientDetailsArchive("ddooelddd", true);
    }


}