/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.alea.systems.brassage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.unknowndomain.alea.systems.RpgSystemOptions;
import net.unknowndomain.alea.systems.annotations.RpgSystemData;
import net.unknowndomain.alea.systems.annotations.RpgSystemOption;


/**
 *
 * @author journeyman
 */
@RpgSystemData(bundleName = "net.unknowndomain.alea.systems.brassage.RpgSystemBundle")
public class BrassAgeOptions extends RpgSystemOptions
{
    @RpgSystemOption(name = "potential", shortcode = "p", description = "brassage.options.action", argName = "potentialValue")
    private Integer potential;
    @RpgSystemOption(name = "threshold", shortcode = "t", description = "brassage.options.threshold", argName = "thresholdValue")
    private Integer threshold;
    @RpgSystemOption(name = "notation", shortcode = "n", description = "brassage.options.notation", argName = "diceNotation")
    private String notation;
    @RpgSystemOption(name = "initiative", shortcode = "i", description = "brassage.options.initiative", argName = "fatigueValue")
    private Integer initiative;
            
                        
    @Override
    public boolean isValid()
    {
        return !(
                isHelp() || 
                ((initiative != null) && (potential != null || threshold != null || notation != null)) || 
                (notation != null && (potential != null || threshold != null)) || 
                (potential != null ^ threshold != null)
                );
    }
    
    public boolean isInitiativeMode()
    {
        return (initiative != null) && (potential == null && threshold == null && notation == null);
    }
    
    public boolean isNotationMode()
    {
        return (notation != null && (potential == null && threshold == null));
    }
    
    public boolean isBasicMode()
    {
        return (potential != null && threshold != null);
    }

    public Integer getPotential()
    {
        int p = 0;
        if (isBasicMode())
        {
            p += potential;
        }
        if (isNotationMode())
        {
            String [] n = notation.split("/");
            p += Integer.parseInt(n[0]);
        }
        return p;
    }

    public Integer getThreshold()
    {
        int t = 0;
        if (isBasicMode())
        {
            t += threshold;
        }
        if (isNotationMode())
        {
            String [] n = notation.split("/");
            t += Integer.parseInt(n[1]);
        }
        return t;
    }

    public Collection<BrassAgeModifiers> getModifiers()
    {
        Set<BrassAgeModifiers> mods = new HashSet<>();
        if (isVerbose())
        {
            mods.add(BrassAgeModifiers.VERBOSE);
        }
        return mods;
    }

    public Integer getFatigue()
    {
        int f = 0;
        if (initiative != null)
        {
            f += initiative;
        }
        return f;
    }
    
}