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

package com.xxlabaza.utils.netty.client.single;

import io.appulse.utils.Bytes;
import io.appulse.utils.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Sharable
class MessageEncoder extends MessageToByteEncoder<Bytes> {

  MessageEncoder () {
    super(false);
  }

  @Override
  public void exceptionCaught (ChannelHandlerContext context, Throwable cause) throws Exception {
    log.error("Error during channel connection with {}",
              context.channel().remoteAddress(), cause);

    context.fireExceptionCaught(cause);
    context.close();
  }

  @Override
  protected void encode (ChannelHandlerContext context, Bytes bytes, ByteBuf out) throws Exception {
    val buffer = bytes.array();
    val offset = bytes.readerIndex();
    val length = bytes.readableBytes();
    try {
      out.writeBytes(buffer, offset, length);
      bytes.readerIndex(offset + length);
      log.debug("Message was sent");
    } catch (Exception ex) {
      val hex = HexUtil.prettyHexDump(bytes, offset, length);
      log.error("Error during encoding message for {}\n  {}\n",
                context.channel().remoteAddress(), hex, ex);
      throw ex;
    }
  }
}
