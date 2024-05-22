package com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Services;

import com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Entity.Asset;
import com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Asset createAsset(Asset asset) {
        // Initial values will be automatically set by Asset entity
        return assetRepository.save(asset);
    }

    @Override
    public void receiveAsset(Long assetId, BigDecimal value) {
        Optional<Asset> optionalAsset = assetRepository.findById(assetId);
        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();
            // Update accumulated depreciation, net book value will be recalculated automatically
            asset.setAccumulatedDepreciation(asset.getAccumulatedDepreciation().add(value));
            assetRepository.save(asset);
        } else {
            throw new IllegalArgumentException("Asset not found with id: " + assetId);
        }
    }

    @Override
    public void disposeAsset(Long assetId) {
        Optional<Asset> optionalAsset = assetRepository.findById(assetId);
        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();
            // Dispose the asset at salvage value
            asset.disposeAtSalvageValue();
            assetRepository.save(asset);
        } else {
            throw new IllegalArgumentException("Asset not found with id: " + assetId);
        }
    }

    @Override
    public BigDecimal calculateProfitOrLoss(Long assetId) {
        Optional<Asset> optionalAsset = assetRepository.findById(assetId);
        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();
            return asset.calculateProfitOrLoss();
        } else {
            throw new IllegalArgumentException("Asset not found with id: " + assetId);
        }
    }
}
