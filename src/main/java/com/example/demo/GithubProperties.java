package com.example.demo;

import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author sathaphorn.stp (T.STP)
 * @since 11-11-2018, 14:21
 */
@ConfigurationProperties("github")
@Validated
public class GithubProperties {

  /**
   * Github private access token "user:token"
   */
  @Pattern(regexp = "\\w+:\\w+")
  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
