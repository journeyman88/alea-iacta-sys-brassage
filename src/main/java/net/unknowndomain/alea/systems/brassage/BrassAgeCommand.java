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

import java.util.Locale;
import java.util.Optional;
import net.unknowndomain.alea.systems.RpgSystemCommand;
import net.unknowndomain.alea.systems.RpgSystemDescriptor;
import net.unknowndomain.alea.roll.GenericRoll;
import net.unknowndomain.alea.systems.RpgSystemOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author journeyman
 */
public class BrassAgeCommand extends RpgSystemCommand
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BrassAgeCommand.class);
    private static final RpgSystemDescriptor DESC = new RpgSystemDescriptor("Brass Age", "ba1", "brass-age");
    
    public BrassAgeCommand()
    {
        
    }
    
    @Override
    public RpgSystemDescriptor getCommandDesc()
    {
        return DESC;
    }

    @Override
    protected Logger getLogger()
    {
        return LOGGER;
    }

    @Override
    protected Optional<GenericRoll> safeCommand(RpgSystemOptions options, Locale lang)
    {
        Optional<GenericRoll> retVal;
        if (options.isHelp() || !(options instanceof BrassAgeOptions) )
        {
            retVal = Optional.empty();
        }
        else
        {
            BrassAgeOptions opt = (BrassAgeOptions) options;
            GenericRoll roll; 
            if (opt.isInitiativeMode())
            {
                roll = new BrassAgeInitiativeRoll(opt.getFatigue(), lang);
            }
            else
            {
                roll = new BrassAgeRoll(opt.getPotential(), opt.getThreshold(), lang, opt.getModifiers());
            }
            retVal = Optional.of(roll);
        }
        return retVal;
    }

    @Override
    public RpgSystemOptions buildOptions()
    {
        return new BrassAgeOptions();
    }
    
}
