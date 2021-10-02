/*
 * Copyright ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.besu.ethereum.api.jsonrpc.internal.results;

import org.hyperledger.besu.ethereum.core.BlockHeader;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
  "blockHash",
  "parentHash",
  "coinbase",
  "stateRoot",
  "receiptRoot",
  "logsBloom",
  "random",
  "blockNumber",
  "gasLimit",
  "gasUsed",
  "timestamp",
  "extraData",
  "baseFee",
  "transactions"
})
public class ExecutionBlockResult {
  protected final String blockHash;
  private final String parentHash;
  private final String coinbase;
  private final String stateRoot;
  private final String receiptRoot;
  private final String logsBloom;
  private final String random;
  private final String blockNumber;
  private final String gasLimit;
  private final String gasUsed;
  private final String timestamp;
  private final String extraData;
  private final String baseFeePerGas;
  protected final List<String> transactions;

  public ExecutionBlockResult(final BlockHeader header, final List<String> transactions) {
    this.blockNumber = Quantity.create(header.getNumber());
    this.blockHash = header.getHash().toString();
    this.parentHash = header.getParentHash().toString();
    this.logsBloom = header.getLogsBloom().toString();
    this.stateRoot = header.getStateRoot().toString();
    this.receiptRoot = header.getReceiptsRoot().toString();
    this.extraData = header.getExtraData().toString();
    this.baseFeePerGas = header.getBaseFee().map(Quantity::create).orElse(null);
    this.gasLimit = Quantity.create(header.getGasLimit());
    this.gasUsed = Quantity.create(header.getGasUsed());
    this.timestamp = Quantity.create(header.getTimestamp());
    this.transactions = transactions;
    this.coinbase = header.getCoinbase().toString();

    // TODO: should have an optional field for random rather than repurposing difficulty
    this.random = header.getDifficulty().toHexString();
  }

  @JsonGetter(value = "blockNumber")
  public String getNumber() {
    return blockNumber;
  }

  @JsonGetter(value = "blockHash")
  public String getHash() {
    return blockHash;
  }

  @JsonGetter(value = "parentHash")
  public String getParentHash() {
    return parentHash;
  }

  @JsonGetter(value = "logsBloom")
  public String getLogsBloom() {
    return logsBloom;
  }

  @JsonGetter(value = "random")
  public String getRandom() {
    return random;
  }

  @JsonGetter(value = "stateRoot")
  public String getStateRoot() {
    return stateRoot;
  }

  @JsonGetter(value = "receiptRoot")
  public String getReceiptRoot() {
    return receiptRoot;
  }

  @JsonGetter(value = "extraData")
  public String getExtraData() {
    return extraData;
  }

  @JsonGetter(value = "baseFeePerGas")
  public String getBaseFeePerGas() {
    return baseFeePerGas;
  }

  @JsonGetter(value = "gasLimit")
  public String getGasLimit() {
    return gasLimit;
  }

  @JsonGetter(value = "gasUsed")
  public String getGasUsed() {
    return gasUsed;
  }

  @JsonGetter(value = "timestamp")
  public String getTimestamp() {
    return timestamp;
  }

  @JsonGetter(value = "transactions")
  public List<String> getTransactions() {
    return transactions;
  }

  @JsonGetter(value = "coinbase")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public String getCoinbase() {
    return coinbase;
  }
}
