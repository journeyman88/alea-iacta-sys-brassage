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
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.LocalizedResult;

/**
 *
 * @author journeyman
 */
public class BrassAgeResults extends LocalizedResult
{
    private final static String BUNDLE_NAME = "net.unknowndomain.alea.systems.brassage.RpgSystemBundle";
    
    private final List<SingleResult<Integer>> results;
    private int successes = 0;
    private SingleResult<Integer> oldValue;
    private SingleResult<Integer> newValue;
    private List<SingleResult<Integer>> successDice = new ArrayList<>();
    private List<SingleResult<Integer>> leftovers;
    private boolean increment = false;
    
    public BrassAgeResults(List<SingleResult<Integer>> results)
    {
        List<SingleResult<Integer>> tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
    }
    
    public void addSuccess(SingleResult<Integer> dice)
    {
        addSuccess(1, dice);
    }
    
    public void addAutoSuccesses(int value)
    {
        addSuccess(value, null);
    }
    
    
    private void addSuccess(int value, SingleResult<Integer> dice)
    {
        if (dice != null)
        {
            successDice.add(dice);
        }
        successes += value;
    }

    public int getSuccesses()
    {
        return successes;
    }

    public List<SingleResult<Integer>> getResults()
    {
        return results;
    }

    public SingleResult<Integer> getNewValue()
    {
        return newValue;
    }

    public void setNewValue(SingleResult<Integer> newValue)
    {
        this.newValue = newValue;
    }

    public SingleResult<Integer> getOldValue()
    {
        return oldValue;
    }

    public void setOldValue(SingleResult<Integer> oldValue)
    {
        this.oldValue = oldValue;
    }

    public List<SingleResult<Integer>> getSuccessDice()
    {
        return successDice;
    }

    public List<SingleResult<Integer>> getLeftovers()
    {
        return leftovers;
    }

    public void setLeftovers(List<SingleResult<Integer>> leftovers)
    {
        this.leftovers = leftovers;
    }

    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        messageBuilder.append(translate("brassage.results.successes", getSuccesses())).appendNewLine();
        if (verbose)
        {
            messageBuilder.append(translate("brassage.results.automatic", getSuccesses() - getSuccessDice().size())).appendNewLine();
            if (increment)
            {
                messageBuilder.append(translate("brassage.results.increment", increment));
                messageBuilder.append(getOldValue().getValue()).append(" => ").append(getNewValue().getValue()).append(")").appendNewLine();
            }
            messageBuilder.append("Roll ID: ").append(getUuid()).appendNewLine();
            messageBuilder.append(translate("brassage.results.diceResults")).append(" [ ");
            for (SingleResult<Integer> t : getResults())
            {
                messageBuilder.append("( ").append(t.getLabel()).append(" => ");
                messageBuilder.append(t.getValue()).append(") ");
            }
            messageBuilder.append("]").appendNewLine();
        }
    }

    public boolean isIncrement()
    {
        return increment;
    }

    public void setIncrement(boolean increment)
    {
        this.increment = increment;
    }

    @Override
    protected String getBundleName()
    {
        return BUNDLE_NAME;
    }
    
}
