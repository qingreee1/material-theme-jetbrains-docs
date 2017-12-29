---
layout: docs
title: Tab Settings
description: Control the appearance of the Editor Tabs
group: configuration
toc: true
comments: true

previous:
  url: '/docs/configuration/color-schemes'
  title: Color Schemes
next:
  url: '/docs/configuration/panel-settings'
  title: Panel Settings
---

This page displays the features and settings that customize the appearance of the Editor Tabs.
{:class='title'}

## Active Tab Options

#### Active Tab Highlighter

This setting controls the appearance of the little indicator under the current highlighted tab.

{% include figure.html content="/screens/tabs.png" caption="Active Tab Indicator" %}

- When the setting is **OFF**, the indicator color will be the [current accent color]({{site.baseurl}}/docs/configuration/accents).
- When the setting is **ON**, you can select your own custom color.


#### Thickness

This allows you to set the _thickness_ of the tab highlighter. This setting also controls the thickness of the selected item in the _Project View_.

{% include figure.html content="/screens/thickness.png" caption="Tab Highlighter Thickness" %}

In order to avoid crazy values that could ruin the UI, the values are limited between `1` to `5` pixels.

#### Reset Defaults

Click this button to reset those settings to their default values:
- Active Tab Highlight is unchecked
- The color is set back to #80CBC4
- The thickness is set to 2

#### About Tabs Placement

While most of people set their tabs to be on the top, Jetbrains IDE also give the possibility to place your tabs to the bottom, left or right side.

In that case, the active tab indicator will be placed accordingly to the tab position:
- At the top for _bottom placement_
- At the right for _right placement_
- At the left for _left placement_

{% include figure.html content="/screens/leftSideHeight.png" caption="Left Placement" %}

-----
## Other Tab Options

### Uppercase Bold Tabs

This feature allows you to set the tabs to look even more like usual [Material Design Tabs](https://material.io/guidelines/components/tabs.html#tabs-usage), e.g. in _Uppercase_ and _bold_.

{% include figure.html content="/screens/uppercasebold.png" caption="Uppercase Bold Tabs" %}

This settings gets rid of special characters such as hyphens or underscores and replace them with ++spaces++. It also converts from camel case to "Spaced Uppercase".

Note: Because of the highly experimental status of this feature, it contains some bugs. For instance, the upper casing is not set until the tab is selected.

### Tab Height

This setting will allow you to customize the height of the different tabs of the IDE (e.g. not only the editor tabs but also _debugger tabs_, _code style tabs_, etc.). This will allow you to have the ultimate Material Design Tabs experience.

The allowed values are between `18` to `60` pixels, where the default recommended value for horizontal tabs is `50`. If you use vertical tabs though, my personal recommended value would be `25` so you can place more opened tabs at the side.

{% include figure.html content="/screens/leftSideHeight.png" caption="Left Side Tab Height" %}

There is no reset button, but there is an action in the `Material Toolbar` that resets the value to its default value, `50`.

----
## Other Tweaks

#### Edited files icon

Files that are edited but not yet saved will display a little pencil icon with the current accent color on the tab, in place of the regular * asterisk *.
![editIcon](/img/icons/editIcon.png)

You need to activate this option under `Editor -> General -> Editor Tabs -> Mark modified tabs with asterisk`

#### Pinned files icon

Same as the edited file icon, pinned tabs will display a little pin on the icon, colored with the current accent color.
![pinnedIcon](/img/icons/pin.png)

#### Accent close icon

The close icon didn't change, but it will be colored to the accent color on hover.

-----
## FAQ

**Q**: Is there a way to completely remove the Active Tab Indicator?

**A**: Currently no. Even disabling the feature in the IDE will still display the indicator provided by the plugin. If there is enough demand to add this functionality, we will add it.

**Q**: The Tab Height feature is cool in the editor, but I'd rather leave other tabs untouched.

**A**: Me as well. Unfortunately currently the setting that sets the tab height is shared between all tabs in the SDK, and there is no way to distinguish between them currently. Maybe in the future if Jetbrains agrees to allow more customization natively.

**Q**: Does the active tab highlighter works with the Lighter theme?

**A**: Again, no, because of how the active tab highlight is implemented in the IDEA SDK, it is only working on Darcula based themes, and therefore only on Dark Themes. But maybe I'll try to figure out a fix for this.

**Q**: Why limiting the thickness or the tab height? I want to have 10 in thickness and 100 in tabs!!!

**A**: Because allowing values past these limits would make the UI ugly or worse, crash it. If you have a good reason to want it anyway, you can open an issue on Github with why you would want that. At most, you can still fork the plugin and tweak it to whatever you want.

**Q**: The uppercase tabs feature is so useless! Editor Tabs !== Material Design Tabs!

**A**: While I might agree with this statement, I personally find it a cool feature and it doesn't bother me. It is not allowed by default, so new users will not be startled by it, and if you don't like it you can simply turn it off.