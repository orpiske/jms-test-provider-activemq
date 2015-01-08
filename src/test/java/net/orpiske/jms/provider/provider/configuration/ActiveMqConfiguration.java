/**
 Copyright 2014 Otavio Rodolfo Piske

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package net.orpiske.jms.provider.configuration;

import net.orpiske.jms.provider.ProviderConfiguration;
import net.orpiske.jms.provider.activemq.ActiveMqProvider;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * This class configures the provider once it has been initialized
 */
public class ActiveMqConfiguration implements
        ProviderConfiguration<ActiveMqProvider>
{
    private static final Logger logger = LoggerFactory.getLogger
            (ActiveMqConfiguration.class);

    /**
     * The address used by the broker
     */
    public static final String CONNECTOR = "tcp://localhost:61616";


    /**
     * Configure the provider
     * @param provider the provider to configure
     */
    public void configure(ActiveMqProvider provider) {
        logger.info("Configuring the provider");
        provider.setUri(CONNECTOR);

        /**
         * Configure the broker to use the Maven's target directory (ie.:
         * ${basedir}/target/test-classes) as the data directory for the
         * broker. Therefore, it is cleaned whenever 'mvn clean' is run.
         */
        BrokerService brokerService = provider.getBroker();
        String path = null;
        URL url = this.getClass().getResource("/");

        /*
          Check if we are running it in within the jar, in which case we
          won't be able to use its location ...
         */

        if (url == null) {
            /*
             ... and, if that's the case, we use the OS temporary directory
             for the data directory
             */
            path = FileUtils.getTempDirectoryPath();
        }
        else {
            path = url.getPath();
        }

        brokerService.setDataDirectory(path);
        brokerService.setPersistent(false);

        try {
            brokerService.addConnector(CONNECTOR);
        } catch (Exception e) {
            throw new RuntimeException("Unable to add a connector for the "
                    + "service: " + e.getMessage(), e);
        }
    }
}
