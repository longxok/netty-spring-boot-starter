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

import com.xxlabaza.utils.netty.NettyServer;
import com.xxlabaza.utils.netty.config.BuildContextConfigurer;

import lombok.val;
import org.springframework.beans.factory.support.GenericBeanDefinition;

class ConfigurerNettyServer implements BuildContextConfigurer<NettyServerBuildContext> {

  @Override
  public void configure (NettyServerBuildContext context) {
    val suffix = "NettyServer";
    if (context.containsBean(suffix)) {
      return;
    }

    val server = NettyServer.builder()
        .serverBootstrap(context.serverBootstrap)
        .properties(context.properties)
        .build();

    val definition = new GenericBeanDefinition();
    definition.setBeanClass(NettyServer.class);
    definition.setInstanceSupplier(() -> server);
    definition.setDestroyMethodName("close");

    context.register(suffix, definition);
  }
}
