/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.transform.wandainfo;

import org.apache.seatunnel.api.configuration.util.OptionRule;
import org.apache.seatunnel.api.table.catalog.CatalogTable;
import org.apache.seatunnel.api.table.connector.TableTransform;
import org.apache.seatunnel.api.table.factory.Factory;
import org.apache.seatunnel.api.table.factory.TableTransformFactory;
import org.apache.seatunnel.api.table.factory.TableTransformFactoryContext;

import com.google.auto.service.AutoService;
import org.apache.seatunnel.shade.com.typesafe.config.Config;

@AutoService(Factory.class)
public class WandaInfoTransformFactory implements TableTransformFactory {
    @Override
    public String factoryIdentifier() {
        return WandaInfoTransform.PLUGIN_NAME;
    }

    @Override
    public OptionRule optionRule() {
        return OptionRule.builder()
                .bundled(WandaInfoTransformConfig.url, WandaInfoTransformConfig.app_key)
                .bundled(WandaInfoTransformConfig.app_secret)
                .build();
    }

    @Override
    public TableTransform createTransform(TableTransformFactoryContext context) {
        WandaInfoTransformConfig wandainfoTransformConfig = WandaInfoTransformConfig.of(context.getOptions());
        CatalogTable catalogTable = context.getCatalogTables().get(0);
        return () -> new WandaInfoTransform(wandainfoTransformConfig, catalogTable);
    }
}
