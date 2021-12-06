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
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.random.dice.bag.D10;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class BrassAgeInitiativeRoll implements GenericRoll
{
    
    private final int fatigueValue;
    private final Locale lang;
    
    public BrassAgeInitiativeRoll(int fatigueValue, Locale lang)
    {
        this.fatigueValue = fatigueValue;
        this.lang = lang;
    }

    @Override
    public GenericResult getResult()
    {
        SingleResult<Integer> dice = D10.INSTANCE.nextResult().get();
        BrassAgeInitiativeResults result = new BrassAgeInitiativeResults( new SingleResult<>("d10 + " + fatigueValue, dice.getValue() + fatigueValue));
        result.setLang(lang);
        return result;
    }
    
}
