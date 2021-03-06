/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xxlabaza.utils.netty.config.server.builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * A configuration class for preparing environment and
 * instantiation the new {@link com.xxlabaza.utils.netty.NettyServer}
 * beans and all attendant beans.
 */
@Configuration
@SuppressWarnings({
    "PMD.UseUtilityClass",
    "checkstyle:HideUtilityClassConstructor"
})
public class NettyServerBuilderConfiguration {

  /**
   * A bean post processor for creating all necessary beans and the new
   * {@link com.xxlabaza.utils.netty.NettyServer} beans from
   * {@link com.xxlabaza.utils.netty.config.server.NettyServerConfig}.
   *
   * @param environment Spring's environment bean.
   *
   * @return the bean post processor for {@link com.xxlabaza.utils.netty.NettyServer}s.
   */
  @Bean
  static NettyServerConfigsBeanFactoryPostProcessor nettyServerConfigsBeanFactoryPostProcessor (Environment environment) {
    return new NettyServerConfigsBeanFactoryPostProcessor(environment);
  }
}
