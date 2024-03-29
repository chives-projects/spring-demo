package com.csc.spring.oauth.server.service.impl;

import com.csc.spring.oauth.server.service.dto.OauthClientDetailsDto;
import com.csc.spring.oauth.server.domain.oauth.OauthClientDetails;
import com.csc.spring.oauth.server.domain.oauth.OauthRepository;
import com.csc.spring.oauth.server.service.OauthService;
import com.csc.spring.oauth.server.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Shengzhao Li
 */
@Service("oauthService")
public class OauthServiceImpl implements OauthService {


    private static final Logger LOG = LoggerFactory.getLogger(OauthServiceImpl.class);

    @Autowired
    private OauthRepository oauthRepository;

    @Override
    @Transactional(readOnly = true)
    public OauthClientDetails loadOauthClientDetails(String clientId) {
        return oauthRepository.findOauthClientDetails(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OauthClientDetailsDto> loadAllOauthClientDetailsDtos() {
        List<OauthClientDetails> clientDetailses = oauthRepository.findAllOauthClientDetails();
        return OauthClientDetailsDto.toDtos(clientDetailses);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void archiveOauthClientDetails(String clientId) {
        oauthRepository.updateOauthClientDetailsArchive(clientId, true);
        LOG.debug("{}|Update OauthClientDetails(clientId: {}) archive = true", WebUtils.getIp(), clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public OauthClientDetailsDto loadOauthClientDetailsDto(String clientId) {
        final OauthClientDetails oauthClientDetails = oauthRepository.findOauthClientDetails(clientId);
        return oauthClientDetails != null ? new OauthClientDetailsDto(oauthClientDetails) : null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void registerClientDetails(OauthClientDetailsDto formDto) {
        OauthClientDetails clientDetails = formDto.createDomain();
        oauthRepository.saveOauthClientDetails(clientDetails);
        LOG.debug("{}|Save OauthClientDetails: {}", WebUtils.getIp(), clientDetails);
    }

}