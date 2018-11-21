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
 * (build: 2018-10-08 17:45:39 UTC)
 * on 2018-11-21 at 05:24:43 UTC 
 * Modify at your own risk.
 */

package com.example.echo.facade.model;

/**
 * Model definition for Color.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the facade. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Color extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer alpha;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer blue;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private ColorSpace colorSpace;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer green;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer red;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer rgb;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer transparency;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getAlpha() {
    return alpha;
  }

  /**
   * @param alpha alpha or {@code null} for none
   */
  public Color setAlpha(java.lang.Integer alpha) {
    this.alpha = alpha;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getBlue() {
    return blue;
  }

  /**
   * @param blue blue or {@code null} for none
   */
  public Color setBlue(java.lang.Integer blue) {
    this.blue = blue;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public ColorSpace getColorSpace() {
    return colorSpace;
  }

  /**
   * @param colorSpace colorSpace or {@code null} for none
   */
  public Color setColorSpace(ColorSpace colorSpace) {
    this.colorSpace = colorSpace;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getGreen() {
    return green;
  }

  /**
   * @param green green or {@code null} for none
   */
  public Color setGreen(java.lang.Integer green) {
    this.green = green;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getRed() {
    return red;
  }

  /**
   * @param red red or {@code null} for none
   */
  public Color setRed(java.lang.Integer red) {
    this.red = red;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getRgb() {
    return rgb;
  }

  /**
   * @param rgb rgb or {@code null} for none
   */
  public Color setRgb(java.lang.Integer rgb) {
    this.rgb = rgb;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getTransparency() {
    return transparency;
  }

  /**
   * @param transparency transparency or {@code null} for none
   */
  public Color setTransparency(java.lang.Integer transparency) {
    this.transparency = transparency;
    return this;
  }

  @Override
  public Color set(String fieldName, Object value) {
    return (Color) super.set(fieldName, value);
  }

  @Override
  public Color clone() {
    return (Color) super.clone();
  }

}
