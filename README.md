# alea-iacta-sys-brassage
A RPG system module for Alea Iacta Est implementing Brass Age

## Description
This command implements the dice rule system of Brass Age and can work in three modes: Initiative mode, Notation mode and Extended mode.
The first one is a simple dice + modifier mode, while the others will calculate successes based on potential, threshold, exploding tens, automatic successes and incremented results.

### Initiative Mode
This mode is a simple way to resolve initiative, rolling  1d10 and summing the current fatigue value.

### Notation Mode
This is a shortcut for the extended mode,translating a single P/T notation (13/6) to -p 13 -t 6. 

### Extended Mode
This mode will roll up to 10d10 - depending on the potential - exploding any 10 obtained, then check for success against the threshold and, if the potential is >= 10 adding the relative automatic successes, and doing the dice increment on a dice according to the rules, trying to achieve the maximum number of successes.

### Roll modifiers
Passing these parameters, the associated modifier will be enabled:

* `-v` : Will enable a more verbose mode that will show a detailed version of every result obtained in the roll.

## Help print
```
Brass Age [ brass-age | ba1 ]

Usage: ba1 -i <fatigueValue>
   or: ba1 -n <diceNotation>
   or: ba1 -p <potentialValue> -t <thresholdValue>

Description:
This command implements the dice rule system of Brass Age and
can work in three modes: Initiative mode, Notation mode and
Extended mode. The first one is a simple dice + modifier
mode, while the others will calculate successes based on
potential, threshold, exploding tens, automatic successes and
incremented results.

Options:
  -p, --potential=potentialValue

  -t, --threshold=thresholdValue
                  Threshold for the dice pool between 10 and 5. Requires
                    potential
  -n, --notation=diceNotation
                  Roll in P/T notation
  -i, --initiative=fatigueValue
                  Do an initiative check
  -h, --help      Print the command help
  -v, --verbose   Enable verbose output
```
