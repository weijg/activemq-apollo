/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.legacy.transport.tcp;

import org.apache.activemq.legacy.transport.TransportBrokerTestSupport;
import org.apache.activemq.openwire.BrokerTestScenario;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class SslTransportBrokerTest extends TransportBrokerTestSupport {

    public static final String KEYSTORE_TYPE = "jks";
    public static final String PASSWORD = "password";
    public static final String SERVER_KEYSTORE = "src/test/resources/server.keystore";
    public static final String TRUST_KEYSTORE = "src/test/resources/client.keystore";

    protected String getBindLocation() {
        return "ssl://localhost:0";
    }

    @BeforeTest
    protected void setUp() throws Exception {
        System.setProperty("javax.net.ssl.trustStore", TRUST_KEYSTORE);
        System.setProperty("javax.net.ssl.trustStorePassword", PASSWORD);
        System.setProperty("javax.net.ssl.trustStoreType", KEYSTORE_TYPE);        
        System.setProperty("javax.net.ssl.keyStore", SERVER_KEYSTORE);
        System.setProperty("javax.net.ssl.keyStorePassword", PASSWORD);
        System.setProperty("javax.net.ssl.keyStoreType", KEYSTORE_TYPE);        
        //System.setProperty("javax.net.debug", "ssl,handshake,data,trustmanager");        
    }

    @Override
    public Object createBean() throws Exception {
    	BrokerTestScenario scenario = (BrokerTestScenario) super.createBean();
    	scenario.maxWait = 10000;
		return scenario;
    }

}
