/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.debitcard;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.tfb.aibank.biz.user.repository.DebitCardRepository;
import com.tfb.aibank.biz.user.repository.entities.DebitCardEntity;
import com.tfb.aibank.biz.user.services.debitcard.model.DebitCardModel;

// @formatter:off
/**
 * @(#)DebitCardService.java
 * 
 * <p>Description:簽帳卡服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DebitCardService {

    private DebitCardRepository debitCardRepository;

    public DebitCardService(DebitCardRepository debitCardRepository) {
        this.debitCardRepository = debitCardRepository;
    }

    /**
     * 取得所有簽帳卡
     * 
     * @return
     */
    public List<DebitCardModel> getAllDebitCardImg() {
        List<DebitCardEntity> debitCardEntities = this.debitCardRepository.findAll();
        final Integer defaultPicIndex = -1;
        // 預設卡面
        String defaultCardPictureUrl = debitCardEntities.stream().filter(cardPic -> Objects.equals(cardPic.getCardType(), defaultPicIndex)).map(DebitCardEntity::getCardPicture).findFirst().orElse("");
        return debitCardEntities.stream().map(entity -> {
            DebitCardModel model = new DebitCardModel();
            model.setCardType(entity.getCardType());
            model.setCardPicture(Optional.ofNullable(entity.getCardPicture()).orElse(defaultCardPictureUrl));
            model.setLocale(entity.getLocale());
            model.setCardName(entity.getCardName());
            return model;
        }).toList();
    }

}
