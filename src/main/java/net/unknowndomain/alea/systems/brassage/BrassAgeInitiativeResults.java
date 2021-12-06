/*
 * Copyright 2020 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.brassage;

import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.LocalizedResult;

/**
 *
 * @author journeyman
 */
public class BrassAgeInitiativeResults extends LocalizedResult
{
    private final static String BUNDLE_NAME = "net.unknowndomain.alea.systems.brassage.RpgSystemBundle";
    
    private final SingleResult<Integer> result;
    
    public BrassAgeInitiativeResults(SingleResult<Integer> result)
    {
        this.result = result;
    }

    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        messageBuilder.append(translate("brassage.results.initiative", result.getValue())).appendNewLine();
    }

    @Override
    protected String getBundleName()
    {
        return BUNDLE_NAME;
    }
    
}
