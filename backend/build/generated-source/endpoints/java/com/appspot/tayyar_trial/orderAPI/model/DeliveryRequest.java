/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2017-02-15 17:18:02 UTC)
 * on 2017-03-25 at 04:51:25 UTC 
 * Modify at your own risk.
 */

package com.appspot.tayyar_trial.orderAPI.model;

/**
 * Model definition for DeliveryRequest.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the orderAPI. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class DeliveryRequest extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean customerConfirmPickUP;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String customerID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String customerLocationID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean driverConfirmPickUP;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String driverID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> driversWhoRefusedIDs;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private ListOfOrders listOfOrders;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String merchantID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String merchantType;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getCustomerConfirmPickUP() {
    return customerConfirmPickUP;
  }

  /**
   * @param customerConfirmPickUP customerConfirmPickUP or {@code null} for none
   */
  public DeliveryRequest setCustomerConfirmPickUP(java.lang.Boolean customerConfirmPickUP) {
    this.customerConfirmPickUP = customerConfirmPickUP;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCustomerID() {
    return customerID;
  }

  /**
   * @param customerID customerID or {@code null} for none
   */
  public DeliveryRequest setCustomerID(java.lang.String customerID) {
    this.customerID = customerID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCustomerLocationID() {
    return customerLocationID;
  }

  /**
   * @param customerLocationID customerLocationID or {@code null} for none
   */
  public DeliveryRequest setCustomerLocationID(java.lang.String customerLocationID) {
    this.customerLocationID = customerLocationID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getDriverConfirmPickUP() {
    return driverConfirmPickUP;
  }

  /**
   * @param driverConfirmPickUP driverConfirmPickUP or {@code null} for none
   */
  public DeliveryRequest setDriverConfirmPickUP(java.lang.Boolean driverConfirmPickUP) {
    this.driverConfirmPickUP = driverConfirmPickUP;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDriverID() {
    return driverID;
  }

  /**
   * @param driverID driverID or {@code null} for none
   */
  public DeliveryRequest setDriverID(java.lang.String driverID) {
    this.driverID = driverID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDriversWhoRefusedIDs() {
    return driversWhoRefusedIDs;
  }

  /**
   * @param driversWhoRefusedIDs driversWhoRefusedIDs or {@code null} for none
   */
  public DeliveryRequest setDriversWhoRefusedIDs(java.util.List<java.lang.String> driversWhoRefusedIDs) {
    this.driversWhoRefusedIDs = driversWhoRefusedIDs;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public DeliveryRequest setId(java.lang.String id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public ListOfOrders getListOfOrders() {
    return listOfOrders;
  }

  /**
   * @param listOfOrders listOfOrders or {@code null} for none
   */
  public DeliveryRequest setListOfOrders(ListOfOrders listOfOrders) {
    this.listOfOrders = listOfOrders;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getMerchantID() {
    return merchantID;
  }

  /**
   * @param merchantID merchantID or {@code null} for none
   */
  public DeliveryRequest setMerchantID(java.lang.String merchantID) {
    this.merchantID = merchantID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getMerchantType() {
    return merchantType;
  }

  /**
   * @param merchantType merchantType or {@code null} for none
   */
  public DeliveryRequest setMerchantType(java.lang.String merchantType) {
    this.merchantType = merchantType;
    return this;
  }

  @Override
  public DeliveryRequest set(String fieldName, Object value) {
    return (DeliveryRequest) super.set(fieldName, value);
  }

  @Override
  public DeliveryRequest clone() {
    return (DeliveryRequest) super.clone();
  }

}
