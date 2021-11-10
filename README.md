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