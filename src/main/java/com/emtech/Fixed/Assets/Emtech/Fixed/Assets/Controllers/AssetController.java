package com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Controllers;

import com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Entity.Asset;
import com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AssetService assetService;

    @PostMapping("/create")
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    @PostMapping("/{assetId}/receive")
    public ResponseEntity<String> receiveAsset(@PathVariable Long assetId, @RequestParam BigDecimal value) {
        assetService.receiveAsset(assetId, value);
        return ResponseEntity.ok("Asset received successfully.");
    }

    @PostMapping("/{assetId}/dispose")
    public ResponseEntity<String> disposeAsset(@PathVariable Long assetId) {
        assetService.disposeAsset(assetId);
        return ResponseEntity.ok("Asset disposed successfully.");
    }
    @GetMapping("/{assetId}/profitOrLoss")
    public ResponseEntity<BigDecimal> calculateProfitOrLoss(@PathVariable Long assetId) {
        BigDecimal profitOrLoss = assetService.calculateProfitOrLoss(assetId);
        return ResponseEntity.ok(profitOrLoss);
    }
}
