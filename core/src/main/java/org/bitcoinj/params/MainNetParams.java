/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 128;
        addressHeader = 81;
        p2shHeader = 5;
        segwitAddressHrp = "bc";
        port = 9253;
        packetMagic = 0xf9beb4d9L;
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1e3fffff);
        genesisBlock.setTime(1415384723L);
        genesisBlock.setNonce(369858);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 500000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("000009f7e55e9e3b4781e22bd87a7cfa4acada9e4340d43ca738bf4e9fb8f5ce"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(0, Sha256Hash.wrap("000009f7e55e9e3b4781e22bd87a7cfa4acada9e4340d43ca738bf4e9fb8f5ce"));
        checkpoints.put(21613, Sha256Hash.wrap("00000026d817ea814504686131485ef24b5782f3fedc954e8520e345f04b61b8"));
        checkpoints.put(51104, Sha256Hash.wrap("000000543aa26a791eac84bce37d29780c84b0039e7871181cfb60ad75681c31"));
        checkpoints.put(80608, Sha256Hash.wrap("00000000b909a6037afd8697d7f7e718b133c2d2a6e810d40bd38d5eaaebeb07"));
        checkpoints.put(194862, Sha256Hash.wrap("000000b69614bb7ba3de61e65b4ac98dcd63134eef83dbbac5bb4a0e3b859c8a"));
        checkpoints.put(398630, Sha256Hash.wrap("00000087ab8e37bae1cc0441a3473c253bca847340d5530439964b2d5ee43fa1"));
        checkpoints.put(543227, Sha256Hash.wrap("000000576442a8fa691bccebed4a35509025e7c97f691af08b4bbae96675e57e"));
        checkpoints.put(746790, Sha256Hash.wrap("0000017098883fac12441cdc6d06069ee2ccf8031c6ead40281b240ec549b060"));
        checkpoints.put(890507, Sha256Hash.wrap("000000b9fb587f4976e63776c9f92a9a429cb0217803f0323a09a121bcaec1a9"));

        dnsSeeds = new String[] {
                "113.146.115.99",
                "133.18.173.147",
                "203.152.216.75",
                "203.152.216.76",
                "203.152.216.77",
                "213.32.89.205",
                "222.10.42.148",
                "35.231.30.152"
        };
        httpSeeds = new HttpDiscovery.Details[] {
        };

        addrSeeds = new int[] {
            0x63739271,
            0x93ad1285,
            0x4bd898cb,
            0x4cd898cb,
            0x4dd898cb,
            0xcd5920d5,
            0x942aade,
            0x981ee723
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
