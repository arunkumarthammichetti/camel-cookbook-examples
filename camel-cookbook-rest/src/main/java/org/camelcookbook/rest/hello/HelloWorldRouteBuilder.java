/*
 * Copyright (C) Scott Cranton, Jakub Korab, and Christian Posta
 * https://github.com/CamelCookbook
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

package org.camelcookbook.rest.hello;

import org.apache.camel.builder.RouteBuilder;

/**
 * Simple REST DSL example
 */
public class HelloWorldRouteBuilder extends RouteBuilder {
    private int port1;

    public HelloWorldRouteBuilder() {
    }

    public HelloWorldRouteBuilder(int port) {
        this.port1 = port;
    }

    public void setPort1(int port1) {
        this.port1 = port1;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("netty4-http").port(port1);

        rest("/say")
            .get("/hello").to("direct:hello")
            .get("/bye").to("direct:bye");

        from("direct:hello")
            .transform().constant("Hello World");
        from("direct:bye")
            .transform().constant("Bye World");
    }
}
