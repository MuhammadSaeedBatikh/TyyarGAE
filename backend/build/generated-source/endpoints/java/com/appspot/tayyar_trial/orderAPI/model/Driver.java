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
 * on 2017-03-24 at 03:41:14 UTC 
 * Modify at your own risk.
 */

package com.appspot.tayyar_trial.orderAPI.model;

/**
 * Model definition for Driver.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the orderAPI. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Driver extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double balance;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String city;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Location currentLocation;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String driverID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String email;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean idle;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String imageURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String password;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String phone;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer rate;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String vehicle;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getBalance() {
    return balance;
  }

  /**
   * @param balance balance or {@code null} for none
   */
  public Driver setBalance(java.lang.Double balance) {
    this.balance = balance;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCity() {
    return city;
  }

  /**
   * @param city city or {@code null} for none
   */
  public Driver setCity(java.lang.String city) {
    this.city = city;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Location getCurrentLocation() {
    return currentLocation;
  }

  /**
   * @param currentLocation currentLocation or {@code null} for none
   */
  public Driver setCurrentLocation(Location currentLocation) {
    this.currentLocation = currentLocation;
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
  public Driver setDriverID(java.lang.String driverID) {
    this.driverID = driverID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEmail() {
    return email;
  }

  /**
   * @param email email or {@code null} for none
   */
  public Driver setEmail(java.lang.String email) {
    this.email = email;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getIdle() {
    return idle;
  }

  /**
   * @param idle idle or {@code null} for none
   */
  public Driver setIdle(java.lang.Boolean idle) {
    this.idle = idle;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getImageURL() {
    return imageURL;
  }

  /**
   * @param imageURL imageURL or {@code null} for none
   */
  public Driver setImageURL(java.lang.String imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * @param name name or {@code null} for none
   */
  public Driver setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPassword() {
    return password;
  }

  /**
   * @param password password or {@code null} for none
   */
  public Driver setPassword(java.lang.String password) {
    this.password = password;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPhone() {
    return phone;
  }

  /**
   * @param phone phone or {@code null} for none
   */
  public Driver setPhone(java.lang.String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getRate() {
    return rate;
  }

  /**
   * @param rate rate or {@code null} for none
   */
  public Driver setRate(java.lang.Integer rate) {
    this.rate = rate;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getVehicle() {
    return vehicle;
  }

  /**
   * @param vehicle vehicle or {@code null} for none
   */
  public Driver setVehicle(java.lang.String vehicle) {
    this.vehicle = vehicle;
    return this;
  }

  @Override
  public Driver set(String fieldName, Object value) {
    return (Driver) super.set(fieldName, value);
  }

  @Override
  public Driver clone() {
    return (Driver) super.clone();
  }

}
