# GunCorp
A consumerist game of producing various weapons, and selling them to other factories or governments.

## Screens
 - Map
   - Shows location of governments
   - Shows distribution of minerals
 - Corpopedia
   - Information about all elements
   - Information about all governments
 - Machinery Designer
   - Design weapons
   - Design machines to process raw minerals
 - Buy/Sell screen
   - Buy or sell weapons
   - Buy or sell designs
   - Buy or sell minerals (whether purified or otherwise)
   - Buy pure elements from governments

## Minerals
Each world has a total of 30^2 elements, where each position on the map is made up of a random percentage of each of them.
Ratios of elements are called minerals

### Names of minerals
When a mineral is composed of more than 80% a single element it is called `dirty {element name}`

If its greater than 90% composed of a single element it is called `fairly dirty {element name}`

when its greater than 95% it is called `purified {element name}`

when greater than 98% its called `heavily purified {element name}`.
heavily purified minerals also have a grade, these are `D` for >98%, `C` for >99%, `B` for >99.6%, `A` for >99.9%, `S` for 100% (measure to 2 digits accuracy)

otherwise, mineral names are randomly generated using a dictionary like elements.

### Naturally generating minerals 
Between 4 and 10 minerals occur naturally where whichever has a higher perlin noise value is generated in a particular area code.
Each mineral consists of up to 20 random elements in range N<=7, M<=7.

These elements may fail to generate if any of the following conditions are met:
 - The element is radioactive (has a decay rate >0)
 - The element is likely to cold react with itself
 - The element is likely to hot react with itself
 - It is not solid at room temperature (300 °K)

## Elements
Each element has various associated statistics which determine their value in the physics sim environment

### Reaction types
 - Cold reaction
   - absorbs heat
   - 2 molecules combine and decrease in heat
 - Hot reaction
   - releases a little heat
   - 2 molecules of very different M, and N values combine and split again to create 2 molecules of more similar values
 - Decay reaction
   - releases lots of heat
   - one large molecule splits into 2 smaller ones

### Stats
 - N value
 - M value
 - Cold reaction threshold (relates to pressure)
 - Cold reaction preference
 - Hot reaction threshold (relates to temperature)
 - Hot reaction preference
 - Boiling point (as temperature * pressure)
 - Melting point (as temperature * pressure)
 - Decay rate (as probability per second)
 - Preferred Density

Temperature is in Kelvins

Pressure (Outward force 0<x<∞) = True Density / Preferred Density

### Physics Data
 - Temperature
 - Density

## Save Data Contents
 - World Data
 - Company Data
   - owned schematics
   - owned weapons
   - wealth
   - reputation
   - weapons bought/sold
   - materials bought/sold
 - Government Data
   - wealth (increases 10% pa)
   - preference (reputation to value of purchase)
   - Law enforcement and army details
     - size
     - weapon preference
 - Schematics
 - Weapons
   - specs
   - average sale price