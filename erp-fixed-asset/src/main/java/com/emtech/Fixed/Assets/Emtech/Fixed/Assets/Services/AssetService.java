package com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Services;

import com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Entity.Asset;

import java.math.BigDecimal;

public interface AssetService {
    Asset createAsset(Asset asset);
    void receiveAsset(Long assetId, BigDecimal value);
    void disposeAsset(Long assetId);
    BigDecimal calculateProfitOrLoss(Long assetId);
// Additional methods for asset depreciation calculation, report generation, etc.
}

