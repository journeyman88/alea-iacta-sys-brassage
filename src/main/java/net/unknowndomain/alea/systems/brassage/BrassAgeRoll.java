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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.unknowndomain.alea.dice.standard.D10;
import net.unknowndomain.alea.pools.DicePool;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class BrassAgeRoll implements GenericRoll
{
    
    
    private final DicePool<D10> dicePool;
    private final Integer potential;
    private final Integer threshold;
    private final Set<BrassAgeModifiers> mods;
    
    public BrassAgeRoll(Integer potential, Integer threshold, BrassAgeModifiers ... mod)
    {
        this(potential, threshold, Arrays.asList(mod));
    }
    
    public BrassAgeRoll(Integer potential, Integer threshold, Collection<BrassAgeModifiers> mod)
    {
        this.mods = new HashSet<>();
        if (mod != null)
        {
            this.mods.addAll(mod);
        }
        int dice = potential;
        if (potential > 10)
        {
            dice = 10;
        }
        if (potential < 1 )
        {
            potential = 1;
            threshold = 10;
        }
        if (threshold < 5)
        {
            threshold = 5;
        }
        if (threshold > 10)
        {
            threshold = 10;
        }
        this.dicePool = new DicePool<>(D10.INSTANCE, dice, 10);
        this.potential = potential;
        this.threshold = threshold;
    }
    
    @Override
    public GenericResult getResult()
    {
        List<Integer> resultsPool = this.dicePool.getResults();
        List<Integer> res = new ArrayList<>();
        res.addAll(resultsPool);
        BrassAgeResults results = buildResults(res);
        results.setVerbose(mods.contains(BrassAgeModifiers.VERBOSE));
        return results;
    }
    
    private BrassAgeResults buildResults(List<Integer> res)
    {
        res.sort((Integer o1, Integer o2) ->
        {
            return o1.compareTo(o2);
        });
        BrassAgeResults results = new BrassAgeResults(res);
        int auto = this.potential / 10;
        List<Integer> left = new ArrayList<>();
        results.addAutoSuccesses(auto);
        while(!res.isEmpty())
        {
            int value = res.remove(0);
            if (value >= this.threshold)
            {
                results.addSuccess(value);
            }
            else
            {
                left.add(value);
            }
        }
        if ((this.potential > 10) && (!left.isEmpty()))
        {
            results.setIncrement(true);
            int module = this.potential % 10;
            if (module > left.size())
            {
                module = left.size();
            }
            int value = left.remove(module-1);
            results.setOldValue(value);
            results.setNewValue(value+3);
            if (results.getNewValue() >= this.threshold)
            {
                results.addSuccess(results.getNewValue());
            }
            else
            {
                left.add(results.getNewValue());
            }
        }
        results.setLeftovers(left);
        return results;
    }
}
