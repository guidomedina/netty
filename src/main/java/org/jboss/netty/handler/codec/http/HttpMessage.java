/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.handler.codec.http;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * An HTTP message which provides common properties for {@link HttpRequest} and
 * {@link HttpResponse}.
 * @see HttpHeaders
 *
 * @apiviz.landmark
 * @apiviz.has org.jboss.netty.handler.codec.http.HttpChunk oneway - - is followed by
 */
public interface HttpMessage {

    /**
     * Returns the protocol version of this message.
     */
    HttpVersion getProtocolVersion();

    /**
     * Sets the protocol version of this message.
     */
    void setProtocolVersion(HttpVersion version);

    /**
     * Returns the headers of this message.
     */
    HttpHeaders headers();

    /**
     * Returns the content of this message.  If there is no content or
     * {@link #isChunked()} returns {@code true}, an
     * {@link ChannelBuffers#EMPTY_BUFFER} is returned.
     */
    ChannelBuffer getContent();

    /**
     * Sets the content of this message.  If {@code null} is specified,
     * the content of this message will be set to {@link ChannelBuffers#EMPTY_BUFFER}.
     */
    void setContent(ChannelBuffer content);

    /**
     * Returns {@code true} if and only if this message does not have any
     * content but the {@link HttpChunk}s, which is generated by
     * {@link HttpMessageDecoder} consecutively, contain the actual content.
     * <p>
     * Please note that this method will keep returning {@code true} if the
     * {@code "Transfer-Encoding"} of this message is {@code "chunked"}, even if
     * you attempt to override this property by calling {@link #setChunked(boolean)}
     * with {@code false}.
     */
    boolean isChunked();

    /**
     * Sets if this message does not have any content but the
     * {@link HttpChunk}s, which is generated by {@link HttpMessageDecoder}
     * consecutively, contain the actual content.
     * <p>
     * If this method is called with {@code true}, the content of this message
     * becomes {@link ChannelBuffers#EMPTY_BUFFER}.
     * <p>
     * Even if this method is called with {@code false}, {@link #isChunked()}
     * will keep returning {@code true} if the {@code "Transfer-Encoding"} of
     * this message is {@code "chunked"}.
     */
    void setChunked(boolean chunked);
}
